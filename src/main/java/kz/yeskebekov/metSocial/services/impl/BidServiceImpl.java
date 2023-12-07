package kz.yeskebekov.metSocial.services.impl;

import kz.yeskebekov.metSocial.entities.AddToFriendBid;
import kz.yeskebekov.metSocial.entities.AddToFriendBidDto;
import kz.yeskebekov.metSocial.entities.Users;
import kz.yeskebekov.metSocial.mappers.UsersMapper;
import kz.yeskebekov.metSocial.repositories.BidRepository;
import kz.yeskebekov.metSocial.repositories.UsersRepository;
import kz.yeskebekov.metSocial.services.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BidServiceImpl implements BidService {
    @Autowired
    private BidRepository bidRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersMapper usersMapper;


    @Override
    public List<AddToFriendBidDto> getBids(Long userId) {
        List<AddToFriendBidDto> add = new ArrayList<>();
        for(AddToFriendBid add1 : bidRepository.findAll()){
            if(add1.getUsersList().get(1).getId()==userId){
                if(!add1.isStatus()) {
                    add.add(new AddToFriendBidDto(add1.getId(),usersMapper.mapToUSerDTOList(add1.getUsersList()),add1.isStatus()));
                }
            }
        }
        return add;
    }

    @Override
    public boolean createBid(Users users) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if(username!=null && usersRepository.findAllById(users.getId())!=null){
            List<Users> usersList = new ArrayList<>();
            usersList.add(usersRepository.findAllByEmail(username));
            usersList.add(usersRepository.findAllById(users.getId()));
            AddToFriendBid add = AddToFriendBid.builder()
                    .status(false)
                    .usersList(usersList).build();
            if(bidRepository.save(add)!=null){
                return true;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }

    @Override
    public boolean acceptBid(AddToFriendBid add) {
        AddToFriendBid bid = bidRepository.findAllById(add.getId());
        if(bid!=null){
            Users sender = usersRepository.findAllById(bid.getUsersList().get(0).getId());
            Users receiver = usersRepository.findAllById(bid.getUsersList().get(1).getId());
            List<Users> senderFriends = sender.getFriends();
            senderFriends.add(receiver);
            sender.setFriends(senderFriends);
            List<Users> receiverFriends = receiver.getFriends();
            receiverFriends.add(sender);
            receiver.setFriends(receiverFriends);
            usersRepository.save(sender);
            usersRepository.save(receiver);
            bid.setStatus(true);
            bidRepository.save(bid);
            return true;
        }else {
            return false;
        }
    }


}
