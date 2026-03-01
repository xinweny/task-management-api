package com.slic.task_management_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.slic.task_management_api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   User findByEmail(String email);
}
