package com.haiph.userservice.service;

import com.haiph.userservice.entity.person.User;
import com.haiph.userservice.model.request.user.UserCreate;
import com.haiph.userservice.model.request.user.UserUpdate;
import com.haiph.userservice.model.response.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<User> findAll();

    Page<UserDTO> findAllPage(Pageable pageable);

    UserDTO findById(UUID userId);

    UserDTO findByUsername(String username);

    UserDTO findByEmail(String email);

    User create(UserCreate userCreate);

    User update(UserUpdate userUpdate);

    void deleteById(UUID userId);
}
