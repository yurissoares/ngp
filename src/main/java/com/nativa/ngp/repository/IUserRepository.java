package com.nativa.ngp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nativa.ngp.entity.UserEntity;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

	public UserEntity findByNome(String nome);

	public boolean existsByEmail(String email);

	public Optional<UserEntity> findByEmail(String username);
	
}
