package com.haiph.userservice.service;

import com.haiph.userservice.entity.person.Employee;
import com.haiph.userservice.model.request.employee.EmployeeCreate;
import com.haiph.userservice.model.request.employee.EmployeeUpdate;
import com.haiph.userservice.model.response.dto.EmployeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    List<Employee> findAll();

    Page<EmployeeDTO> findAllPage(Pageable pageable);

    EmployeeDTO findById(UUID id);

    Employee create(EmployeeCreate create);

    Employee update(EmployeeUpdate update);

    void deleteById(UUID id);
}
