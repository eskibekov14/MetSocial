package kz.yeskebekov.metSocial.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
@Entity
@Table(name = "comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String commentText;
    @CreationTimestamp
    private Timestamp commentDate;
    @ManyToOne(fetch = FetchType.EAGER)
    private Users users;
    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;
}
