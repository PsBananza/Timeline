package com.dmitry.NewsClient.repository;

import com.dmitry.NewsClient.entity.NewsEntity;
import com.dmitry.NewsClient.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NewsRep extends JpaRepository<NewsEntity, UUID> {
    Page<NewsEntity> findByUser(Pageable pageable, UserEntity user);
}
