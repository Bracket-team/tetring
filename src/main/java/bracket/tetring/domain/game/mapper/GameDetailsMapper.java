package bracket.tetring.domain.game.mapper;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.dto.GameDetailsDto;
import bracket.tetring.domain.game.dto.GameDto;
import bracket.tetring.domain.player.domain.PlayerBlock;
import bracket.tetring.domain.player.domain.PlayerRelic;
import bracket.tetring.domain.player.dto.PlayerBlockDto;
import bracket.tetring.domain.player.dto.PlayerRelicDto;
import bracket.tetring.domain.player.mapper.PlayerBlockMapper;
import bracket.tetring.domain.player.mapper.PlayerRelicMapper;
import bracket.tetring.domain.store.domain.Store;
import bracket.tetring.domain.store.domain.StoreBlock;
import bracket.tetring.domain.store.domain.StoreRelic;
import bracket.tetring.domain.store.dto.StoreBlockDto;
import bracket.tetring.domain.store.dto.StoreDto;
import bracket.tetring.domain.store.dto.StoreRelicDto;
import bracket.tetring.domain.store.mapper.StoreBlockMapper;
import bracket.tetring.domain.store.mapper.StoreRelicMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static bracket.tetring.global.util.CalculateSystem.getMoneyLevelUpPrice;

@Component
@RequiredArgsConstructor
public class GameDetailsMapper {

    private final PlayerBlockMapper playerBlockMapper;
    private final PlayerRelicMapper playerRelicMapper;
    private final StoreBlockMapper storeBlockMapper;
    private final StoreRelicMapper storeRelicMapper;

    public GameDetailsDto toGameDetailsDto (Game game, List<PlayerBlock> playerBlocks, List<PlayerRelic> playerRelics, Integer rerollPrice, Store store, List<StoreBlock> storeBlocks, List<StoreRelic> storeRelics) {
        GameDetailsDto gameDetailsDto = new GameDetailsDto();
        //게임 정보
        GameDto gameDto = new GameDto(game.getGameId(), game.getStageNumber(), game.getStageBestScore(), game.getIsStore());
        gameDetailsDto.setGame(gameDto);
        //플레이어 블록, 유물 정보
        List<PlayerBlockDto> playerBlockDtos = playerBlockMapper.playerBlocksToPlayerBlockDtos(playerBlocks);
        List<PlayerRelicDto> playerRelicDtos = playerRelicMapper.playerRelicsToPlayerRelicDtos(playerRelics);
        gameDetailsDto.setPlayerBlock(playerBlockDtos);
        gameDetailsDto.setPlayerRelic(playerRelicDtos);
        //상점 정보
        StoreDto storeDto = new StoreDto(store.getRelicRerollTime(), rerollPrice, store.getMoney(), store.getMoneyLevel(), getMoneyLevelUpPrice(store.getMoneyLevel()));
        gameDetailsDto.setStore(storeDto);
        //상점 블록, 유물 정보
        List<StoreBlockDto> storeBlockDtos = storeBlockMapper.storeBlocksToStoreBlockDtos(storeBlocks);
        List<StoreRelicDto> storeRelicDtos = storeRelicMapper.storeRelicsToStoreRelicDtos(storeRelics);
        gameDetailsDto.setStoreBlock(storeBlockDtos);
        gameDetailsDto.setStoreRelic(storeRelicDtos);

        return gameDetailsDto;
    }
}
