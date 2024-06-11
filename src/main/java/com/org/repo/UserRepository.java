package com.org.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.dto.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByEmailAndPassword(String email, String password);


}
