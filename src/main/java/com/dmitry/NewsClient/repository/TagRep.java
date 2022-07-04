package com.dmitry.NewsClient.repository;

import com.dmitry.NewsClient.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRep extends JpaRepository<Tag, Long> {

}
