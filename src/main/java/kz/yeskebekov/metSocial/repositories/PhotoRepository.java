package kz.yeskebekov.metSocial.repositories;

import jakarta.transaction.Transactional;
import kz.yeskebekov.metSocial.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
