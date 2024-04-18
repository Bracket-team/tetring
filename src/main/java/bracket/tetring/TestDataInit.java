package bracket.tetring;

import bracket.tetring.domain.game.domain.Rarity;
import bracket.tetring.domain.game.domain.Relic;
import bracket.tetring.domain.game.repository.RelicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
public class TestDataInit {

    private final RelicRepository relicRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        relicRepository.save(new Relic(1, "test", Rarity.RARE, 10, "sdfkajd", null));
        relicRepository.save(new Relic(2, "relic", Rarity.NORMAL, 5, "sdfkajd", null));
    }
}
