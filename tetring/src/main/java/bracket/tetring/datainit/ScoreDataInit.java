package bracket.tetring.datainit;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.domain.Rarity;
import bracket.tetring.domain.game.domain.Relic;
import bracket.tetring.domain.game.domain.Score;
import bracket.tetring.domain.game.repository.GameRepository;
import bracket.tetring.domain.game.repository.RelicRepository;
import bracket.tetring.domain.game.repository.ScoreRepository;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Optional;

@RequiredArgsConstructor
public class ScoreDataInit {

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final ScoreRepository scoreRepository;



    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        Optional<Player> testPlayer = playerRepository.findById(123L);
        Optional<Game> testGame = gameRepository.findByPlayer(testPlayer.get());
        Score testScore = new Score(testGame.get(), testPlayer.get(), 200, 12);
        scoreRepository.save(testScore);

    }
}
