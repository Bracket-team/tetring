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
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class GameDataInit {

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        Player testPlayer = playerRepository.findByName("송하준").orElseThrow(
                () -> new NoSuchElementException("송하준 플레이어가 없습니다.")
        );
        Game testGame = new Game(2, 1200L, false, false, testPlayer);
        gameRepository.save(testGame);
    }
}
