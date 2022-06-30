package com.dmitry.NewsClient.repository;

import java.util.List;
import java.util.UUID;

import com.dmitry.NewsClient.entity.UserEntity;
import lombok.NonNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUser extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String userDto);
    @NonNull
    List<UserEntity> findAll();
    UserEntity findById(UUID id);

    void deleteById(UUID aLong);
}
