package bracket.tetring.domain.player.repository;

import bracket.tetring.domain.player.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlayerRepository extends JpaRepository<Player, UUID> {
    Optional<Player> findByName(String name);

    Optional<Player> findByEmail(String email);
}
