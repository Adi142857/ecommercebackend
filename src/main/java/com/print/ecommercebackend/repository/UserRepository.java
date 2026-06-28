package com.print.ecommercebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.print.ecommercebackend.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}