package kz.yeskebekov.metSocial.controllers;

import kz.yeskebekov.metSocial.entities.Friend;
import kz.yeskebekov.metSocial.entities.Users;
import kz.yeskebekov.metSocial.entities.UsersDTO;
import kz.yeskebekov.metSocial.mappers.UsersMapper;
import kz.yeskebekov.metSocial.services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UsersController {
    @Autowired
    private MyUserService userService;
    @Autowired
    private UsersMapper usersMapper;

    @GetMapping(value = "/{userName}")
    public List<UsersDTO> getUsers(@PathVariable(name = "userName") String userName){
        return usersMapper.mapToUSerDTOList(userService.getAllUsers(userName));
    }

    @GetMapping(value = "/friend/{userId}")
    public UsersDTO getUser(@PathVariable(name = "userId") Long userId){
        return usersMapper.mapToUserDTO(userService.getUserById(userId));
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/friends-list")
    public List<UsersDTO> getFriends(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userService.getFriends(username);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/is-friend/{friendId}")
    public boolean isFriend(@PathVariable("friendId") Long friendId){
        return userService.isFriend(friendId);
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping
    public String signUpUser(@RequestBody Users users){
        return userService.signUp(users);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping
    public UsersDTO updateUser(@RequestBody Users users){
        return userService.updateUser(users);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping(value = "/password")
    public void updatePassword(@RequestBody Users users){
        userService.updatePassword(users.getEmail(), users.getPassword());
    }
}
