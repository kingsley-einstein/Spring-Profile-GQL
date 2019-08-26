package com.spring.microservices.repository;

import com.spring.microservices.model.Profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
  public Profile findByUserId(Long userId);
}
