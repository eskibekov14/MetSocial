package kz.yeskebekov.metSocial.repositories;

import jakarta.transaction.Transactional;
import kz.yeskebekov.metSocial.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findAllById(Long id);
}
