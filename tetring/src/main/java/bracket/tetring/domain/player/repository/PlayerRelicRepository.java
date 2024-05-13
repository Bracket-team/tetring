package bracket.tetring.domain.player.repository;

import bracket.tetring.domain.player.domain.PlayerRelic;
import bracket.tetring.domain.player.domain.PlayerRelicId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRelicRepository extends JpaRepository<PlayerRelic, PlayerRelicId> {
}
