package bracket.tetring.datainit;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.repository.GameRepository;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.repository.PlayerRepository;
import bracket.tetring.domain.store.domain.Store;
import bracket.tetring.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class StoreDataInit {

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final StoreRepository storeRepository;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        Player testPlayer = playerRepository.findByName("송하준").orElseThrow(
                () -> new NoSuchElementException("송하준 플레이어가 없습니다.")
        );
        Game testGame = gameRepository.findPlayingGameByPlayerId(testPlayer.getPlayerId()).orElseThrow(
                () -> new NoSuchElementException("플레이 중인 게임은 없습니다. id: 123")
        );
        Store store = new Store(testGame, 0, 10, 1);
        storeRepository.save(store);
    }
}
