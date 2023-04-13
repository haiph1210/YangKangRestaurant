package com.haiph.userservice.service.impl.sercurity;

import com.haiph.salesservice.exception.CommonException;
import com.haiph.salesservice.exception.Response;
import com.haiph.userservice.entity.person.Employee;
import com.haiph.userservice.entity.person.Person;
import com.haiph.userservice.entity.person.User;
import com.haiph.userservice.model.request.employee.EmployeeCreate;
import com.haiph.userservice.model.request.sercurity.AuthRequest;
import com.haiph.userservice.model.request.user.UserCreate;
import com.haiph.userservice.model.response.sercurity.TokenResponse;
import com.haiph.userservice.repository.person.EmployeeRepository;
import com.haiph.userservice.repository.person.PersonRepository;
import com.haiph.userservice.repository.person.UserRepository;
import com.haiph.userservice.service.impl.Mail;
import jakarta.validation.Valid;
import org.apache.tomcat.websocket.AuthenticationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Optional;
import java.util.regex.Pattern;


@Service
public class AuthService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private EmailService emailService;

    public TokenResponse login(AuthRequest authRequest) throws AuthenticationException {
        Optional<Person> person = personRepository.findByUsername(authRequest.getUsername());
        if (!person.isPresent()) {
            throw new UsernameNotFoundException("User not valid : " + authRequest.getUsername());
        }
        String token = "";
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            if (authentication.isAuthenticated()) {
                token = generateToken(authRequest.getUsername());
            } else
                throw new AuthenticationException("Invalid Access");
            if (checkActive(authRequest.getUsername()) == false) {
                throw new CommonException(Response.ACCESS_DENIED,"User not active");
            }
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String JwtToken = token;
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return new TokenResponse(
                    JwtToken, userDetails.getUsername(), userDetails.getAuthorities().toString()); //,userDetails.getAuthorities().toString()
//        } catch (CommonException exception) {
//            System.out.println("exception = " + exception);
//            throw new CommonException(Response.ACCESS_DENIED,"Username or Password Invalid!!!");
        }catch (UsernameNotFoundException exception) {
            System.out.println("exception = " + exception);
            throw new UsernameNotFoundException("User not valid : " + authRequest.getUsername());
        }catch (AuthenticationException exception){
            throw new AuthenticationException("Invalid Access");
        }
    }

    private boolean checkActive(String username) {
        Optional<Person> person = personRepository.findByUsername(username);
        if (person.get().getStatus().equals(Person.Status.NOT_ACTIVE)) {
            return false;
        }
        return true;
    }

    public String saveUser(UserCreate create) {
        User user = mapper.map(create, User.class);
        Boolean exitsByName = personRepository.existsByUsername(create.getUsername());
        Boolean exitsByEmail = personRepository.existsByEmail(create.getEmail());
        if (exitsByName) {
            throw new CommonException(Response.PARAM_INVALID, "Username Invalid");
        } else
            user.setUsername(create.getUsername());
        if (exitsByEmail) {
            throw new CommonException(Response.PARAM_INVALID, "Email Invalid");
        }
        user.setEmail(create.getEmail());
        user.setStatus(Person.Status.NOT_ACTIVE);
        user.setPassword(passwordEncoder.encode(create.getPassword()));
        String fullName = create.getFirstName() + " " + create.getLastName();
        user.setPersonCode(generatePersonCode(fullName));
        userRepository.save(user);
        sendMai(create.getEmail());
        return "Add User Successfully";
    }

    public String saveEmpl( EmployeeCreate create) {
        Employee employee = mapper.map(create, Employee.class);
        Boolean exitsByName = personRepository.existsByUsername(create.getUsername());
        Boolean exitsByEmail = personRepository.existsByEmail(create.getEmail());
        if (exitsByName) {
            throw new CommonException(Response.PARAM_INVALID, "Username Invalid");
        } else
            employee.setUsername(create.getUsername());
        if (exitsByEmail) {
            throw new CommonException(Response.PARAM_INVALID, "Email Invalid");
        } else
            employee.setEmail(create.getEmail());
        employee.setStatus(Person.Status.NOT_ACTIVE);
        employee.setPassword(passwordEncoder.encode(create.getPassword()));
        String fullName = create.getFirstName() + " " + create.getLastName();
        employee.setPersonCode(generatePersonCode(fullName));
        employeeRepository.save(employee);
        return "Add Employee Successfully";
    }

    public String generateToken(String username) {
//        List<String> roles = Arrays.asList("USER", "ADMIN","EMPLOYEE");
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
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

    private String sendMai(String email) {

        Optional<Person> person = personRepository.findByEmail(email);

        if (person.isPresent()) {
            String confirm = Mail.CONFIRM + person.get().getPersonCode();
            StringBuilder message = new StringBuilder();
            message.append(confirm);
            String html = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "\n" +
                    "<style>\n" +
                    "    html{\n" +
                    "        background-color: rgba(255, 59, 9, 0.2); \n" +
                    "        box-sizing: border-box;\n" +
                    "        margin: 0px;\n" +
                    "        padding: 0px;\n" +
                    "        width: 1200px;\n" +
                    "    }\n" +
                    "    body{\n" +
                    "    }\n" +
                    "    .heading{\n" +
                    "        padding: 2rem 0rem 1.5rem 10rem;\n" +
                    "        font-size: 22px;\n" +
                    "        background-color: rgba(255, 59, 9, 0.2); \n" +
                    "        font-family: Arial, Helvetica, sans-serif;\n" +
                    "        color: brown;\n" +
                    "    }\n" +
                    "    .content{\n" +
                    "        padding: 2rem 0rem 0rem 10rem ;\n" +
                    "        font-size: 18px;\n" +
                    "        background-color: rgba(255, 59, 9, 0.2); \n" +
                    "        font-family: Arial, Helvetica, sans-serif;\n" +
                    "        color: brown;\n" +
                    "    }\n" +
                    "   \n" +
                    "</style>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +
                    "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ\" crossorigin=\"anonymous\">\n" +
                    "    <div class=\"heading\">" + Mail.SUBJECT + "</div>\n" +
                    "    <div class=\"content\"><p>" + Mail.MESSAGE + "</p>\n" +
                    "            <br> \n" +
                    "        <p>" + message + "</p>\n" +
                    "    </div>\n" +
                    "            \n" +
                    "</body>\n" +
                    "</html>";
            emailService.sendMail2(person.get().getEmail(), Mail.SUBJECT, html);
            return "Send mail to person: " + person.get().getPersonCode() + " success";
        }else
            return "Send mail to person: " + person.get().getPersonCode() + " fail";
    }
}
