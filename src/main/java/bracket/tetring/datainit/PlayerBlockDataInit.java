package bracket.tetring.datainit;

import bracket.tetring.domain.game.domain.BlockColor;
import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.repository.GameRepository;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.domain.PlayerBlock;
import bracket.tetring.domain.player.repository.PlayerBlockRepository;
import bracket.tetring.domain.player.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
public class PlayerBlockDataInit {

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final PlayerBlockRepository playerBlockRepository;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        Player testPlayer = playerRepository.findByName("송하준").orElseThrow(
                () -> new NoSuchElementException("송하준 플레이어가 없습니다.")
        );
        Game testGame = gameRepository.findPlayingGameByPlayerId(testPlayer.getPlayerId()).orElseThrow(
                () -> new NoSuchElementException("플레이 중인 게임은 없습니다. id: " + testPlayer.getPlayerId())
        );
        playerBlockRepository.save(new PlayerBlock(testGame, "0100010001000000", BlockColor.SKY));
        playerBlockRepository.save(new PlayerBlock(testGame, "0100011001000000", BlockColor.PURPLE));
    }
}
