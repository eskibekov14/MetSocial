package kz.yeskebekov.metSocial.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Friend {
    private Long id;
    private String email;
    private String fullName;
    private int age;
    private String address;
    private String gender;
    private String about;
    private List<Photo> photos;

}
