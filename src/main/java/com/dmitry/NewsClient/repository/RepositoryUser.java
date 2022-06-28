package com.dmitry.NewsClient.repository;

import com.dmitry.NewsClient.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUser extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String userDto);
}
