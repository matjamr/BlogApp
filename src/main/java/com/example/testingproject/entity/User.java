package com.example.testingproject.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NotNull
    String name;
    @NotNull
    String surname;
    String description;
    @NotNull
    @Column(unique = true)
    String email;

    @NotNull
    LocalDateTime dateOfAccountCreation;
    LocalDateTime dateOfLastLogin;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    List<Post> posts;


    @PrePersist
    void prePersist() {
        dateOfAccountCreation = LocalDateTime.now();
    }


    public void updateDateOfLastLogin() {
        dateOfLastLogin = LocalDateTime.now();
    }

}


