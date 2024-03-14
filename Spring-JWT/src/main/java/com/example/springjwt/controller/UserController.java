package com.example.springjwt.controller;

import com.example.springjwt.dto.OrderDTO;
import com.example.springjwt.dto.UserResponse;
import com.example.springjwt.model.AuthRequest;
import com.example.springjwt.model.UserInfo;
import com.example.springjwt.repository.UserInfoRepository;
import com.example.springjwt.service.JWTService;
import com.example.springjwt.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserInfoService userInfoService;
    private final JWTService jwtService;
    private final UserInfoRepository userInfoRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return userInfoService.addUser(userInfo);
    }

    @GetMapping("/user/userProfile/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public UserResponse userProfile(@PathVariable("id") Long id) {
        final var userExist = userInfoRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String api = "http://localhost:8080/api/v1/orders/" + id;
        RestTemplate restTemplate = new RestTemplate();
        OrderDTO[] orderDTOS = restTemplate.getForObject(api, OrderDTO[].class);
        assert orderDTOS != null;

        return UserResponse.builder()
                .userId(id)
                .name(userExist.getName())
                .orderDTOS(List.of(orderDTOS))
                .build();
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/login")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
