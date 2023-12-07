package kz.yeskebekov.metSocial.services.impl;

import kz.yeskebekov.metSocial.entities.Photo;
import kz.yeskebekov.metSocial.entities.Users;
import kz.yeskebekov.metSocial.repositories.PhotoRepository;
import kz.yeskebekov.metSocial.repositories.UsersRepository;
import kz.yeskebekov.metSocial.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service

public class PhotoServiceImpl implements PhotoService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PhotoRepository photoRepository;
    @Override
    public boolean upLoadPhoto(MultipartFile file) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        try {
            Users users = usersRepository.findAllByEmail(username);
            Photo photo = Photo.builder()
                    .name(username + users.getPhotos().size() + ".png")
                    .build();
            byte[] bytes = file.getBytes();
            Path path = Paths.get("build/resources/main/static/photos/" ,username + users.getPhotos().size() + ".png");
            Files.write(path,bytes);
            photoRepository.save(photo);
            users.getPhotos().add(photo);
            usersRepository.save(users);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
