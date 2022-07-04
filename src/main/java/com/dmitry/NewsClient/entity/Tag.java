package com.dmitry.NewsClient.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tags")
@Accessors(chain = true)
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String title;
    @ManyToOne(optional=false, cascade=CascadeType.ALL)
    @JsonIgnore
    private NewsEntity entity;
}
