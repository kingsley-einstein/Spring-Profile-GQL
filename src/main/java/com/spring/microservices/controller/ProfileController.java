package com.spring.microservices.controller;

import com.spring.microservices.model.Profile;
import com.spring.microservices.repository.ProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
public class ProfileController {
  @Autowired
  private ProfileRepository repo;

  @PostMapping("/profile")
  public Profile createProfile(@RequestBody Profile body) {
    Profile profile = new Profile(
      body.getFirstName(),
      body.getLastName(),
      body.getUserId()
    );
    return repo.save(profile);
  }

  @PutMapping("/profile/{id}")
  public Profile editProfile(@RequestBody Profile body, @PathVariable("id") Long id) {
    Profile profile = repo.findById(id)
                          .get();
    if (body.getFirstName() != null) {
      profile.setFirstName(body.getFirstName());
    }
    if (body.getLastName() != null) {
      profile.setLastName(body.getLastName());
    }
    return repo.save(profile);
  }

  @GetMapping("/profile/byuser/{id}")
  public Profile getByUser(@PathVariable("id") Long id) {
    Profile profile = repo.findByUserId(id);
    return profile;
  }

  @GetMapping("/profile/{id}")
  public Profile getById(@PathVariable("id") Long id) {
    Profile profile = repo.findById(id)
                          .get();
    return profile;
  }
}