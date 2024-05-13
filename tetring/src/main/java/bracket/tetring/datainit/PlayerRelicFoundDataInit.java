package bracket.tetring.datainit;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.domain.Rarity;
import bracket.tetring.domain.game.domain.Relic;
import bracket.tetring.domain.game.domain.Score;
import bracket.tetring.domain.game.repository.GameRepository;
import bracket.tetring.domain.game.repository.RelicRepository;
import bracket.tetring.domain.game.repository.ScoreRepository;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.domain.PlayerRelicFound;
import bracket.tetring.domain.player.repository.PlayerRelicFoundRepository;
import bracket.tetring.domain.player.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Optional;

@RequiredArgsConstructor
public class PlayerRelicFoundDataInit {

    private final PlayerRepository playerRepository;
    private final RelicRepository relicRepository;
    private final PlayerRelicFoundRepository playerRelicFoundRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        Optional<Player> testPlayer = playerRepository.findById(123L);
        Optional<Relic> firstRelic = relicRepository.findById(1);
        playerRelicFoundRepository.save(new PlayerRelicFound(testPlayer.get(), firstRelic.get(), true));
    }
}
