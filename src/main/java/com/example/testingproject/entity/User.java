package com.example.testingproject.entity;

import lombok.Data;

import javax.persistence.*;

/*
TODO
 create new fields: description, account creation and post creation method
 */

@Entity
@Data
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
}


