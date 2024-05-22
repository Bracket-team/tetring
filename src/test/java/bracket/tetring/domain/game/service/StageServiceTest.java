package bracket.tetring.domain.game.service;

import bracket.tetring.domain.game.domain.BlockColor;
import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.domain.Rarity;
import bracket.tetring.domain.game.domain.Relic;
import bracket.tetring.domain.game.dto.EndStageDto;
import bracket.tetring.domain.game.dto.StartStageDto;
import bracket.tetring.domain.game.repository.GameRepository;
import bracket.tetring.domain.game.repository.RelicRepository;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.domain.PlayerBlock;
import bracket.tetring.domain.player.domain.PlayerRelic;
import bracket.tetring.domain.player.domain.Role;
import bracket.tetring.domain.player.repository.PlayerBlockRepository;
import bracket.tetring.domain.player.repository.PlayerRelicRepository;
import bracket.tetring.domain.player.repository.PlayerRepository;
import bracket.tetring.domain.store.domain.Store;
import bracket.tetring.domain.store.repository.StoreRepository;
import bracket.tetring.global.exception.CustomException;
import bracket.tetring.global.util.CalculateSystem;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StageServiceTest {
    @Autowired
    private StageService stageService;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayerRelicRepository playerRelicRepository;
    @Autowired
    private PlayerBlockRepository playerBlockRepository;
    @Autowired
    private RelicRepository relicRepository;
    @Autowired
    private StoreRepository storeRepository;

    private Player player;

    @BeforeEach
    public void init() {
        player = new Player("송하준", "427shg@gmail.com", null, Role.USER);
        playerRepository.save(player);
    }

    @DisplayName("스테이지 시작 시 이미 스테이지 인 경우")
    @Test
    public void startStageNotInStore() {
        //given
        Game game = new Game(11, 1500L, false, false, player);
        gameRepository.save(game);

        //when

        //then
        assertThatThrownBy(() -> stageService.playerStartStage(player.getPlayerId()))
                .isInstanceOf(CustomException.class);
    }

    @DisplayName("스테이지 시작 시 정상적으로 요청하는 경우")
    @Test
    public void startStage() {
        //given
        Game game = new Game(11, 1500L, true, false, player);
        gameRepository.save(game);
        Store store = new Store(game, 2, 10, 1);
        storeRepository.save(store);
        playerBlockRepository.save(new PlayerBlock(game, "0000011001100000", BlockColor.YELLOW));
        playerBlockRepository.save(new PlayerBlock(game, "0000011001100000", BlockColor.YELLOW));
        Relic relic = new Relic(1, "노을 사진", Rarity.NORMAL, 10, "하늘색, 노란색 블록 터트릴 때마다 +1 line score", null);
        Relic relic2 = new Relic(2, "T블록", Rarity.NORMAL, 5, "보라색 블록 터트릴 때마다 +1.5 line score", null);
        relicRepository.save(relic);
        relicRepository.save(relic2);
        playerRelicRepository.save(new PlayerRelic(game, relic, 1, null));

        //when
        StartStageDto playerRelicAndBlock = stageService.playerStartStage(player.getPlayerId());

        //then
        assertThat(playerRelicAndBlock.getStageNumber()).isEqualTo(11);
        assertThat(playerRelicAndBlock.getStageGoal()).isEqualTo(CalculateSystem.getStageGoal(11));
        assertThat(playerRelicAndBlock.getPlayerBlocks()).hasSize(2);
        assertThat(playerRelicAndBlock.getPlayerRelics()).hasSize(1);
        assertThat(playerRelicAndBlock.getMoney()).isEqualTo(10);
        assertThat(store.getRelicRerollTime()).isEqualTo(0);
        assertThat(game.getIsStore()).isFalse();
    }

    @DisplayName("스테이지 끝날 때 이미 상점인 경우")
    @Test
    public void endStageInStore() {
        //given
        Game game = new Game(11, 1500L, true, false, player);
        gameRepository.save(game);

        //when

        //then
        assertThatThrownBy(() -> stageService.playerEndStage(player.getPlayerId(), game.getStageBestScore()))
                .isInstanceOf(CustomException.class);

    }

    @DisplayName("스테이지 끝날 때 이겼을 시")
    @Test
    public void endStageWin() {
        //given
        Game game = new Game(11, 1500L, false, false, player);
        gameRepository.save(game);
        Store store = new Store(game, 2, 10, 1);
        storeRepository.save(store);
        playerBlockRepository.save(new PlayerBlock(game, "0000011001100000", BlockColor.YELLOW));
        playerBlockRepository.save(new PlayerBlock(game, "0000011001100000", BlockColor.YELLOW));
        Relic relic = new Relic(1, "노을 사진", Rarity.NORMAL, 10, "하늘색, 노란색 블록 터트릴 때마다 +1 line score", null);
        Relic relic2 = new Relic(2, "T블록", Rarity.NORMAL, 5, "보라색 블록 터트릴 때마다 +1.5 line score", null);
        relicRepository.save(relic);
        relicRepository.save(relic2);
        playerRelicRepository.save(new PlayerRelic(game, relic, 1, null));

        //when
        EndStageDto stageResult = stageService.playerEndStage(player.getPlayerId(), 2000L);

        //then
        assertThat(stageResult.getWin()).isTrue();
        assertThat(stageResult.getMoney()).isEqualTo(11);
        assertThat(stageResult.getStageNumber()).isEqualTo(12);
        assertThat(stageResult.getStageGoal()).isEqualTo(CalculateSystem.getStageGoal(12));
    }

    @DisplayName("스테이지 끝날 때 졌을 시")
    @Test
    public void endStageLose() {
        //given
        Game game = new Game(11, 1500L, false, false, player);
        gameRepository.save(game);
        Store store = new Store(game, 2, 10, 1);
        storeRepository.save(store);
        playerBlockRepository.save(new PlayerBlock(game, "0000011001100000", BlockColor.YELLOW));
        playerBlockRepository.save(new PlayerBlock(game, "0000011001100000", BlockColor.YELLOW));
        Relic relic = new Relic(1, "노을 사진", Rarity.NORMAL, 10, "하늘색, 노란색 블록 터트릴 때마다 +1 line score", null);
        Relic relic2 = new Relic(2, "T블록", Rarity.NORMAL, 5, "보라색 블록 터트릴 때마다 +1.5 line score", null);
        relicRepository.save(relic);
        relicRepository.save(relic2);
        playerRelicRepository.save(new PlayerRelic(game, relic, 1, null));

        //when
        EndStageDto stageResult = stageService.playerEndStage(player.getPlayerId(), 500L);

        //then
        assertThat(stageResult.getWin()).isFalse();
        assertThat(stageResult.getMoney()).isEqualTo(10);
        assertThat(stageResult.getStageNumber()).isEqualTo(11);
    }
}