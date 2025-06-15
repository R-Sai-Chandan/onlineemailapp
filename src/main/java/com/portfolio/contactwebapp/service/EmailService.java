package com.portfolio.contactwebapp.service;

import com.portfolio.contactwebapp.model.ContactForm;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmailService {
	@Value("${spring.mail.username}")
	private String Email;
@Autowired
private JavaMailSender mailSender;

public boolean sendEmailWithAttachment(ContactForm form, MultipartFile file) {
    try {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(form.getEmail());
        helper.setSubject("Auto-reply: " + form.getSubject());

        String autoReplyBody = String.format(
            "Hello Sir,\n\n" +
            "Thank you for reaching out to our customer support with the following details:\n\n" +
            "Email: %s\n" +
            "Subject: %s\n" +
            "Message:\n%s\n\n" +
            "Our team will contact you shortly.\n\n" +
            "This is an auto-generated message. Please do not reply to this email.",
            form.getEmail(),
            form.getSubject(),
            form.getBody()
        );

        helper.setText(autoReplyBody);

        if (!file.isEmpty()) {
            helper.addAttachment(file.getOriginalFilename(), file);
        }

        mailSender.send(message);
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

}