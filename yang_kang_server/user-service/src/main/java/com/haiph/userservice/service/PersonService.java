package com.haiph.userservice.service;

import com.haiph.userservice.entity.person.Person;
import com.haiph.userservice.model.response.dto.PersonDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;
import java.util.UUID;

public interface PersonService extends UserDetailsService {
//    List<Person> findAll();

    Page<PersonDTO> findAllPage(Pageable pageable);

    PersonDTO findById(UUID personId);

    PersonDTO findByPersonCode(String personCode);

    Optional<Person> findByUsername(String username);


    String activePerson(String personcode);
}
