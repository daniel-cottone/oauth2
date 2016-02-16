package com.brahalla.oauthprovider.repository;

import com.brahalla.oauthprovider.domain.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  public User findByUsername(String username);

}
