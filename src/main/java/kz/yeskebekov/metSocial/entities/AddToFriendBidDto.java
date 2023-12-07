package kz.yeskebekov.metSocial.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddToFriendBidDto {
    private Long id;
    private List<UsersDTO> usersList;
    private boolean status;
}
