package kz.yeskebekov.metSocial.services;

import kz.yeskebekov.metSocial.entities.Permission;
import kz.yeskebekov.metSocial.entities.Users;
import kz.yeskebekov.metSocial.entities.UsersDTO;
import kz.yeskebekov.metSocial.mappers.UsersMapper;
import kz.yeskebekov.metSocial.repositories.PermissionRepository;
import kz.yeskebekov.metSocial.repositories.UsersRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class MyUserService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = usersRepository.findAllByEmail(email);
        if(users==null){
            throw new UsernameNotFoundException("User not found");
        }
        return users;
    }

    public String signUp(Users signUpUser){
        String flag = "userExist";
        Users checkUser = usersRepository.findAllByEmail(signUpUser.getEmail());
        if(checkUser==null){
                List<Permission> permissions = new ArrayList<>();
                permissions.add(permissionRepository.findAllByRole("ROLE_USER"));
                String gender = signUpUser.getGender();
                Users users = Users.builder()
                        .email(signUpUser.getEmail())
                        .fullName(signUpUser.getFullName())
                        .age(signUpUser.getAge())
                        .address(signUpUser.getAddress())
                        .gender(gender.equals("Male")? "Male" : (gender.equals("Female")? "Female" : null))
                        .about(signUpUser.getAbout())
                        .permissions(permissions)
                        .password(passwordEncoder.encode(signUpUser.getPassword())).build();
                usersRepository.save(users);
                flag = "registeredSuccess";
        }
        return flag;
    }

    public UsersDTO updateUser(Users user){
        Users users = usersRepository.findAllByEmail(user.getEmail());
        if(users!=null){
            users.setFullName(user.getFullName());
            users.setAge(user.getAge());
        }
        return usersMapper.mapToUserDTO(usersRepository.save(users));
    }
    public void updatePassword(String email, String password){
        Users user = usersRepository.findAllByEmail(email);
        if(user!=null){
            user.setPassword(passwordEncoder.encode(password));
            usersRepository.save(user);
        }
    }

    public String generateTempPassword(){
        return RandomStringUtils.randomAlphanumeric(8);
    }

    public List<Users> getAllUsers(String userName){
        return usersRepository.findAllByFullNameContainsIgnoreCase(userName);
    }

    public Users getUserById(Long userId){
        return usersRepository.findAllById(userId);
    }

    public List<UsersDTO> getFriends(String email){
        List<UsersDTO> friends = usersMapper.mapToUSerDTOList(usersRepository.findAllByEmail(email).getFriends());
        return friends;
    }

    public boolean isFriend(Long friendId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        for(Users u : usersRepository.findAllByEmail(username).getFriends()){
            if(u.getId()==friendId){
                return true;
            }
        }
        return false;
    }
}
