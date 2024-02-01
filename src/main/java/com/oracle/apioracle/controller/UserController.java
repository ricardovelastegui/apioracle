package com.oracle.apioracle.controller;

import com.oracle.apioracle.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import com.oracle.apioracle.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.oracle.apioracle.entities.User;

import java.util.Map;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("new")
    public ResponseEntity<?> save(@RequestBody User user) {

        try {
            User curso = userService.save(user);
            return new ResponseEntity<>(curso, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getByid(@PathVariable Long id){
        User user = userService.getById(id);
        if (user != null){
            return new ResponseEntity<>(user,HttpStatus.OK);
        } else {return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);}
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<?> parcialUpdateUser(@PathVariable Long id, @RequestBody Map<String, Object> updates){
        try{
            userService.parcialUpdateId(id, (String)updates.get("name"), (Long) updates.get("age"));
            return new ResponseEntity<>("exitosamente modificado", HttpStatus.OK);
        } catch (Exception e){return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);}
    }

    @PutMapping("desactive/{id}")
    public ResponseEntity<?> desactivarUsuario(@PathVariable Long id, @RequestBody Map<String, Boolean> estadoupdate) {
        try {
            boolean activo = estadoupdate.get("activo");
            userService.desactivarById(id, activo);
            if (activo) {
                return new ResponseEntity<>("registro ya activado", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("registro desactivado", HttpStatus.OK);
            }

        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

}


