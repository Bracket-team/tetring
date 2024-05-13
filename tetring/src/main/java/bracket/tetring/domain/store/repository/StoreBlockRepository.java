package bracket.tetring.domain.store.repository;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.store.domain.Store;
import bracket.tetring.domain.store.domain.StoreBlock;
import bracket.tetring.domain.store.domain.StoreBlockId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreBlockRepository extends JpaRepository<StoreBlock, StoreBlockId> {
}
