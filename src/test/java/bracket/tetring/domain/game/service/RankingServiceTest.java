package bracket.tetring.domain.game.service;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.domain.Score;
import bracket.tetring.domain.game.dto.RankingDto;
import bracket.tetring.domain.game.repository.GameRepository;
import bracket.tetring.domain.game.repository.ScoreRepository;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.domain.Role;
import bracket.tetring.domain.player.repository.PlayerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RankingServiceTest {
    @Autowired
    private RankingService rankingService;
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameRepository gameRepository;

    @BeforeEach
    public void init() {
        Player player1 = new Player("송하준", "427shg@gmail.com", null, Role.USER);
        Player player2 = new Player("박성우", "49014ㅓ9@gmail.co", null, Role.USER);
        playerRepository.save(player1);
        playerRepository.save(player2);
        Game game1 = new Game(12, 1500L, false, true, player1);
        Game game2 = new Game(11, 1300L, false, true, player1);
        Game game3 = new Game(10, 1100L, false, true, player2);
        gameRepository.save(game1);
        gameRepository.save(game2);
        gameRepository.save(game3);
        Score score1 = new Score(game1, player1, 1500L, 12);
        Score score2 = new Score(game2, player1, 1300L, 11);
        Score score3 = new Score(game3, player2, 1100L, 10);
        scoreRepository.save(score2);
        scoreRepository.save(score3);
        scoreRepository.save(score1);
    }

    @DisplayName("랭킹 목록 가져오기")
    @Test
    public void getRankingList() {
        List<RankingDto> rankingList = rankingService.getRankings();

        Assertions.assertThat(rankingList).hasSize(3);
        Assertions.assertThat(rankingList.get(0).getScore()).isEqualTo(1500L);
        Assertions.assertThat(rankingList.get(1).getScore()).isEqualTo(1300L);
        Assertions.assertThat(rankingList.get(2).getScore()).isEqualTo(1100L);
    }
}