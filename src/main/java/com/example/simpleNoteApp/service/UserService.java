package com.example.simpleNoteApp.service;

import com.example.simpleNoteApp.constants.Constants;
import com.example.simpleNoteApp.dto.CreateUserDTO;
import com.example.simpleNoteApp.entity.UserEntity;
import com.example.simpleNoteApp.model.UserModel;
import com.example.simpleNoteApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {



    private final UserRepository userRepository;

    /**
     * As  of Spring 4.3, classes with a single constructor can omit the @Autowired annotation. A nice little bit of
     * convenience and boilerplate removal!
     * ALSO SEE http://olivergierke.de/2013/11/why-field-injection-is-evil/
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel getUserModel(String userId) {
        return toModel(this.getUserEntity(userId));
    }

    public List<UserModel> getAllUsers() {

        return userRepository.findAll().stream().map(this::toModel).collect(Collectors.toList());
    }


    public UserModel createUser(CreateUserDTO userModel)
    {
        return toModel(userRepository.save(fromCreateUserDTO(userModel)));

    }

    public HttpStatus deleteUserEntity(String userId) {

        Optional<UserEntity> optionalUsersEntity = userRepository.findById(userId);

        if (optionalUsersEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, Constants.USER_NOT_FOUND);
        }

        userRepository.deleteById(userId);
        return HttpStatus.NO_CONTENT;

    }

    private UserEntity getUserEntity(String userId) throws ResponseStatusException {
        Optional<UserEntity> userEntity = userRepository.findById(userId);

        return userEntity.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.USER_NOT_FOUND));
    }


    private UserModel toModel(UserEntity userEntity) {
        return new UserModel(userEntity.getId(), userEntity.getUsername());
    }

    private UserEntity fromCreateUserDTO(CreateUserDTO userDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("USERNAME "+userDTO.username);
        System.out.println("PASSWORD "+userDTO.password);
        String hashedPassword = encoder.encode(Constants.SALT +userDTO.password);

        return new UserEntity(userDTO.username, hashedPassword);
    }





}
