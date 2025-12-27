package com.hse.userflow.storingservice.repository;

import com.hse.userflow.storingservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
