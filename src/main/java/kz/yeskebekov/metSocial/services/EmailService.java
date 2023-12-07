package kz.yeskebekov.metSocial.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendTempPassword(String toEmail, String tempPassword){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Сброс пароля для аккаунта MetSocial");
        message.setText("Ваш временный пароль для доступа к аккаунту: " +
                tempPassword);
        javaMailSender.send(message);
    }
}
