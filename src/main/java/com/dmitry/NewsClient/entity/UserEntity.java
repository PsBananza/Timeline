package com.dmitry.NewsClient.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Data
@Table(name = "users")
@Accessors(chain = true)
public class UserEntity {

    private String avatar;
    private String email;
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String role;
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<NewsEntity> news;

}
