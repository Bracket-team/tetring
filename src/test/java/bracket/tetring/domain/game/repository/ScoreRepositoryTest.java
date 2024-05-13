package bracket.tetring.domain.game.repository;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.domain.Score;
import bracket.tetring.domain.player.domain.Player;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
class ScoreRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ScoreRepository scoreRepository;

    Player player;
    Player player2;
    Game game;
    Game game2;
    Game game3;

    @BeforeEach
    public void setData() {
        player = new Player("Test Player", "test@example.com");
        entityManager.persist(player);

        player2 = new Player("player2", "428@gamil.com");
        entityManager.persist(player2);

        game = new Game(1, 100L, false, true, player);
        entityManager.persist(game);

        game2 = new Game(2, 1000L, false, true, player);
        entityManager.persist(game2);

        game3 = new Game(3, 1500L, false, true, player2);
        entityManager.persist(game3);

        Score score = new Score(game, player, 100L, 1);
        entityManager.persist(score);

        Score score2 = new Score(game2, player, 1000L, 2);
        entityManager.persist(score2);

        Score score3 = new Score(game3, player2, 1500L, 3);
        entityManager.persist(score3);
    }

    @Test
    @DisplayName("게임과 플레이어로 스코어 판에 있는지 확인")
    public void testExistsScoreByGameAndPlayer() {
        // when
        boolean exists = scoreRepository.existsScoreByGameAndPlayer(game, player);

        // then
        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("플레이어 랭킹 출력")
    public void testFindAllRankings() {

        // when
        List<Score> rankings = scoreRepository.findAllRankings();

        // then
        assertThat(rankings.get(0).getPlayer()).isEqualTo(player2);
        assertThat(rankings.get(0).getGame()).isEqualTo(game3);
        assertThat(rankings.get(1).getPlayer()).isEqualTo(player);
        assertThat(rankings.get(1).getGame()).isEqualTo(game2);
    }
}