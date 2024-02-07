package com.oracle.apioracle.services;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oracle.apioracle.entities.User;
import com.oracle.apioracle.repository.UserRepository;


import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public User save(User user) {
        return userRepository.save(user);
    }


    
    public List<User> getAllusers(){
        return userRepository.findAll();
    }


    public User getById(Long id) throws UserPrincipalNotFoundException {
        if (id==null || id<=0){
            throw new IllegalArgumentException("El id no cumple con los parametros");
        }
        User user;
        user = userRepository.findById(id).orElseThrow(()-> new UserPrincipalNotFoundException("no se halló registro con el id ingresado"));
        return user;}

    public User patchUpdate(Long id, User partialUpdate) throws UserPrincipalNotFoundException {
        User existingUser = userRepository.findById(id).orElseThrow(()-> new UserPrincipalNotFoundException("Usuario no encontrado"));
        if (partialUpdate.getEstado() != null) {
            existingUser.setEstado(partialUpdate.getEstado());
        }
        return userRepository.save(existingUser);
    }

    public User softdelete(Long id) throws UserPrincipalNotFoundException {
        User existingUser = userRepository.findById(id).orElseThrow(()-> new UserPrincipalNotFoundException("registro no encontrado con ese id ingresado"));
        existingUser.setEstado(false);
        return userRepository.save(existingUser);
    }

}
