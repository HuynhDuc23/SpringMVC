package com.rungroop.repository;

import com.rungroop.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity , Long> {
    public UserEntity findByEmail(String email);
    public UserEntity findByUsername(String username);
    public UserEntity findFirstByUsername(String username);
}
