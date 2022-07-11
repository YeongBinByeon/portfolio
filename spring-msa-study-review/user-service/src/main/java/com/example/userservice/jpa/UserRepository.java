package com.example.userservice.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    @Query("select u from UserEntity u where u.userId=:userId")
    UserEntity findByUserId(String userId);

    UserEntity findByEmail(String username);
}
