package kz.yeskebekov.metSocial.repositories;

import jakarta.transaction.Transactional;
import kz.yeskebekov.metSocial.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface MessageRepository extends JpaRepository<Message, Long> {
}
