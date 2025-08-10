package server.board.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.board.domain.user.enums.Part;
import server.board.domain.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    List<User> findByPart(Part part);

    boolean existsByEmail(String email);
}
