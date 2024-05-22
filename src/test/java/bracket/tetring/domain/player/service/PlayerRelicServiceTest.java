package bracket.tetring.domain.player.service;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.domain.Rarity;
import bracket.tetring.domain.game.domain.Relic;
import bracket.tetring.domain.game.repository.GameRepository;
import bracket.tetring.domain.game.repository.RelicRepository;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.domain.PlayerRelic;
import bracket.tetring.domain.player.domain.Role;
import bracket.tetring.domain.player.dto.PlayerRelicDto;
import bracket.tetring.domain.player.repository.PlayerRelicRepository;
import bracket.tetring.domain.player.repository.PlayerRepository;
import bracket.tetring.global.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@Transactional
class PlayerRelicServiceTest {
    @Autowired
    private PlayerRelicService playerRelicService;
    @Autowired
    private RelicRepository relicRepository;
    @Autowired
    private PlayerRelicRepository playerRelicRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameRepository gameRepository;

    private Player player;

    @BeforeEach
    public void init() {
        player = new Player("송하준", "427shg@gmail.com", null, Role.USER);
        playerRepository.save(player);
        Game game = new Game(11, 1400L, false, false, player);
        gameRepository.save(game);
        Relic relic = new Relic(1, "노을 사진", Rarity.NORMAL, 10, "하늘색, 노란색 블록 터트릴 때마다 +1 line score", null);
        Relic relic2 = new Relic(2, "T블록", Rarity.NORMAL, 5, "보라색 블록 터트릴 때마다 +1.5 line score", null);
        relicRepository.save(relic);
        relicRepository.save(relic2);
        playerRelicRepository.save(new PlayerRelic(game, relic, 2, null));
        playerRelicRepository.save(new PlayerRelic(game, relic2, 1, null));
    }

    @DisplayName("플레이어 유물 조회")
    @Test
    public void getPlayerRelicsTest() {
        //given

        //when
        List<PlayerRelicDto> playerRelics = playerRelicService.getPlayerRelics(player.getPlayerId());

        //then
        assertThat(playerRelics).hasSize(2);
        assertThat(playerRelics.get(0).getRelicNumber()).isEqualTo(2);
        assertThat(playerRelics.get(1).getRelicNumber()).isEqualTo(1);
    }

    @DisplayName("플레이어 유물 버리기 기능(유물 없을 시)")
    @Test
    public void throwRelicExTest() {
        //given

        //when

        //then
        Assertions.assertThatThrownBy(() -> playerRelicService.throwPlayerRelic(player.getPlayerId(), 3))
                .isInstanceOf(CustomException.class);
    }

    @DisplayName("플레이어 유물 버리기 기능(유물 있을 시)")
    @Test
    public void throwRelicTest() {
        //given

        //when
        playerRelicService.throwPlayerRelic(player.getPlayerId(), 2);

        //then
        assertThat(playerRelicService.getPlayerRelics(player.getPlayerId())).hasSize(1);
        assertThat(playerRelicService.getPlayerRelics(player.getPlayerId()).get(0).getRelicNumber()).isEqualTo(2);
    }

    @DisplayName("유물 존재 확인")
    @Test
    public void existsRelicTest() {
        //given

        //when
        boolean result1 = playerRelicService.existsRelic(player.getPlayerId(), 1);
        boolean result2 = playerRelicService.existsRelic(player.getPlayerId(), 3);

        //then
        assertThat(result1).isTrue();
        assertThat(result2).isFalse();
    }
}