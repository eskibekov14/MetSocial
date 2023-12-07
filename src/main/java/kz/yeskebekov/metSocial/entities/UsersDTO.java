package kz.yeskebekov.metSocial.entities;

import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UsersDTO {
    private Long id;
    private String email;
    private String fullName;
    private int age;
    private String address;
    private String gender;
    private String about;
    private List<Photo> photos;
    private List<Friend> friends;
}
