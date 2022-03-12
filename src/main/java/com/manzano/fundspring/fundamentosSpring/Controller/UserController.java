package com.manzano.fundspring.fundamentosSpring.Controller;

import com.manzano.fundspring.fundamentosSpring.Entity.User;
import com.manzano.fundspring.fundamentosSpring.UseCases.CreateUser;
import com.manzano.fundspring.fundamentosSpring.UseCases.DeleteUser;
import com.manzano.fundspring.fundamentosSpring.UseCases.GetUser;
import com.manzano.fundspring.fundamentosSpring.UseCases.UpdateUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/userOpers")
public class UserController {

    //Aqui se crearan las implementaciones de CRUD

    /*Para este tipo de implementaciones, debemos manejarnos a traves de los casos de uso que vamos a ejemplificar dada
    la logica que busquemos a seguir en nuestro servicio

    En esta primera parte, necesitamos obtner todos los usuarios con base en la configuración de GetUser, su imp, y su
    archivo de config
     */
    private GetUser getUser;
    private CreateUser createUser;
    private DeleteUser deleteUser;
    private UpdateUser updateUser;



    public UserController(GetUser getUser, CreateUser createUser, DeleteUser deleteUser, UpdateUser updateUser) {
        this.getUser = getUser;
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.updateUser = updateUser;
    }

    @RequestMapping("/getAllUsers")
    List<User> getAllUsers(){
        return getUser.getAll();
    }

    @RequestMapping("/newUser")
    ResponseEntity<User> createNewUser(@RequestBody User newUser){
        return new ResponseEntity<>(createUser.saveNewUser(newUser), HttpStatus.CREATED);
    }

    @RequestMapping("/delete/{id}")
    ResponseEntity deleteUser(@PathVariable Long id){
        deleteUser.deleteUser(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping("/update/{id}")
    ResponseEntity<User> updateUser(@RequestBody User toUpdateUser, @PathVariable Long id){
        return new ResponseEntity<>(updateUser.updateUser(toUpdateUser, id), HttpStatus.ACCEPTED);
    }



}
