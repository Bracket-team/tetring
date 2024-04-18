package bracket.tetring.domain.player.repository;

import bracket.tetring.domain.player.domain.PlayerBlockDeck;
import bracket.tetring.domain.player.domain.PlayerBlockDeckId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerBlockDeckRepository extends JpaRepository<PlayerBlockDeck, PlayerBlockDeckId> {
}
