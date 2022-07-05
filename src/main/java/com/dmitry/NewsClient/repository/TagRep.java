package com.dmitry.NewsClient.repository;

import com.dmitry.NewsClient.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRep extends JpaRepository<Tag, Long> {

    Tag findByTitle(String s);

}
