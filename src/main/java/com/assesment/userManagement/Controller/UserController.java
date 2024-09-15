package com.assesment.userManagement.Controller;


import com.assesment.userManagement.models.UserDTO;
import com.assesment.userManagement.models.UserRequest;
import com.assesment.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    @Transactional
    public ResponseEntity<Long> registerUser(@RequestBody UserRequest userRequest){
            Long userId = userService.registerUser(userRequest);
            return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }

    @GetMapping
    public List<UserDTO> getAllUser(){
        return userService.getAllUser();
    }

    @PutMapping("/{id}")
    @Transactional
    public UserDTO updatePassword(@PathVariable Long id,@RequestParam String password){
        return userService.updatePassword(id, password);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deletedUserById(id);
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }


}
