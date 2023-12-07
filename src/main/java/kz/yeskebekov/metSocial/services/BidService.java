package kz.yeskebekov.metSocial.services;

import kz.yeskebekov.metSocial.entities.AddToFriendBid;
import kz.yeskebekov.metSocial.entities.AddToFriendBidDto;
import kz.yeskebekov.metSocial.entities.Users;

import java.util.List;

public interface BidService {
    List<AddToFriendBidDto> getBids(Long userId);
    boolean createBid(Users users);
    boolean acceptBid(AddToFriendBid add);
}
