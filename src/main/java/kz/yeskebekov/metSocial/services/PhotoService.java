package kz.yeskebekov.metSocial.services;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {
    boolean upLoadPhoto(MultipartFile file);
}
