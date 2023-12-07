package kz.yeskebekov.metSocial.controllers;

import kz.yeskebekov.metSocial.entities.UsersDTO;
import kz.yeskebekov.metSocial.services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.StringReader;
import java.security.Principal;

@Controller
@RequestMapping(value = "/met")
public class PageController {
    @Autowired
    private MyUserService userService;

    @GetMapping(value = "/home")
    public String openHomePage(){
        return "Home";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping(value = "/login")
    public String openLoginPage(@RequestParam(required = false,name = "error") String error,
                                Model model){
        if(error!=null){
            model.addAttribute("error","Логин или пароль неправильный!");
        }
        return "Login";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/profile")
    public String openProfilePage(){
        return "Profile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/message")
    public String openMessagePage(){
        return "Message";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/chat/{friendId}")
    public String openChatPage(@PathVariable(name = "friendId") Long friendId,
                               Model model){
        model.addAttribute("friendId",friendId);
        return "Chat";
    }

    @GetMapping(value = "/friend-profile/{userId}")
    public String openFriendProfile(@PathVariable(name = "userId") Long userId,
                                    Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if(userService.getUserById(userId).getEmail().equals(username)){
            return "redirect:/met/profile";
        }
        model.addAttribute("userId",userId);
        return "FriendProfile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/notification")
    public String openNotificationPage(){
        return "Notification";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/friends-list")
    public String openFriendsListPage(){
        return "Friends_List";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping(value = "/sign-up")
    public String openSignUpPage(){
        return "SignUp";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping(value = "/reset-password-page")
    public String openResetPasswordPage(){
        return "Reset_Password";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/edit-profile")
    public String openEditProfilePage(){
        return "EditProfile";
    }
}
