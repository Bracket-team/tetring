package bracket.tetring.datainit;

import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.domain.Role;
import bracket.tetring.domain.player.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class PlayerDataInit {

    private final PlayerRepository playerRepository;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        Player testPlayer = new Player("송하준", "427shg@gmail.com", "", Role.USER);
        playerRepository.save(testPlayer);
    }
}
