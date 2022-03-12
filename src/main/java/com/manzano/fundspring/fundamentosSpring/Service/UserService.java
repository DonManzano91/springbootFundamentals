package com.manzano.fundspring.fundamentosSpring.Service;

import com.manzano.fundspring.fundamentosSpring.Entity.User;
import com.manzano.fundspring.fundamentosSpring.Repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private final Log LOGGER = LogFactory.getLog(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Transactional
    public void saveTransaction(List<User> users){
        users.stream()
                .forEach(userRepository::save);
        //Lo de arriba es equivalente a lo de aca abajo
        //      .forEach(user -> userRepository.save(user))
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }


    public User saveNewUser(User newUser) {
        return userRepository.save(newUser);
    }

    public void deleteUser(Long id) {
        userRepository.delete(new User(id));
    }

    public User updateUser(User toUpdateUser, Long id) {
        return userRepository.findById(id)
                .map(
                        newUser -> {
                            newUser.setEmail(toUpdateUser.getEmail());
                            newUser.setDateBirth(toUpdateUser.getDateBirth());
                            newUser.setName(toUpdateUser.getName());
                            return userRepository.save(newUser);
                        }
                ).orElseThrow(
                        () -> new RuntimeException("No existe el usuario que quieres modificar.")
                );
    }
}
