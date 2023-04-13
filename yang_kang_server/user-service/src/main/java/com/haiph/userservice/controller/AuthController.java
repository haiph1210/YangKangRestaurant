package com.haiph.userservice.controller;

import com.haiph.salesservice.exception.Response;
import com.haiph.salesservice.exception.ResponseBody;
import com.haiph.userservice.model.request.employee.EmployeeCreate;
import com.haiph.userservice.model.request.sercurity.AuthRequest;
import com.haiph.userservice.model.request.user.UserCreate;
import com.haiph.userservice.service.impl.sercurity.AuthService;
import jakarta.validation.Valid;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private AuthenticationManager authenticationManager;
//    @Autowired
//    private PersonService personService;

    @PostMapping("/registerUser")
    public ResponseEntity<ResponseBody> addNewUser(@RequestBody @Valid UserCreate userCreate) {
        return ResponseEntity.ok(
                new ResponseBody(
                        Response.SUCCESS.getResponseCode(),
                        Response.SUCCESS.getResponseMessage(),
                        authService.saveUser(userCreate)
                )
        );
    }

    @PostMapping("/registerEmpl")
    public ResponseEntity<ResponseBody> addNewEmpl(@RequestBody @Valid EmployeeCreate employeeCreate) {
        return ResponseEntity.ok(
                new ResponseBody(
                        Response.SUCCESS.getResponseCode(),
                        Response.SUCCESS.getResponseMessage(),
                        authService.saveEmpl(employeeCreate)
                )
        );
    }

    @PostMapping("/token")
    public String getToken(@RequestBody @Valid AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return authService.generateToken(authRequest.getUsername());
        } else
            throw new RuntimeException("Invalid Access");
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam String token) {
        authService.validateToken(token);
        return "Token is valid";
    }

    @GetMapping("/signin")
    public ResponseEntity<ResponseBody> signIn(@RequestBody @Valid AuthRequest authRequest) throws AuthenticationException {

        return ResponseEntity.ok(
                new ResponseBody(
                        Response.SUCCESS.getResponseCode(),
                        Response.SUCCESS.getResponseMessage(),
                        authService.login(authRequest)
                )
        );
    }
}
