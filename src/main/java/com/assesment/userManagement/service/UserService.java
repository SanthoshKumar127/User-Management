package com.assesment.userManagement.service;

import com.assesment.userManagement.CustomException.DuplicateEmailIdException;
import com.assesment.userManagement.CustomException.DuplicateUserNameException;
import com.assesment.userManagement.CustomException.UserNotFoundException;
import com.assesment.userManagement.models.Status;
import com.assesment.userManagement.models.UserDTO;
import com.assesment.userManagement.models.UserRequest;
import com.assesment.userManagement.persistence.entities.UserEntity;
import com.assesment.userManagement.persistence.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private ObjectMapper objectMapper;

    public Long registerUser(UserRequest userRequest){
        if(duplicateEmailId(userRequest.getEmailId())){
            throw new DuplicateEmailIdException("User already exist with this email Id "+ userRequest.getEmailId());
        }

        if(duplicateUserName(userRequest.getUserName())){
            throw new DuplicateUserNameException("User already exist with this user name "+ userRequest.getUserName());
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userRequest.getUserName());
        userEntity.setEmail(userRequest.getEmailId());
        userEntity.setRole(userRequest.getRole());
        userEntity.setStatus(Status.ACTIVE);
//        userEntity.setPassword(BCrypt.hashpw(userRequest.getPassword(), BCrypt.gensalt(10)));
        userEntity.setPassword(userRequest.getPassword());

        return userRepository.save(userEntity).getId();
    }

    public UserDTO getUserById(Long id){
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user not found with id : " + id));
        return objectMapper.convertValue(user, UserDTO.class);
    }

    public void deletedUserById(Long id){
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user not found with id : " + id));
        user.setStatus(Status.DELETE);
        userRepository.save(user);
    }

    public List<UserDTO> getAllUser(){
        List<UserEntity> userEntityList = userRepository.findAll();
        List<UserDTO> userDtoList = new ArrayList<>();
        for(UserEntity userEntity : userEntityList){
            userDtoList.add(objectMapper.convertValue(userEntity, UserDTO.class));
        }
        return userDtoList;
    }

    public UserDTO updatePassword(Long id, String password){
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user not found with id : " + id));
//        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(10)));
        user.setPassword(password);
        userRepository.save(user);
        return objectMapper.convertValue(user, UserDTO.class);
    }

    private boolean duplicateUserName(String userName){
        return Objects.nonNull(userRepository.findByUserName(userName));
    }


    private boolean duplicateEmailId(String emailId){
        return Objects.nonNull(userRepository.findByEmail(emailId));
    }


}
