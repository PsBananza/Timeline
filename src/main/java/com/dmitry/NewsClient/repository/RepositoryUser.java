package com.dmitry.NewsClient.repository;

import java.util.UUID;

import com.dmitry.NewsClient.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUser extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT DISTINCT u FROM UserEntity u WHERE email = :email")
    UserEntity findByEmail(String email);

    @Query(value = "SELECT DISTINCT u FROM UserEntity u WHERE id = :id")
    UserEntity findById(UUID id);

}
