package com.springboot.StoreApplicationApi.repositories;

import com.springboot.StoreApplicationApi.entities.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}