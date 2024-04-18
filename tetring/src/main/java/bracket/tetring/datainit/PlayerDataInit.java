package bracket.tetring.datainit;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.domain.Rarity;
import bracket.tetring.domain.game.domain.Relic;
import bracket.tetring.domain.game.domain.Score;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
public class PlayerDataInit {

    private final PlayerRepository playerRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        Player testPlayer = new Player(123L, "송하준", "427shg@gmail.com");
        playerRepository.save(testPlayer);
    }
}
