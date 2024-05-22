package bracket.tetring.domain.player.service;

import bracket.tetring.domain.game.domain.Rarity;
import bracket.tetring.domain.game.domain.Relic;
import bracket.tetring.domain.game.repository.RelicRepository;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.domain.PlayerRelicFound;
import bracket.tetring.domain.player.domain.Role;
import bracket.tetring.domain.player.dto.PlayerRelicFoundDto;
import bracket.tetring.domain.player.repository.PlayerRelicFoundRepository;
import bracket.tetring.domain.player.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PlayerRelicFoundServiceTest {

    @Autowired
    private PlayerRelicFoundService playerRelicFoundService;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private RelicRepository relicRepository;
    @Autowired
    private PlayerRelicFoundRepository playerRelicFoundRepository;

    private Player player;

    @BeforeEach
    public void init() {
        player = new Player("송하준", "427shg@gmail.com", null, Role.USER);
        playerRepository.save(player);

        Relic relic = new Relic(1, "노을 사진", Rarity.NORMAL, 10, "하늘색, 노란색 블록 터트릴 때마다 +1 line score", null);
        Relic relic2 = new Relic(2, "T블록", Rarity.NORMAL, 5, "보라색 블록 터트릴 때마다 +1.5 line score", null);
        relicRepository.save(relic);
        relicRepository.save(relic2);
        playerRelicFoundRepository.save(new PlayerRelicFound(player, relic));
        playerRelicFoundRepository.save(new PlayerRelicFound(player, relic2));
    }


    @DisplayName("사용자가 발견한 유물 가져오기")
    @Test
    public void getRelicFoundTest() {
        //given

        //when
        List<PlayerRelicFoundDto> relicFoundList = playerRelicFoundService.getRelicFound(player.getPlayerId());

        //then
        assertThat(relicFoundList).hasSize(2);
        assertThat(relicFoundList.get(0).getRelicNumber()).isEqualTo(1);
        assertThat(relicFoundList.get(1).getRelicNumber()).isEqualTo(2);
    }
}