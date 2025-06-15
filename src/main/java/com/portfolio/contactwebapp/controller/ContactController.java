package com.portfolio.contactwebapp.controller;
import com.portfolio.contactwebapp.model.ContactForm;
import com.portfolio.contactwebapp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ContactController {

@Autowired
private EmailService emailService;

@GetMapping("/contact")
public String contact(Model model) {
    model.addAttribute("contactForm", new ContactForm());
    return "contact";
}

@PostMapping("/sendEmail")
public String sendEmail(@ModelAttribute ContactForm contactForm,
                        @RequestParam("attachment") MultipartFile file,
                        Model model) {
    boolean success = emailService.sendEmailWithAttachment(contactForm, file);
    model.addAttribute("message", success ? "Email sent successfully!" : "Failed to send email.");
    return "contact";
}}