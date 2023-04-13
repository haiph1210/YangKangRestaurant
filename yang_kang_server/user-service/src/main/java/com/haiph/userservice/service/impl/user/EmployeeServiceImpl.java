package com.haiph.userservice.service.impl.user;

import com.haiph.userservice.entity.bank.BankUser;
import com.haiph.userservice.entity.person.Employee;
import com.haiph.userservice.entity.person.Person;
import com.haiph.userservice.model.request.employee.EmployeeCreate;
import com.haiph.userservice.model.request.employee.EmployeeUpdate;
import com.haiph.userservice.model.response.dto.BankUserDTO;
import com.haiph.userservice.model.response.dto.EmployeeDTO;
import com.haiph.userservice.repository.bank.BankUserRepository;
import com.haiph.userservice.repository.person.EmployeeRepository;
import com.haiph.userservice.repository.person.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class EmployeeServiceImpl implements com.haiph.userservice.service.EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private BankUserRepository bankUserRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
    private BankUserDTO findBankedUser(String personCode) {
        Optional<BankUser> bankUser = bankUserRepository.findByPersonCode(personCode);
        BankUserDTO bankUserDTO = new BankUserDTO();
        if (bankUser.isPresent()) {
            bankUserDTO.setBankUserNumber(bankUser.get().getBankUserNumber());
            bankUserDTO.setBankName(bankUser.get().getBankName());
            bankUserDTO.setUserOwenrName(bankUser.get().getUserOwenrName());
            bankUserDTO.setCountPrice(bankUser.get().getCountPrice());
        }
        return bankUserDTO;
    }
    @Override
    public Page<EmployeeDTO> findAllPage(Pageable pageable) {
        Page<Employee> page = employeeRepository.findAll(pageable);
        List<Employee> employees = page.getContent();
        List<EmployeeDTO> dtos = new ArrayList<>();

        for (Employee employee : employees) {
            BankUserDTO bankUser = findBankedUser(employee.getPersonCode());
            EmployeeDTO dto = new EmployeeDTO();
            if (employee!= null) {
                dto.setPersonCode(employee.getPersonCode());
                dto.setEmail(employee.getEmail());
                dto.setPhoneNumber(employee.getPhoneNumber());
                dto.setAddress(employee.getAddress());
                dto.setGender(employee.getGender());
                dto.setFullName(employee.getFullName());
                dto.setRole(employee.getRole());
                dto.setBank(bankUser);
                dto.setCreatedDate(employee.getCreatedDate());
                dto.setPosition(employee.getPosition());
                dto.setSalary(employee.getSalary());
                dtos.add(dto);
            }
        }
        return new PageImpl<>(dtos,pageable, page.getTotalElements());
    }

    @Override
    public EmployeeDTO findById(UUID id) {
       Employee employee = employeeRepository.findById(id).orElse(null);
        BankUserDTO bankUser = findBankedUser(employee.getPersonCode());
        EmployeeDTO dto = new EmployeeDTO();
        if (employee!= null) {
            dto.setPersonCode(employee.getPersonCode());
            dto.setEmail(employee.getEmail());
            dto.setPhoneNumber(employee.getPhoneNumber());
            dto.setAddress(employee.getAddress());
            dto.setGender(employee.getGender());
            dto.setFullName(employee.getFullName());
            dto.setRole(employee.getRole());
            dto.setBank(bankUser);
            dto.setCreatedDate(employee.getCreatedDate());
            dto.setPosition(employee.getPosition());
            dto.setSalary(employee.getSalary());
        }
        return dto;
    }

    public EmployeeDTO findByPersonCode(String personCode) {
        Employee employee = employeeRepository.findByPersonCode(personCode).orElse(null);
        BankUserDTO bankUser = findBankedUser(employee.getPersonCode());
        EmployeeDTO dto = new EmployeeDTO();
        if (employee!= null) {
            dto.setPersonCode(employee.getPersonCode());
            dto.setEmail(employee.getEmail());
            dto.setPhoneNumber(employee.getPhoneNumber());
            dto.setAddress(employee.getAddress());
            dto.setGender(employee.getGender());
            dto.setFullName(employee.getFullName());
            dto.setRole(employee.getRole());
            dto.setBank(bankUser);
            dto.setCreatedDate(employee.getCreatedDate());
            dto.setPosition(employee.getPosition());
            dto.setSalary(employee.getSalary());
        }
        return dto;
    }

    @Override
    public Employee create(EmployeeCreate create) {
        Employee employee = mapper.map(create, Employee.class);
        String fullName = create.getFirstName() + " " + create.getLastName();
        employee.setPersonCode(generatePersonCode(fullName));
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(EmployeeUpdate update) {
        Employee employee = mapper.map(update, Employee.class);
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(UUID id) {
        employeeRepository.deleteById(id);
    }

    private static final int MAX_ATTEMPTS = 100;

    private String generatePersonCode(String fullName) {
        String temp = Normalizer.normalize(fullName, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String output = pattern.matcher(temp).replaceAll("").replace('đ', 'd').replace('Đ', 'D');
        String[] newFullName = output.split(" ");
        String newPersonCode = "";
        int number = 1;
        if (newFullName.length > 0) {
            String lastName = newFullName[newFullName.length - 1].toUpperCase();
            String firstName = newFullName[0].substring(0, 2).toUpperCase();
            newPersonCode = lastName + firstName + "-";
            int attempts = 0;
            while (attempts < MAX_ATTEMPTS) {
                Optional<Person> person = personRepository.findByPersonCode(newPersonCode + number);
                if (!person.isPresent()) {
                    newPersonCode += number;
                    break;
                }
                number++;
                attempts++;
            }
        }
        return newPersonCode;
    }
}
