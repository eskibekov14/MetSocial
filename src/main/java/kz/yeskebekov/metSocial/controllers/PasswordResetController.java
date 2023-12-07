package kz.yeskebekov.metSocial.controllers;

import kz.yeskebekov.metSocial.entities.Users;
import kz.yeskebekov.metSocial.repositories.UsersRepository;
import kz.yeskebekov.metSocial.services.EmailService;
import kz.yeskebekov.metSocial.services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/temp-password")
public class PasswordResetController {
    @Autowired
    private MyUserService userService;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/{userEmail}")
    public ResponseEntity<String> resetPassword(@PathVariable(name = "userEmail") String email){
        Users user = usersRepository.findAllByEmail(email);
        if(user!=null){
            String tempPassword = userService.generateTempPassword();
            userService.updatePassword(user.getEmail(), tempPassword);
            emailService.sendTempPassword(email, tempPassword);
            return ResponseEntity.ok("Временный пароль отправлен на Вашу почту");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователь не найден!");
        }
    }

}
