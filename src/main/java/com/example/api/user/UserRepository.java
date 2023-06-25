package com.example.api.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
	public Optional<UserEntity> findByName(String name);

}