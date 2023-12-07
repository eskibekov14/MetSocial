package kz.yeskebekov.metSocial.controllers;

import kz.yeskebekov.metSocial.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/photo")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @PostMapping
    public boolean uploadPhoto(@RequestBody MultipartFile file){
        return photoService.upLoadPhoto(file);
    }

}
