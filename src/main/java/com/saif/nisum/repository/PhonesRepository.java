package com.saif.nisum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saif.nisum.model.Phones;

@Repository
public interface PhonesRepository extends JpaRepository<Phones, Integer> {

}
