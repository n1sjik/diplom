package ru.spb.cmt.doctorcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.spb.cmt.doctorcloud.entity.Person;
import ru.spb.cmt.doctorcloud.security.LoginStatus;
import ru.spb.cmt.doctorcloud.security.SecurityService;
import ru.spb.cmt.doctorcloud.security.UserLoginData;
import ru.spb.cmt.doctorcloud.service.PersonService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private SecurityService securityService;
    @Autowired
    @Qualifier("personAuthentication")
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private PersonService personService;

    @RequestMapping("/login")
    public LoginStatus login(HttpSession session,
                              @RequestBody UserLoginData userLoginData) {
        return securityService.login(authenticationProvider, session, userLoginData);
    }

    @RequestMapping("/me")
    public Person me(Principal principal) {
        return (Person) ((UsernamePasswordAuthenticationToken)principal).getPrincipal();
    }

    @RequestMapping("/list")
    public List<Person> getAll() {
        return personService.getAllPersons();
    }

    @RequestMapping("/save")
    public Person save(@RequestBody Person section) {
        return personService.save(section);
    }


    @RequestMapping("/remove")
    public void remove(@RequestParam long id) {
        personService.remove(id);
    }


}
