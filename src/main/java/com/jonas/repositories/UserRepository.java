package com.jonas.repositories;

import com.jonas.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <UserEntity, Long> {
	UserEntity findByEmail(String email);
}
