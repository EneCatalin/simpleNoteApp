package com.example.simpleNoteApp.controller;

import com.example.simpleNoteApp.dto.CreateUserDTO;
import com.example.simpleNoteApp.model.UserModel;
import com.example.simpleNoteApp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService usersService;

    public UserController(UserService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> users = usersService.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserModel> getUser(@PathVariable String userId) throws ResponseStatusException {
        UserModel userModel = usersService.getUserModel(userId);

        return new ResponseEntity<>(userModel, HttpStatus.OK);
    }

    @ExceptionHandler({ResponseStatusException.class})
    @PostMapping("/createUser")
    public ResponseEntity<UserModel> createUser(@RequestBody @Valid CreateUserDTO userDTO) throws Exception {

        return new ResponseEntity<>(usersService.createUser(userDTO), HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") String id) throws Exception {

        return new ResponseEntity<>(usersService.deleteUserEntity(id));

    }


}
