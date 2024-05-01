package com.evan.user_service.repository;

import com.evan.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("jpa")
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
//    public void deleteByEmail(String email);
}
