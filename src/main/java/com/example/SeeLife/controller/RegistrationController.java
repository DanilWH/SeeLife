package com.example.SeeLife.controller;

import java.util.Collections;
import java.util.Map;

import com.example.SeeLife.dto.CaptchaResponseDto;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.SeeLife.CommonOperations;
import com.example.SeeLife.model.Role;
import com.example.SeeLife.model.User;
import com.example.SeeLife.repository.UserRepo;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

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
            @Valid User user,
            BindingResult bindingResult,
            @RequestParam String password_confirm,
            @RequestParam(name="g-recaptcha-response") String gRecaptchaResponse,
            Model model
    ) {
        String url = String.format(CAPTCHA_URL, this.captchaSecret, gRecaptchaResponse);
        CaptchaResponseDto responseDto = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);

        // check if the reCAPTCHA filled.
        if (!responseDto.isSuccess()) {
            model.addAttribute("captchaError", "Fill captcha!");
        }

        // check if the password_confirm isn't blank.
        if (Strings.isBlank(password_confirm)) {
            model.addAttribute("password_confirmError", "The field must not be blank!");
        }

        // check if the password and confirm_password match.
        boolean passwordsMatches = user.getPassword() != null && user.getPassword().equals(password_confirm);
        if (!passwordsMatches) {
            model.addAttribute("passwordError", "Password don't match!");
        }

        // check if the user already exists.
        boolean userExists = this.userRepo.findByUsername(user.getUsername()) != null;
        if (userExists) {
            model.addAttribute("usernameError", "The user already exists!");
        }

        // check all the errors. Get all the errors and return back to the registration page if there is any error.
        if (!responseDto.isSuccess() || Strings.isBlank(password_confirm) ||
            !passwordsMatches || userExists || bindingResult.hasErrors())
        {
            Map<String, String> errorsMap = ControllersUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);

            return "registration";
        }


        // if everything is fine, save the user into the database.
        User newUser = new User();

        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRoles(Collections.singleton(Role.USER));
        newUser.setActive(true);
        
        this.userRepo.save(newUser);
        
        return "redirect:/";
    }
}
