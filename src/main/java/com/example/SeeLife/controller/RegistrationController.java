package com.example.SeeLife.controller;

import java.util.Collections;
import java.util.Map;

import com.example.SeeLife.dto.CaptchaResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.SeeLife.CommonOperations;
import com.example.SeeLife.model.Role;
import com.example.SeeLife.model.User;
import com.example.SeeLife.repository.UserRepo;
import org.springframework.web.client.RestTemplate;

@Controller
public class RegistrationController {
    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @Value("${captcha.secret.key}")
    private String captchaSecret;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
    
    @PostMapping("/registration")
    public String add_user(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String password_confirm,
            @RequestParam(name="g-recaptcha-response") String gRecaptchaResponse,
            Map<String, Object> model
    ) {
        String url = String.format(CAPTCHA_URL, this.captchaSecret, gRecaptchaResponse);
        CaptchaResponseDto responseDto = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);

        if (!responseDto.isSuccess()) {
            model.put("captchaError", "Fill captcha!");
            return "registration";
        }


        // check if the user already exists.
        if (this.userRepo.findByUsername(username) != null) {
            model.put("username_msg", "The user already exists!");
            System.out.println(model.get("username_msg"));
            return registration();
        }
        
        // check if the username and the password correct.
        model.put("username_msg", CommonOperations.isUsernameValid(username, userRepo));
        model.put("password_msg", CommonOperations.isPasswordValid(password, password_confirm));
        if (model.get("username_msg") != null || model.get("password_msg") != null)
            return registration();
        
        // if everything is fine, save the user into the database.
        User newUser = new User();

        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRoles(Collections.singleton(Role.USER));
        newUser.setActive(true);
        
        this.userRepo.save(newUser);
        
        return "redirect:/";
    }
}
