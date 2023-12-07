package kz.yeskebekov.metSocial.controllers;

import kz.yeskebekov.metSocial.entities.AddToFriendBid;
import kz.yeskebekov.metSocial.entities.AddToFriendBidDto;
import kz.yeskebekov.metSocial.entities.Users;
import kz.yeskebekov.metSocial.services.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/bids")
@RequiredArgsConstructor
public class BidController {
    @Autowired
    private BidService bidService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/{userId}")
    public List<AddToFriendBidDto> getAllBids(@PathVariable(name = "userId") Long userId){
        return bidService.getBids(userId);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public boolean addToFriend(@RequestBody Users user){
        return bidService.createBid(user);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping
    public boolean acceptBid(@RequestBody AddToFriendBid bid){
        return bidService.acceptBid(bid);
    }
}
