package kz.yeskebekov.metSocial.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "messages")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String messageText;
    private String uniqChatTopic;
    @ManyToOne(fetch = FetchType.EAGER)
    private Users sender;
    @ManyToOne(fetch = FetchType.EAGER)
    private Users receiver;
    @CreationTimestamp
    private Timestamp messageDate;

}
