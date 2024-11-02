package com.example.user.repository;

import com.example.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**=====================================================
     * Declare a method to find a user by username
     * Optional is used to represent a nullable result
     * Method name follows Spring Data JPA naming conventions
     ======================================================== */

    Optional<User> findByUserName(String userName);

}
