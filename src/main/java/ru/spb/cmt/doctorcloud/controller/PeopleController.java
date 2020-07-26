package ru.spb.cmt.doctorcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import ru.spb.cmt.doctorcloud.entity.People;
import ru.spb.cmt.doctorcloud.security.LoginStatus;
import ru.spb.cmt.doctorcloud.security.SecurityService;
import ru.spb.cmt.doctorcloud.security.UserLoginData;
import ru.spb.cmt.doctorcloud.service.PeopleService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    @Qualifier("peopleAuthentication")
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private PeopleService peopleService;

    @RequestMapping("/login")
    public LoginStatus login(HttpSession session,
                              @RequestBody UserLoginData userLoginData) {
        return securityService.login(authenticationProvider, session, userLoginData);
    }

    @RequestMapping("/register")
    public People register(HttpSession session,
                           @RequestBody People people) {
        return peopleService.register(session, people);
    }

    @RequestMapping("/me")
    public People me(Principal principal) {
        return (People) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    }

    @RequestMapping("/visit")
    public boolean visitSection(Principal principal, @RequestParam(name = "id") long id) {
        if (!(principal instanceof UsernamePasswordAuthenticationToken) || !(((UsernamePasswordAuthenticationToken) principal).getPrincipal() instanceof People)) {
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        People people = (People) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        return peopleService.visitSection(people, id);
    }

    @RequestMapping("/join")
    public void joinSection(Principal principal, @RequestParam long id,
                            @RequestParam(defaultValue = "0") int status,
                            @RequestParam(defaultValue = "0") int isFee) {
        if (!(principal instanceof UsernamePasswordAuthenticationToken) || !(((UsernamePasswordAuthenticationToken) principal).getPrincipal() instanceof People)) {
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        People people = (People) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        peopleService.joinSection(people, id, status, isFee);
    }

    @RequestMapping("/list")
    public List<People> getAll() {
        return peopleService.getAllPeople();
    }

    @RequestMapping("/save")
    public People save(@RequestBody People people) {
        return peopleService.save(people);
    }

    @RequestMapping("/remove")
    public void remove(@RequestParam long id) {
        peopleService.remove(id);
    }


}
