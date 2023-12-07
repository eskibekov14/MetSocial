package kz.yeskebekov.metSocial.repositories;

import jakarta.transaction.Transactional;
import kz.yeskebekov.metSocial.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findAllByEmail(String email);
    Users findAllById(Long id);
    List<Users> findAllByFullNameContainsIgnoreCase(String name);
}
