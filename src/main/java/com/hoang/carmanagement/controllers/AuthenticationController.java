package com.hoang.carmanagement.controllers;

import com.hoang.carmanagement.models.ResponseObject;
import com.hoang.carmanagement.security.models.AuthenticationRequest;
import com.hoang.carmanagement.security.util.JwtUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BaseController.BASE_URL + "/auth/jwt")
public class AuthenticationController extends BaseController{

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/")
    public ResponseEntity<String> getJwtToken(@RequestBody AuthenticationRequest request, HttpServletResponse response){
        //Check user/pw
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtUtils.generateToken(userDetails);
        Cookie cookie = new Cookie("token",token);
        cookie.setHttpOnly(true);
        //cookie.setSecure(true);   //Enable this: Cookie only is transferred through HTTPS
        cookie.setPath("/");
        cookie.setMaxAge(7*24*60*60);
        response.addCookie(cookie);
        return ResponseEntity.ok(token);
    }
}
