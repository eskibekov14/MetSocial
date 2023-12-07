package kz.yeskebekov.metSocial.repositories;

import jakarta.transaction.Transactional;
import kz.yeskebekov.metSocial.entities.AddToFriendBid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface BidRepository extends JpaRepository<AddToFriendBid, Long> {
    AddToFriendBid findAllById(Long id);
}
