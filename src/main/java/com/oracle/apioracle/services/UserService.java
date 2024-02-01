package com.oracle.apioracle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oracle.apioracle.entities.User;
import com.oracle.apioracle.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }
    public User getById(Long id){return userRepository.findById(id).orElse(null);}

    public void parcialUpdateId(Long id, String name, Long age){
        userRepository.parcialUpdateId(id,name,age);
    }
    public void desactivarById(Long id,boolean activo){
        userRepository.desactivarById(id, activo);
    }

}
