package bracket.tetring.domain.game.service;

import bracket.tetring.domain.game.domain.BlockColor;
import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.domain.Rarity;
import bracket.tetring.domain.game.domain.Relic;
import bracket.tetring.domain.game.dto.GameDetailsDto;
import bracket.tetring.domain.game.repository.GameRepository;
import bracket.tetring.domain.game.repository.RelicRepository;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.domain.PlayerBlock;
import bracket.tetring.domain.player.domain.PlayerRelic;
import bracket.tetring.domain.player.domain.Role;
import bracket.tetring.domain.player.repository.PlayerBlockRepository;
import bracket.tetring.domain.player.repository.PlayerRelicRepository;
import bracket.tetring.domain.player.repository.PlayerRepository;
import bracket.tetring.domain.store.domain.Store;
import bracket.tetring.domain.store.domain.StoreBlock;
import bracket.tetring.domain.store.domain.StoreRelic;
import bracket.tetring.domain.store.repository.StoreBlockRepository;
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
class GameStartServiceTest {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private RelicRepository relicRepository;
    @Autowired
    private PlayerBlockRepository playerBlockRepository;
    @Autowired
    private PlayerRelicRepository playerRelicRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreBlockRepository storeBlockRepository;
    @Autowired
    private StoreRelicRepository storeRelicRepository;
    @Autowired
    private GameStartService gameStartService;

    Player player;

    @BeforeEach
    public void initData() {
        player = new Player("송하준", "427shg@naver.com", null, Role.USER);
        playerRepository.save(player);
    }

    @DisplayName("사용자가 게임을 플레이 중일 때")
    @Test
    public void playingGameTest() {
        //given
        Game game = new Game(1, 0L, false, false, player);
        gameRepository.save(game);
        //when
        Boolean result = gameStartService.isPlayerGaming(player.getPlayerId());
        //then
        assertThat(result).isTrue();
    }

    @DisplayName("사용자가 게임을 플레이 중이 아닐 때")
    @Test
    public void notPlayingGameTest() {
        //given
        Game game = new Game(1, 0L, false, true, player);
        gameRepository.save(game);
        //when
        Boolean result = gameStartService.isPlayerGaming(player.getPlayerId());
        //then
        assertThat(result).isFalse();
    }

    @DisplayName("사용자가 새롭게 게임을 시작할 경우 게임 세부 정보")
    @Test
    public void newGameDetailTest() {
        //given

        //when
        GameDetailsDto result = gameStartService.getGameDetailsForPlayers(player.getPlayerId());

        //then
        assertThat(result.getPlayerBlock()).hasSize(49);
        assertThat(result.getPlayerRelic()).hasSize(0);
        assertThat(result.getStore().getMoney()).isEqualTo(5);
    }

    @DisplayName("사용자가 이미 게임을 하고 있는 경우 게임 세부 정보")
    @Test
    public void continueGameDetailTest() {
        //given
        Game game = new Game(5, 1200L, true, false, player);
        gameRepository.save(game);
        playerBlockRepository.save(new PlayerBlock(game, "0000011001100000", BlockColor.YELLOW));
        playerBlockRepository.save(new PlayerBlock(game, "0000011001100000", BlockColor.YELLOW));
        Relic relic = new Relic(1, "노을 사진", Rarity.NORMAL, 10, "하늘색, 노란색 블록 터트릴 때마다 +1 line score", null);
        Relic relic2 = new Relic(2, "T블록", Rarity.NORMAL, 5, "보라색 블록 터트릴 때마다 +1.5 line score", null);
        relicRepository.save(relic);
        relicRepository.save(relic2);
        playerRelicRepository.save(new PlayerRelic(game, relic, 1, null));
        Store store = new Store(game, 1, 13, 2);
        storeRepository.save(store);
        storeRelicRepository.save(new StoreRelic(store, relic2, 1));
        storeBlockRepository.save(new StoreBlock(1, store, "0000011001100000", BlockColor.YELLOW));

        //when
        GameDetailsDto result = gameStartService.getGameDetailsForPlayers(player.getPlayerId());

        //then
        assertThat(result.getPlayerBlock()).hasSize(2);
        assertThat(result.getPlayerRelic()).hasSize(1);
        assertThat(result.getStore().getMoney()).isEqualTo(13);
        assertThat(result.getStoreBlock()).hasSize(1);
        assertThat(result.getStoreRelic()).hasSize(1);
    }
}