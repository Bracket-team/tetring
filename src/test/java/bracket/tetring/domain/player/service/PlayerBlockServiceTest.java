package bracket.tetring.domain.player.service;

import bracket.tetring.domain.game.domain.BlockColor;
import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.repository.GameRepository;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.domain.PlayerBlock;
import bracket.tetring.domain.player.domain.Role;
import bracket.tetring.domain.player.dto.PlayerBlockDto;
import bracket.tetring.domain.player.repository.PlayerBlockRepository;
import bracket.tetring.domain.player.repository.PlayerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class PlayerBlockServiceTest {
    @Autowired
    private PlayerBlockService playerBlockService;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private PlayerBlockRepository playerBlockRepository;
    @Autowired
    private GameRepository gameRepository;

    private Player player;
    private PlayerBlock playerBlock1;
    private PlayerBlock playerBlock2;

    @BeforeEach
    public void init() {
        player = new Player("송하준", "427shg@gmail.com", null, Role.USER);
        playerRepository.save(player);
        Game game = new Game(11, 1500L, false, false, player);
        gameRepository.save(game);
        playerBlock1 = new PlayerBlock(game, "0000011001100000", BlockColor.TAN);
        playerBlock2 = new PlayerBlock(game, "0110011001100000", BlockColor.SKY);
        playerBlockRepository.save(playerBlock1);
        playerBlockRepository.save(playerBlock2);
    }

    @DisplayName("플레이어 블록 정보 반환")
    @Test
    public void getPlayerBlocksTest() {
        //given

        //when
        List<PlayerBlockDto> playerBlocks = playerBlockService.getPlayerBlocks(player.getPlayerId());

        //then
        assertThat(playerBlocks).hasSize(2);
        assertThat(playerBlocks.get(0).getBlockColor()).isEqualTo(BlockColor.TAN);
        assertThat(playerBlocks.get(1).getBlockColor()).isEqualTo(BlockColor.SKY);
    }

    @DisplayName("블록 모양 변환")
    @Test
    public void changeBlockShapeTest() {
        //given

        //when
        PlayerBlockDto playerBlockDto = new PlayerBlockDto(playerBlock1.getBlockId(), "0110011001100000", BlockColor.TAN);
        playerBlockService.changeBlock(player.getPlayerId(), playerBlockDto);

        //then
        assertThat(playerBlock1.getBlockShape()).isEqualTo("0110011001100000");
        assertThat(playerBlock1.getBlockColor()).isEqualTo(BlockColor.TAN);
    }
}