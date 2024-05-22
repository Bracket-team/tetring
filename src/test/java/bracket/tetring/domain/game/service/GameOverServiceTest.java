package bracket.tetring.domain.game.service;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.dto.GameOverDto;
import bracket.tetring.domain.game.repository.GameRepository;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.domain.Role;
import bracket.tetring.domain.player.repository.PlayerRepository;
import bracket.tetring.domain.store.domain.Store;
import bracket.tetring.domain.store.repository.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class GameOverServiceTest {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private GameOverService gameOverService;

    private Player player;

    @BeforeEach
    public void initData() {
        player = new Player("송하준", "427shg@gmail.com", null, Role.USER);
        playerRepository.save(player);

    }

    @DisplayName("게임이 오버되었을 경우")
    @Test
    public void gameOverTest() {
        //given
        Game game = new Game(5, 1600L, false, false, player);
        gameRepository.save(game);
        Store store = new Store(game, 0, 23, 3);
        storeRepository.save(store);

        //when
        GameOverDto gameResult = gameOverService.getGameResult(player.getPlayerId());

        //then
        assertThat(gameResult.getBestScore()).isEqualTo(1600L);
        assertThat(gameResult.getStageNumber()).isEqualTo(5);
        assertThat(gameResult.getBlockCount()).isEqualTo(0);
        assertThat(gameResult.getMoney()).isEqualTo(23);
        assertThat(gameResult.getRelics()).isEmpty();
        assertThat(game.getCheckFinished()).isTrue();
    }

    @DisplayName("게임이 종료한 경우")
    @Test
    public void quitGame() {
        //given
        Game game = new Game(5, 1600L, false, false, player);
        gameRepository.save(game);
        Store store = new Store(game, 0, 23, 3);
        storeRepository.save(store);

        //when
        GameOverDto gameResult = gameOverService.quitGame(player.getPlayerId());

        //then
        assertThat(gameResult.getBestScore()).isEqualTo(1600L);
        assertThat(gameResult.getStageNumber()).isEqualTo(5);
        assertThat(gameResult.getBlockCount()).isEqualTo(0);
        assertThat(gameResult.getMoney()).isEqualTo(23);
        assertThat(gameResult.getRelics()).isEmpty();
        assertThat(game.getCheckFinished()).isTrue();
    }
}