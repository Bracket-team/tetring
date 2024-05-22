package bracket.tetring.domain.store.service;

import bracket.tetring.domain.game.domain.BlockColor;
import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.repository.GameRepository;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.domain.PlayerBlock;
import bracket.tetring.domain.player.domain.Role;
import bracket.tetring.domain.player.repository.PlayerBlockRepository;
import bracket.tetring.domain.player.repository.PlayerRepository;
import bracket.tetring.domain.store.domain.Store;
import bracket.tetring.domain.store.domain.StoreBlock;
import bracket.tetring.domain.store.dto.PurchaseBlockDto;
import bracket.tetring.domain.store.repository.StoreBlockRepository;
import bracket.tetring.domain.store.repository.StoreRepository;
import bracket.tetring.global.exception.CustomException;
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

@SpringBootTest
@Transactional
class StoreBlockServiceTest {
    @Autowired
    private StoreBlockService storeBlockService;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreBlockRepository storeBlockRepository;
    @Autowired
    private PlayerBlockRepository playerBlockRepository;

    private Player player;
    private Game game;

    @BeforeEach
    public void init() {
        player = new Player("송하준", "427shg@gmail.com", null, Role.USER);
        playerRepository.save(player);
        game = new Game(12, 1500L, true, false, player);
        gameRepository.save(game);
        Store store = new Store(game, 0, 13, 2);
        storeRepository.save(store);
        StoreBlock storeBlock = new StoreBlock(1, store, "0000011001100000", BlockColor.PURPLE);
        storeBlockRepository.save(storeBlock);
    }

    @DisplayName("블록을 구매혀라고 하는데 없을 경우")
    @Test
    public void failToPurchase() {
        //given

        //when

        //then
        assertThatThrownBy(() -> storeBlockService.purchaseBlock(player.getPlayerId(), 2))
                .isInstanceOf(CustomException.class);
    }

    @DisplayName("블록을 구매하는 경우")
    @Test
    public void purchaseBlock() {
        //given

        //when
        PurchaseBlockDto purchaseBlockDto = storeBlockService.purchaseBlock(player.getPlayerId(), 1);
        List<PlayerBlock> playerBlocks = playerBlockRepository.findAllByGameOrderByBlockIdAsc(game);

        //then
        assertThat(purchaseBlockDto.isCanBuy()).isTrue();
        assertThat(purchaseBlockDto.getLastMoney()).isEqualTo(8);
        assertThat(purchaseBlockDto.getPlayerBlock().getBlockColor()).isEqualTo(BlockColor.PURPLE);
        assertThat(playerBlocks).hasSize(1);
    }
}