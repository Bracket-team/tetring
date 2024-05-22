package bracket.tetring.domain.store.service;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.domain.Rarity;
import bracket.tetring.domain.game.domain.Relic;
import bracket.tetring.domain.game.repository.GameRepository;
import bracket.tetring.domain.game.repository.RelicRepository;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.domain.PlayerRelic;
import bracket.tetring.domain.player.domain.Role;
import bracket.tetring.domain.player.repository.PlayerRelicRepository;
import bracket.tetring.domain.player.repository.PlayerRepository;
import bracket.tetring.domain.store.domain.Store;
import bracket.tetring.domain.store.domain.StoreRelic;
import bracket.tetring.domain.store.dto.PurchaseRelicDto;
import bracket.tetring.domain.store.dto.RerollRelicsDto;
import bracket.tetring.domain.store.repository.StoreRelicRepository;
import bracket.tetring.domain.store.repository.StoreRepository;
import bracket.tetring.global.util.CalculateSystem;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class StoreRelicServiceTest {
    @Autowired
    private StoreRelicService storeRelicService;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreRelicRepository storeRelicRepository;
    @Autowired
    private RelicRepository relicRepository;
    @Autowired
    private PlayerRelicRepository playerRelicRepository;

    private Player player;
    private Game game;
    private Store store;

    @BeforeEach
    public void init() {
        player = new Player("송하준", "427shg@gmail.com", null, Role.USER);
        playerRepository.save(player);
        game = new Game(12, 2200L, true, false, player);
        gameRepository.save(game);
        store = new Store(game, 0, 22, 5);
        storeRepository.save(store);
        Relic relic = new Relic(1, "노을 사진", Rarity.NORMAL, 10, "하늘색, 노란색 블록 터트릴 때마다 +1 line score", null);
        Relic relic2 = new Relic(2, "T블록", Rarity.NORMAL, 5, "보라색 블록 터트릴 때마다 +1.5 line score", null);
        relicRepository.save(relic);
        relicRepository.save(relic2);
        for (int i = 3; i <= 30; i++) {
            relicRepository.save(new Relic(i, "ss", Rarity.ULTRA, 5, "test", null));
        }
        storeRelicRepository.save(new StoreRelic(store, relic, 1));
        storeRelicRepository.save(new StoreRelic(store, relic2, 2));
    }

    @DisplayName("유물 구입 시")
    @Test
    public void purchaseRelicTest() {
        //given

        //when
        PurchaseRelicDto purchaseRelicDto = storeRelicService.purchaseRelic(player.getPlayerId(), 1);
        List<PlayerRelic> playerRelics = playerRelicRepository.findAllByGameOrderBySlotNumberAsc(game);
        List<StoreRelic> storeRelics = storeRelicRepository.findAllByStoreOrderBySlotNumberAsc(store);

        //then
        assertThat(purchaseRelicDto.isCanBuy()).isTrue();
        assertThat(purchaseRelicDto.getLastMoney()).isEqualTo(12);
        assertThat(purchaseRelicDto.getPlayerRelic().getRelicNumber()).isEqualTo(1);
        assertThat(playerRelics).hasSize(1);
        assertThat(playerRelics.get(0).getSlotNumber()).isEqualTo(1);
        assertThat(storeRelics).hasSize(1);
    }

    @DisplayName("유물 리롤 시")
    @Test
    public void rerollRelic() {
        //given

        //when
        RerollRelicsDto rerollRelicsDto = storeRelicService.rerollRelics(player.getPlayerId());

        //then
        assertThat(rerollRelicsDto.isCanReroll()).isTrue();
        assertThat(rerollRelicsDto.getLastMoney()).isEqualTo(17);
        assertThat(rerollRelicsDto.getNextRerollPrice()).isEqualTo(10);
        assertThat(rerollRelicsDto.getRelics()).hasSize(2);

        RerollRelicsDto nextRelics = storeRelicService.rerollRelics(player.getPlayerId());

        assertThat(nextRelics.isCanReroll()).isTrue();
        assertThat(nextRelics.getLastMoney()).isEqualTo(7);
        assertThat(nextRelics.getNextRerollPrice()).isEqualTo(15);
        assertThat(nextRelics.getRelics()).hasSize(2);
    }
}