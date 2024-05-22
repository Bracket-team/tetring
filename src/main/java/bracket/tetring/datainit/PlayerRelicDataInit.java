package bracket.tetring.datainit;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.domain.Relic;
import bracket.tetring.domain.game.repository.GameRepository;
import bracket.tetring.domain.game.repository.RelicRepository;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.domain.PlayerRelic;
import bracket.tetring.domain.player.repository.PlayerRelicRepository;
import bracket.tetring.domain.player.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
public class PlayerRelicDataInit {

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final RelicRepository relicRepository;
    private final PlayerRelicRepository playerRelicRepository;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        Player testPlayer = playerRepository.findByName("송하준").orElseThrow(
                () -> new NoSuchElementException("송하준 플레이어가 없습니다.")
        );
        Game testGame = gameRepository.findPlayingGameByPlayerId(testPlayer.getPlayerId()).orElseThrow(
                () -> new NoSuchElementException("플레이 중인 게임은 없습니다. id: " + testPlayer.getPlayerId())
        );
        Relic firstRelic = relicRepository.findById(1).orElseThrow(
                () -> new NoSuchElementException("1번 유물이 존재하지 않습니다.")
        );
        playerRelicRepository.save(new PlayerRelic(testGame, firstRelic, 1, null));
    }
}
