package com.dmitry.NewsClient.repository;

import com.dmitry.NewsClient.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRep extends JpaRepository<NewsEntity, Long> {
}
