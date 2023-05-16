package com.infosys.ecomerse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.ecomerse.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
