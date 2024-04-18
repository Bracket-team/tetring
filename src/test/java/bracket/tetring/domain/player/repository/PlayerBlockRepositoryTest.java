package bracket.tetring.domain.player.repository;

import bracket.tetring.domain.game.domain.BlockColor;
import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.domain.PlayerBlock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PlayerBlockRepositoryTest {
    @Autowired
    PlayerBlockRepository playerBlockRepository;
    @Autowired
    TestEntityManager entityManager;

    private Game game;
    private Game game2;
    private List<PlayerBlock> playerBlocks;

    @BeforeEach
    public void setData() {
        Player player = new Player("Test Player", "test@example.com");
        entityManager.persist(player);

        game = new Game(1, 1200L, false, false, player);
        entityManager.persist(game);
        entityManager.persist(new PlayerBlock(game, "0000011001100000", BlockColor.PURPLE));
        entityManager.persist(new PlayerBlock(game, "0000011100100000", BlockColor.BLUE));
        entityManager.persist(new PlayerBlock(game, "0000011101000000", BlockColor.SKY));
        entityManager.persist(new PlayerBlock(game, "0000011100010000", BlockColor.TAN));

        game2 = new Game(1, 1200L, false, false, player);
        entityManager.persist(game2);
        entityManager.persist(new PlayerBlock(game2, "0000011111100000", BlockColor.BLUE));
        entityManager.persist(new PlayerBlock(game2, "0000011001000000", BlockColor.SKY));
        entityManager.persist(new PlayerBlock(game2, "0000011001100000", BlockColor.PURPLE));
        entityManager.persist(new PlayerBlock(game2, "0000011100010000", BlockColor.TAN));

        entityManager.flush();
    }

    @Test
    @DisplayName("게임별 블록 목록을 ID 오름차순으로 조회한다.")
    public void findAllByGameOrderByBlockIdAsc() {
        // given

        // when
        List<PlayerBlock> blocks = playerBlockRepository.findAllByGameOrderByBlockIdAsc(game);

        // then
        assertThat(blocks).hasSize(4);
        assertThat(blocks.get(0).getBlockId()).isLessThan(blocks.get(1).getBlockId());
        assertThat(blocks.get(1).getBlockId()).isLessThan(blocks.get(2).getBlockId());
        assertThat(blocks.get(2).getBlockId()).isLessThan(blocks.get(3).getBlockId());
    }

    @Test
    @DisplayName("특정 게임에 대한 블록 수를 계산한다.")
    public void countPlayerBlockByGame() {
        // when
        Integer count = playerBlockRepository.countPlayerBlockByGame(game);

        // then
        assertThat(count).isEqualTo(4);
    }

    @Test
    @DisplayName("게임과 블록 ID로 블록을 조회한다.")
    public void findByGameAndBlockId() {
        // given
        PlayerBlock block = new PlayerBlock(game, "0100010001000100", BlockColor.SKY);
        entityManager.persist(block);

        // when
        PlayerBlock foundBlock = playerBlockRepository.findByGameAndBlockId(game, block.getBlockId()).orElseThrow();

        // then
        assertThat(foundBlock).isNotNull();
        assertThat(foundBlock.getBlockShape()).isEqualTo("0100010001000100");
        assertThat(foundBlock.getBlockColor()).isEqualTo(BlockColor.SKY);
    }

    @Test
    @DisplayName("블록 ID와 게임에 의해 블록의 존재 여부를 검증한다.")
    public void existsByBlockIdAndGame() {
        // given
        PlayerBlock block = new PlayerBlock(game, "0100010001000100", BlockColor.SKY);
        entityManager.persist(block);

        // when
        boolean exists = playerBlockRepository.existsByBlockIdAndGame(block.getBlockId(), game);

        // then
        assertThat(exists).isTrue();
    }
}