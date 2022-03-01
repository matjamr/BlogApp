package com.example.testingproject.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NotNull
    String title;
    @NotNull
    String content;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    User user;
}
