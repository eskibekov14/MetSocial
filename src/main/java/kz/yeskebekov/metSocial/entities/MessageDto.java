package kz.yeskebekov.metSocial.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {
    private Long id;
    private String messageText;
    private String uniqChatTopic;
    private UsersDTO sender;
    private UsersDTO receiver;
    private Timestamp messageDate;

}
