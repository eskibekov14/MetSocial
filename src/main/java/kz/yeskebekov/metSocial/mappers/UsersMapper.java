package kz.yeskebekov.metSocial.mappers;

import kz.yeskebekov.metSocial.entities.Friend;
import kz.yeskebekov.metSocial.entities.Users;
import kz.yeskebekov.metSocial.entities.UsersDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    UsersDTO mapToUserDTO(Users users);
    List<UsersDTO> mapToUSerDTOList(List<Users> usersList);


}
