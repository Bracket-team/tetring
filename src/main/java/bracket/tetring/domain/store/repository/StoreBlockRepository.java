package bracket.tetring.domain.store.repository;

import bracket.tetring.domain.store.domain.Store;
import bracket.tetring.domain.store.domain.StoreBlock;
import bracket.tetring.domain.store.domain.StoreBlockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StoreBlockRepository extends JpaRepository<StoreBlock, StoreBlockId> {
    List<StoreBlock> findAllByStoreOrderBySlotNumberAsc(Store store);

    @Query("select sb from StoreBlock sb where sb.store = :store and sb.slotNumber = :slotNumber")
    Optional<StoreBlock> findByStoreAndSlotNumber(@Param("store") Store store, @Param("slotNumber") Integer slotNumber);
}
