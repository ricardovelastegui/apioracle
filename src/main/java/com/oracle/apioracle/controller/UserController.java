package com.oracle.apioracle.controller;


import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import com.oracle.apioracle.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.oracle.apioracle.entities.User;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
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

    @GetMapping("/registros")
    public ResponseEntity<List<User>> getAllusers(){
        List<User> curso = userService.getAllusers();
        return new ResponseEntity<>(curso, HttpStatus.OK);
    }

    @GetMapping("/registros/{id}")
    public ResponseEntity<?> getByid(@PathVariable Long id) throws UserPrincipalNotFoundException {
        try{
            User user = userService.getById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (IllegalArgumentException e){return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (UserPrincipalNotFoundException e){return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch (Exception e){return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);}

    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> patchUpdate(@PathVariable Long id, @RequestBody User partialUpdate){
        try {
            User updateUser = userService.patchUpdate(id, partialUpdate);
            return new ResponseEntity<>(updateUser, HttpStatus.OK);
        } catch (UserPrincipalNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/softdelete/{id}")
    public ResponseEntity<?> softdelete(@PathVariable Long id){
        try{
            User user = userService.softdelete(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserPrincipalNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}


