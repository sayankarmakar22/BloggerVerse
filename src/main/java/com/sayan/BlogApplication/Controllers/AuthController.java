package com.sayan.BlogApplication.Controllers;

import com.sayan.BlogApplication.DTO.AuthorAuthenticationRequest;
import com.sayan.BlogApplication.Security.JwtProvider;
import com.sayan.BlogApplication.Services.Implementations.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure-app")
public class AuthController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/token-authors")
    public ResponseEntity<String> generateTokenForAuthor(@RequestBody AuthorAuthenticationRequest authorAuthenticationRequest){
        String username = authorAuthenticationRequest.getUsername();
        String password = authorAuthenticationRequest.getPassword();

        String token = null;
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(passwordEncoder.matches(password,userDetails.getPassword())){
            token = jwtProvider.generateToken(username);
        }
        else {
            throw new RuntimeException("user not valid !!");
        }
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }
    @GetMapping("/token-viewer")
    public ResponseEntity<String> generateTokenForViewer(@RequestBody AuthorAuthenticationRequest authorAuthenticationRequest){
        String username = authorAuthenticationRequest.getUsername();
        String password = authorAuthenticationRequest.getPassword();

        String token = null;
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(passwordEncoder.matches(password,userDetails.getPassword())){
            token = jwtProvider.generateToken(username);
        }
        else {
            throw new RuntimeException("user not valid !!");
        }
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }
}
