package bracket.tetring.global.login.repository;


import bracket.tetring.domain.player.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByEmail(String email);
}
