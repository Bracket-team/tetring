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

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class StoreDataInit {

    private final GameRepository gameRepository;
    private final StoreRepository storeRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
//        Optional<Game> testGame = gameRepository.findGameByPlayerId(123L);
//        Game game = testGame.get();
        // Store 엔티티 생성 및 저장
        Game game = new Game(12, 12000, true, true, new Player(122L, "son", "213412@gmail.com"));
        Store store = new Store(game, 0, 10, 1);
        storeRepository.save(store);
    }
}
