package com.saif.nisum.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saif.nisum.model.UserDTO;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, String> {

	Optional<UserDTO> findByEmail(String email);
}
