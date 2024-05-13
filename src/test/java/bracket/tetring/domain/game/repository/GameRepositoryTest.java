package bracket.tetring.domain.game.repository;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.player.domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GameRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GameRepository gameRepository;

    private UUID playerId;
    private Player player;
    private Game playingGame;

    @BeforeEach
    public void setUp() {
        // 플레이어 엔티티를 생성하고 저장
        player = new Player();
        player.setName("Test Player");
        player.setEmail("testplayer@example.com");
        entityManager.persist(player);
        playerId = player.getPlayerId();

        entityManager.flush();
    }

    @Test
    @DisplayName("플레이 중인 게임 찾기")
    public void whenFindPlayingGameByPlayerId_thenReturnPlayingGame() {
        // given
        playingGame = new Game(1, 0L, false, false, player);
        entityManager.persist(playingGame);
        entityManager.flush();

        // when
        Optional<Game> foundGame = gameRepository.findPlayingGameByPlayerId(playerId);

        // then
        assertThat(foundGame.isPresent()).isTrue();
        assertThat(foundGame.get().getCheckFinished()).isFalse();
        assertThat(foundGame.get().getPlayer().getPlayerId()).isEqualTo(playerId);
    }

    @Test
    @DisplayName("플레이 중인 게임 없을 시")
    public void noPlayingGameTest() {
        // given
        Game finishedGame = new Game(12, 1200L, false, true, player);
        entityManager.persist(finishedGame);
        entityManager.flush();

        // when
        Optional<Game> foundGame = gameRepository.findPlayingGameByPlayerId(playerId);

        // then
        assertThat(foundGame.isEmpty()).isTrue();
    }
}