package bracket.tetring.datainit;

import bracket.tetring.domain.game.domain.BlockColor;
import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.domain.Relic;
import bracket.tetring.domain.game.repository.GameRepository;
import bracket.tetring.domain.game.repository.RelicRepository;
import bracket.tetring.domain.player.domain.PlayerBlockDeck;
import bracket.tetring.domain.player.domain.PlayerRelic;
import bracket.tetring.domain.player.repository.PlayerBlockDeckRepository;
import bracket.tetring.domain.player.repository.PlayerRelicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Optional;

@RequiredArgsConstructor
public class PlayerRelicDataInit {

    private final GameRepository gameRepository;
    private final RelicRepository relicRepository;
    private final PlayerRelicRepository playerRelicRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        Optional<Game> testGame = gameRepository.findGameByPlayerId("123");
        Optional<Relic> firstRelic = relicRepository.findById(1);
        playerRelicRepository.save(new PlayerRelic(testGame.get(), firstRelic.get(), 1));
    }
}
