package com.assesment.userManagement.persistence.repository;

import com.assesment.userManagement.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserName(String userName);
    UserEntity findByEmail(String emailId);

    Optional<UserEntity> findById(Long id);
}
