package bracket.tetring.datainit;

import bracket.tetring.domain.game.domain.BlockColor;
import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.repository.GameRepository;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.domain.PlayerBlockDeck;
import bracket.tetring.domain.player.repository.PlayerBlockDeckRepository;
import bracket.tetring.domain.player.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class PlayerBlockDeckDataInit {

    private final GameRepository gameRepository;
    private final PlayerBlockDeckRepository playerBlockDeckRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        Optional<Game> testGame = gameRepository.findGameByPlayerId("123");
        playerBlockDeckRepository.save(new PlayerBlockDeck(testGame.get(), "0100010001000000", BlockColor.SKY));
    }
}
