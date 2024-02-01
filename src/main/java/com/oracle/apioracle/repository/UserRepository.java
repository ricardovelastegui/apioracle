package com.oracle.apioracle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oracle.apioracle.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query("UPDATE user u SET u.name = :name, u.age = :age WHERE u.id=:id")
    void parcialUpdateId(Long id, String name, Long age);


    void desactivarById(Long id, boolean activo);
}
