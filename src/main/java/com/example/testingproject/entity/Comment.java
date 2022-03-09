package com.example.testingproject.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String content;

    @ManyToOne()
    @JoinColumn(name = "post_id")
    Post post;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    User user;
}
