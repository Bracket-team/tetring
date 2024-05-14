package bracket.tetring.domain.game.service;

import bracket.tetring.domain.game.domain.BlockColor;
import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.dto.GameDetailsDto;
import bracket.tetring.domain.game.mapper.GameDetailsMapper;
import bracket.tetring.domain.game.repository.GameRepository;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.domain.PlayerBlock;
import bracket.tetring.domain.player.domain.PlayerRelic;
import bracket.tetring.domain.player.repository.PlayerBlockRepository;
import bracket.tetring.domain.player.repository.PlayerRelicRepository;
import bracket.tetring.domain.store.domain.Store;
import bracket.tetring.domain.store.domain.StoreBlock;
import bracket.tetring.domain.store.domain.StoreRelic;
import bracket.tetring.domain.store.repository.StoreBlockRepository;
import bracket.tetring.domain.store.repository.StoreRelicRepository;
import bracket.tetring.domain.store.repository.StoreRepository;
import bracket.tetring.domain.store.service.RerollPriceHelper;
import bracket.tetring.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static bracket.tetring.global.error.ErrorCode.STORE_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameStartService {

    private final GameRepository gameRepository;
    private final PlayerBlockRepository playerBlockRepository;
    private final PlayerRelicRepository playerRelicRepository;
    private final StoreRepository storeRepository;
    private final StoreBlockRepository storeBlockRepository;
    private final StoreRelicRepository storeRelicRepository;

    private final GameDetailsMapper gameDetailsMapper;

    private final RerollPriceHelper rerollPriceHelper;
    private final GameServiceHelper gameServiceHelper;

    @Transactional
    public Boolean isPlayerGaming(UUID playerId) {
        Optional<Game> game = gameRepository.findPlayingGameByPlayerId(playerId);
        return game.isPresent();
    }

    @Transactional
    public GameDetailsDto getGameDetailsForPlayers(UUID playerId) {
        Game game = gameRepository.findPlayingGameByPlayerId(playerId)
                .orElseGet(() -> initNewGameForPlayer(playerId));

        // 게임에 속한 플레이어 블록과 플레이어 유물 조회
        List<PlayerBlock> playerBlocks = playerBlockRepository.findAllByGameOrderByBlockIdAsc(game);
        List<PlayerRelic> playerRelics = playerRelicRepository.findAllByGameOrderBySlotNumberAsc(game);

        // 관련 스토어 조회 (게임이 새로 생성된 경우 스토어도 함께 생성됨)
        Store store = storeRepository.findByGame(game)
                .orElseThrow(() -> new CustomException(STORE_NOT_FOUND));

        // 스토어 블록과 스토어 유물 조회
        List<StoreBlock> storeBlocks = storeBlockRepository.findAllByStoreOrderBySlotNumberAsc(store);
        List<StoreRelic> storeRelics = storeRelicRepository.findAllByStoreOrderBySlotNumberAsc(store);

        Integer rerollPrice = rerollPriceHelper.getRerollPriceWithRelics(game, store);

        // 최종 DTO 변환 및 반환
        return gameDetailsMapper.toGameDetailsDto(game, playerBlocks, playerRelics, rerollPrice, store, storeBlocks, storeRelics);
    }

    private Game initNewGameForPlayer(UUID playerId) {
        log.info("새로운 플레이어 시작. 플레이어 아이디={}", playerId);
        Player player = gameServiceHelper.getPlayer(playerId);
        Game newGame = initGame(player);
        initPlayerBlock(newGame);
        initStore(newGame);
        return newGame;
    }
    private Game initGame(Player player) {
        Game game = new Game(1, 0L, false, false, player);
        gameRepository.save(game);
        return game;
    }
    private void initPlayerBlock(Game game) {
        int blockCount = 7;
        List<PlayerBlock> playerBlocks = new ArrayList<>();

        // Sky Blocks
        for (int i = 0; i < blockCount; i++) {
            playerBlocks.add(new PlayerBlock(game, "0100010001000100", BlockColor.SKY));
        }
        // Blue Blocks
        for (int i = 0; i < blockCount; i++) {
            playerBlocks.add(new PlayerBlock(game, "0010001001100000", BlockColor.BLUE));
        }
        for (int i = 0; i < blockCount; i++) {
            playerBlocks.add(new PlayerBlock(game, "0100010001100000", BlockColor.TAN));
        }
        // Yellow Blocks
        for (int i = 0; i < blockCount; i++) {
            playerBlocks.add(new PlayerBlock(game, "0000011001100000", BlockColor.YELLOW));
        }
        // Green Blocks
        for (int i = 0; i < blockCount; i++) {
            playerBlocks.add(new PlayerBlock(game, "0100011000100000", BlockColor.GREEN));
        }
        // Purple Blocks
        for (int i = 0; i < blockCount; i++) {
            playerBlocks.add(new PlayerBlock(game, "0100011001000000", BlockColor.PURPLE));
        }
        // Red Blocks
        for (int i = 0; i < blockCount; i++) {
            playerBlocks.add(new PlayerBlock(game, "0010011001000000", BlockColor.RED));
        }
        playerBlockRepository.saveAll(playerBlocks);
    }
    private void initStore(Game game) {
        Store store = new Store(game, 0, 5, 1);
        storeRepository.save(store);
    }
}
