package com.example.testingproject.repository.sql;

import com.example.testingproject.entity.User;
import com.example.testingproject.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlUserRepository extends JpaRepository<User, Integer>, UserRepository {
}
