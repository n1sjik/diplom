package ru.spb.cmt.doctorcloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    @RequestMapping("/login")
    public LoginStatus login(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                             @RequestBody UserLoginData user) {
        return securityService.login(request, response, session, user);
    }

}
