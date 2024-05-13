package bracket.tetring.domain.store.repository;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.store.domain.Store;
import bracket.tetring.domain.store.domain.StoreBlock;
import bracket.tetring.domain.store.domain.StoreRelic;
import bracket.tetring.domain.store.domain.StoreRelicId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StoreRelicRepository extends JpaRepository<StoreRelic, StoreRelicId> {
    List<StoreRelic> findAllByStoreOrderBySlotNumberAsc(Store store);

    @Query("select sr from StoreRelic sr where sr.store = :store and sr.slotNumber = :slotNumber")
    Optional<StoreRelic> findByStoreAndSlotNumber(@Param("store") Store store, @Param("slotNumber") Integer slotNumber);
}
