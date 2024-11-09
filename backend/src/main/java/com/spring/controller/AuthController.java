package com.spring.controller;

import com.spring.config.JwtProvider;
import com.spring.model.User;
import com.spring.repository.UserRepository;
import com.spring.request.LoginRequest;
import com.spring.response.AuthResponse;
import com.spring.service.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private CustomUserDetail customUserDetail;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {

        if (repository.findByEmail(user.getEmail()) != null) {
            throw new Exception("Email already exist!");
        }

        User newUser = new User();
        newUser.setPassword(encoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.setFullName(user.getFullName());

        repository.save(newUser);

        //Authentication
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = JwtProvider.generateToken(authentication);
        AuthResponse response = new AuthResponse();
        response.setMessage("Success");
        response.setJwt(jwt);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        //Authentication
        Authentication authentication = authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = JwtProvider.generateToken(authentication);
        AuthResponse response = new AuthResponse();
        response.setMessage("Success");
        response.setJwt(jwt);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Authentication authenticate(String email, String password) throws Exception {
        UserDetails userDetail = customUserDetail.loadUserByUsername(email);

        if (userDetail == null) {
            throw new Exception("User Not Found!");
        }

        if (!encoder.matches(password, userDetail.getPassword())) {
            throw new Exception("Wrong Password!");
        }

        return new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
    }
}
