package com.manzano.fundspring.fundamentosSpring.Repository;

import com.manzano.fundspring.fundamentosSpring.Dto.UserDto;
import com.manzano.fundspring.fundamentosSpring.Entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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

    /*Esta es la implementación de un Query method, así no requeririamos como tal de un query de jpql*/
    List<User> findByName(String name);

    Optional<User> findByEmailAndName(String email, String name);

    List<User> findByNameLike(String name);

    List<User> findByNameOrEmail(String name, String Email);

    List<User> findByDateBirthBetween(LocalDate begin, LocalDate end);

    List<User> findByNameLikeOrderByIdDesc(String name);

    List<User> findByNameContainingOrderByIdDesc(String name);

    @Query(" Select new com.manzano.fundspring.fundamentosSpring.Dto.UserDto(u.id, u.name, u.dateBirth) " +
            " from User u " +
            " where u.dateBirth=:parametroFecha " +
            " and u.email=:parametroEmail ")
    Optional<UserDto> getAllByBirthDateAndEmail(@Param("parametroFecha") LocalDate dateBirth,
                                                @Param("parametroEmail") String email);
}
