package com.haiph.userservice.controller;

import com.haiph.salesservice.exception.Response;
import com.haiph.salesservice.exception.ResponseBody;
import com.haiph.userservice.entity.person.Employee;
import com.haiph.userservice.model.request.employee.EmployeeCreate;
import com.haiph.userservice.model.request.employee.EmployeeUpdate;
import com.haiph.userservice.model.request.user.UserCreate;
import com.haiph.userservice.model.request.user.UserUpdate;
import com.haiph.userservice.model.response.dto.EmployeeDTO;
import com.haiph.userservice.model.response.dto.PersonDTO;
import com.haiph.userservice.model.response.dto.UserDTO;

import com.haiph.userservice.entity.person.User;
import com.haiph.userservice.service.EmployeeService;
import com.haiph.userservice.service.PersonService;
import com.haiph.userservice.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/user")
@Validated
public class PersonController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    @Qualifier("personServiceImpl")
    private PersonService personService;


    @GetMapping("/findPer")
    public Page<PersonDTO> findAllPerson(Pageable pageable) {
        return personService.findAllPage(pageable);
    }

    @GetMapping("/findPerId/{id}")
    public PersonDTO findById(@PathVariable UUID id) {
        return personService.findById(id);
    }

    @GetMapping("/findPerCode/{id}")
    public PersonDTO findByPersonCode(@PathVariable String id) {
        return personService.findByPersonCode(id);
    }

    /*
     * User
     * */
    @GetMapping("/findUser")
    public ResponseEntity<ResponseBody> findAllUser() {
        return ResponseEntity.ok(
                new ResponseBody(Response.SUCCESS.getResponseCode()
                        , Response.SUCCESS.getResponseMessage(),
                        userService.findAll()
                ));
    }

    @GetMapping("/find-DTO-user")
    public ResponseEntity<ResponseBody> findAll(Pageable pageable) {
        return ResponseEntity.ok(
                new ResponseBody(Response.SUCCESS.getResponseCode()
                        , Response.SUCCESS.getResponseMessage(),
                        userService.findAllPage(pageable)
                ));
    }

    @GetMapping("/findUserId/{id}")
    public ResponseEntity<ResponseBody> findUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(
                new ResponseBody(Response.SUCCESS.getResponseCode()
                        , Response.SUCCESS.getResponseMessage(),
                        userService.findById(id)
                ));
    }

    @PostMapping("/create-user")
    public ResponseEntity<ResponseBody> createUser(@RequestBody @Valid UserCreate userCreate) {

        return ResponseEntity.ok(
                new ResponseBody(Response.SUCCESS.getResponseCode()
                        , Response.SUCCESS.getResponseMessage(),
                        userService.create(userCreate)
                ));
    }

    @PutMapping("/update-user")
    public ResponseEntity<ResponseBody> updateUser(@PathVariable UUID id, @RequestBody @Valid UserUpdate userUpdate) {
        userUpdate.setId(id);
        User update = userService.update(userUpdate);
        return ResponseEntity.ok(
                new ResponseBody(Response.SUCCESS.getResponseCode()
                        , Response.SUCCESS.getResponseMessage(),
                        update
                ));
    }

    @DeleteMapping("delete-user")
    public ResponseEntity<ResponseBody> deleteUserById(@PathVariable UUID id) {
        userService.deleteById(id);
        return ResponseEntity.ok(
                new ResponseBody(Response.SUCCESS.getResponseCode()
                        , Response.SUCCESS.getResponseMessage()
                ));
    }

    /*
    Employee
     */

    @GetMapping("/findEmpl")
    public ResponseEntity<ResponseBody> findAllEmployee() {
        return ResponseEntity.ok(
                new ResponseBody(Response.SUCCESS.getResponseCode()
                        , Response.SUCCESS.getResponseMessage(),
                        employeeService.findAll()
                ));
    }

    @GetMapping("/find-DTO-empl")
    public ResponseEntity<ResponseBody> findAllDTO(Pageable pageable) {
        return ResponseEntity.ok(
                new ResponseBody(Response.SUCCESS.getResponseCode()
                        , Response.SUCCESS.getResponseMessage(),
                        employeeService.findAllPage(pageable)
                ));
    }

    @GetMapping("/findEmplId/{id}")
    public ResponseEntity<ResponseBody> findEmployeeById(@PathVariable UUID id) {
        return ResponseEntity.ok(
                new ResponseBody(Response.SUCCESS.getResponseCode()
                        , Response.SUCCESS.getResponseMessage(),
                        employeeService.findById(id)
                ));
    }

    @PostMapping("/create-empl")
    public ResponseEntity<ResponseBody> createEmployee(@RequestBody @Valid EmployeeCreate employeeCreate) {
        return ResponseEntity.ok(
                new ResponseBody(Response.SUCCESS.getResponseCode()
                        , Response.SUCCESS.getResponseMessage(),
                        employeeService.create(employeeCreate)
                ));
    }

    @PutMapping("/update-empl/{id}")
    public ResponseEntity<ResponseBody> updateEmployee(@PathVariable UUID id, @RequestBody @Valid EmployeeUpdate employeeUpdate) {
        employeeUpdate.setId(id);
        Employee update = employeeService.update(employeeUpdate);
        return ResponseEntity.ok(
                new ResponseBody(Response.SUCCESS.getResponseCode()
                        , Response.SUCCESS.getResponseMessage(),
                        update
                ));
    }

    @DeleteMapping("delete-empl/{id}")
    public ResponseEntity<ResponseBody> deleteEmployeeById(@PathVariable UUID id) {
        employeeService.deleteById(id);
        return ResponseEntity.ok(
                new ResponseBody(Response.SUCCESS.getResponseCode()
                        , Response.SUCCESS.getResponseMessage()
                ));
    }

    @GetMapping("/active")
    public ResponseEntity<ResponseBody> activeAccount(@RequestParam("personcode") String personcode) {
        return ResponseEntity.ok(
                new ResponseBody(
                        Response.SUCCESS,
                        personService.activePerson(personcode)
                )
        );
    }

}