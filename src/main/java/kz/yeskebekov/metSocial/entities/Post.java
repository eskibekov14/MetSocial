package kz.yeskebekov.metSocial.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String postText;
    @CreationTimestamp
    private Timestamp postDate;
    @ManyToOne(fetch = FetchType.EAGER)
    private Users users;
}
