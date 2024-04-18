package bracket.tetring.domain.store.repository;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.store.domain.Store;
import bracket.tetring.domain.store.domain.StoreRelic;
import bracket.tetring.domain.store.domain.StoreRelicId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRelicRepository extends JpaRepository<StoreRelic, StoreRelicId> {
}
