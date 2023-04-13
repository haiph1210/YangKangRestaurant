package com.haiph.userservice.repository.person;

import com.haiph.userservice.entity.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
    Optional<Person> findByPersonCode(String personCode);
    Optional<Person> findByUsername(String username);
    Optional<Person> findByEmail(String email);
    boolean existsByUsernameAndEmail(String username,String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
