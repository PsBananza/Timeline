package com.dmitry.NewsClient.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class UserEntity {

    private String avatar;
    private String email;
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String role;
    private String password;

}
