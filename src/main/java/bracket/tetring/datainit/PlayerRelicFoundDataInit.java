package bracket.tetring.datainit;

import bracket.tetring.domain.game.domain.Relic;
import bracket.tetring.domain.game.repository.RelicRepository;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.domain.PlayerRelicFound;
import bracket.tetring.domain.player.repository.PlayerRelicFoundRepository;
import bracket.tetring.domain.player.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
public class PlayerRelicFoundDataInit {

    private final PlayerRepository playerRepository;
    private final RelicRepository relicRepository;
    private final PlayerRelicFoundRepository playerRelicFoundRepository;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        Player testPlayer = playerRepository.findByName("송하준").orElseThrow(
                () -> new NoSuchElementException("송하준 플레이어가 없습니다.")
        );
        Relic firstRelic = relicRepository.findById(1).orElseThrow(
                () -> new NoSuchElementException("1번 유물이 존재하지 않습니다.")
        );
        Relic secondRelic = relicRepository.findById(2).orElseThrow(
                () -> new NoSuchElementException("2번 유물이 존재하지 않습니다.")
        );
        playerRelicFoundRepository.save(new PlayerRelicFound(testPlayer, firstRelic));
        playerRelicFoundRepository.save(new PlayerRelicFound(testPlayer, secondRelic));
    }
}
