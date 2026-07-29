package com.example.Auth.demo.Controller;

import com.example.Auth.demo.DTO.LoginRequest;
import com.example.Auth.demo.DTO.RegisterRequest;
import com.example.Auth.demo.DTO.UserResponse;
import com.example.Auth.demo.Model.User;
import com.example.Auth.demo.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginRequest request) {

        return userService.login(request);
    }

    @PostMapping("/signup")
    public UserResponse userCreation(@Valid @RequestBody RegisterRequest request){
        User user = userService.saveUser(request);

        UserResponse response = new UserResponse();

        response.setUserId(user.getId());
        response.setUsername(user.getName());
        response.setEmail(user.getEmail());
        return response;
    }

    @GetMapping("/profile")
    public String profile(Authentication authentication) {
        return authentication.getName();
    }

    @GetMapping("/public")
    public String publicFunc() {
        return "public";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
