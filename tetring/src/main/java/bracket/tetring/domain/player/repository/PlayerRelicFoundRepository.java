package bracket.tetring.domain.player.repository;

import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.domain.PlayerRelicFound;
import bracket.tetring.domain.player.domain.PlayerRelicFoundId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRelicFoundRepository extends JpaRepository<PlayerRelicFound, PlayerRelicFoundId> {
    List<PlayerRelicFound> findByPlayer(Player player);
}
