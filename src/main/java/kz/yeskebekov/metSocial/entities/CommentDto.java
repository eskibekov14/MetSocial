package kz.yeskebekov.metSocial.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String commentText;
    private Timestamp commentDate;
    private UsersDTO users;
    private PostDTO post;
}
