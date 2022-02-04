package com.manzano.fundspring.fundamentosSpring.Repository;

import com.manzano.fundspring.fundamentosSpring.Entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*Interfaz que extendra JPA, de aqui se obtendran metodos abstractos que ayudaran a implementar llamadas y
* operaciones en base de datos */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /*La estructura aqui colocada es la de JPQL*/
    @Query("Select u from User u where u.email=?1")
    Optional<User> findByUserEmail(String email); /*-> Esta es una implementación de JpaRepository*/

    @Query("Select u from User u where u.name like ?1%")
    List<User> findAndSort(String name, Sort sort);

}
