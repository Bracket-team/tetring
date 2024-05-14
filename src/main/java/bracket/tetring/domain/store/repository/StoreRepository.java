package bracket.tetring.domain.store.repository;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByGame(Game game);
}
