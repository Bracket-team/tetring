package bracket.tetring.domain.store.service;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.domain.Rarity;
import bracket.tetring.domain.game.domain.Relic;
import bracket.tetring.domain.game.repository.GameRepository;
import bracket.tetring.domain.game.repository.RelicRepository;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.domain.Role;
import bracket.tetring.domain.player.repository.PlayerRepository;
import bracket.tetring.domain.store.domain.Store;
import bracket.tetring.domain.store.domain.StoreRelic;
import bracket.tetring.domain.store.dto.LevelUpMoneySystemDto;
import bracket.tetring.domain.store.dto.StoreDetailsDto;
import bracket.tetring.domain.store.repository.StoreRelicRepository;
import bracket.tetring.domain.store.repository.StoreRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StoreServiceTest {

    @Autowired
    private StoreService storeService;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private RelicRepository relicRepository;
    @Autowired
    private StoreRelicRepository storeRelicRepository;

    private Player player;
    private Game game;

    @BeforeEach
    public void init() {
        player = new Player("송하준", "427shg@gmail.com", null, Role.USER);
        playerRepository.save(player);
        game = new Game(12, 2000L, false, false, player);
        gameRepository.save(game);
        Store store = new Store(game, 0, 22, 1);
        storeRepository.save(store);
        Relic relic = new Relic(1, "노을 사진", Rarity.NORMAL, 10, "하늘색, 노란색 블록 터트릴 때마다 +1 line score", null);
        Relic relic2 = new Relic(2, "T블록", Rarity.NORMAL, 5, "보라색 블록 터트릴 때마다 +1.5 line score", null);
        relicRepository.save(relic);
        relicRepository.save(relic2);
        for (int i = 3; i <= 30; i++) {
            relicRepository.save(new Relic(i, "ss", Rarity.ULTRA, 5, "test", null));
        }
        storeRelicRepository.save(new StoreRelic(store, relic, 1));
    }

    @DisplayName("상점 정보 가져오기(처음 들어오는 경우)")
    @Test
    public void getStoreDetailTest() {
        //given

        //when
        StoreDetailsDto storeInfo = storeService.getStoreInfo(player.getPlayerId());

        //then
        assertThat(storeInfo.getStoreBlocks()).hasSize(3);
        assertThat(storeInfo.getStoreRelics()).hasSize(2);
        assertThat(storeInfo.getStore().getMoney()).isEqualTo(22);
    }

    @DisplayName("상점 정보 가져오기(기존에 있는 경우)")
    @Test
    public void getStoreDetailContinueTest() {
        //given
        game.setIsStore(true);

        //when
        StoreDetailsDto storeInfo = storeService.getStoreInfo(player.getPlayerId());

        //then
        assertThat(storeInfo.getStoreBlocks()).isEmpty();
        assertThat(storeInfo.getStoreRelics()).hasSize(1);
        assertThat(storeInfo.getStore().getMoney()).isEqualTo(22);
    }

    @DisplayName("플레이어 돈 가져오기")
    @Test
    public void getMoneyTest() {
        //given

        //when
        Integer playerMoney = storeService.getPlayerMoney(player.getPlayerId());

        //then
        assertThat(playerMoney).isEqualTo(22);
    }

    @DisplayName("레벨 업 머니 시스템")
    @Test
    public void levelUpMoneySystemTest() {
        //given

        //when
        LevelUpMoneySystemDto levelUpMoneySystemDto = storeService.levelUpMoneySystem(player.getPlayerId());

        //then
        assertThat(levelUpMoneySystemDto.getLastMoney()).isEqualTo(17);
        assertThat(levelUpMoneySystemDto.getLevelUpPrice()).isEqualTo(10);
        assertThat(levelUpMoneySystemDto.isCanBuy()).isTrue();
    }
}