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
public class GameDataInit {

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        Optional<Player> testPlayer = playerRepository.findById(123L);
        Game testGame = new Game(1, 1200, true, true, testPlayer.get());
        gameRepository.save(testGame);
    }
}
