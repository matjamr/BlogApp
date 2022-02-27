package com.example.testingproject.repository;

import com.example.testingproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlUserRepository extends JpaRepository<User, Integer>, UserRepository{
}
