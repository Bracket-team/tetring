package bracket.tetring.domain.store.repository;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.store.domain.Store;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StoreRepositoryTest {

    @Autowired
    private StoreRepository storeRepository;
//
//    @Test
//    void saveStoreTest() {
////        Player testPlayer = new Player(123L, "admin", "427shg@gmali.com");
////        Game testGame = new Game(UUID.randomUUID(), 1, 100, false, true, testPlayer);
////        Store store = new Store(testGame, 2, 10, 2);
////        storeRepository.save(store);
////        Optional<Store> findStore = storeRepository.findById(testGame.getGameId());
////        assertThat(findStore).isPresent();
//////        findStore.ifPresent(foundStore -> assertThat(foundStore).isEqualToComparingFieldByField(store));
//    }
}