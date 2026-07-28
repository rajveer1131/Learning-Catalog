package com.example.Auth.demo.Service;

import com.example.Auth.demo.DTO.LoginRequest;
import com.example.Auth.demo.DTO.RegisterRequest;
import com.example.Auth.demo.DTO.UserResponse;
import com.example.Auth.demo.Model.Role;
import com.example.Auth.demo.Model.User;
import com.example.Auth.demo.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    public UserService(UserRepository userRepository , PasswordEncoder passwordEncoder,JWTService jwtService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public User saveUser(RegisterRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("User Already Exists");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        String hashedPass = passwordEncoder.encode(request.getPassword());
        user.setPassword(hashedPass);
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    public boolean userEmailExists(String email){
        boolean exists = userRepository.existsByEmail(email);
        if(!exists){
            throw new IllegalArgumentException("Email Does not Exists. Recheck email and try again");
        }
        return true;
    }

    public UserResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid credentials"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        UserResponse response = new UserResponse();

        response.setUserId(user.getId());
        response.setUsername(user.getName());
        response.setEmail(user.getEmail());
        response.setToken(jwtService.generateToken(user));

        return response;
    }




}
