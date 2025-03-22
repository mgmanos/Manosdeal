package com.deal.Manosdeal.controller;

import com.deal.Manosdeal.util.UserUtil;
import org.springframework.ui.Model;
import com.deal.Manosdeal.dto.RegisterRequest;
import com.deal.Manosdeal.model.User;
import com.deal.Manosdeal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    @Autowired
    UserService service;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registerui")
    public String register(Model model) {
        RegisterRequest request = new RegisterRequest();
        model.addAttribute("registerRequest", request);
        return "register";
    }

    @PostMapping("/registeruser")
    public String register(@ModelAttribute RegisterRequest request, RedirectAttributes redirectAttributes) {
        ResponseEntity<?> response = service.register(request);

        if (response.getStatusCode().is2xxSuccessful()) {
            redirectAttributes.addFlashAttribute("successMessage", "Registration successful! Please login.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Registration failed. Please try again.");
        }
        return "redirect:/registerui";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        User user = UserUtil.getCurrentUser();

        if (user == null) {
            return "redirect:/login"; // Redirect to login if user is not in session
        }

        model.addAttribute("user", user);
        return "dashboard";
    }

}