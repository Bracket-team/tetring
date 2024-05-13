package bracket.tetring.domain.game.mapper;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.dto.EndStageDto;
import bracket.tetring.domain.game.dto.StartStageDto;
import bracket.tetring.domain.player.domain.PlayerBlock;
import bracket.tetring.domain.player.domain.PlayerRelic;
import bracket.tetring.domain.player.dto.PlayerBlockDto;
import bracket.tetring.domain.player.dto.PlayerRelicDto;
import bracket.tetring.domain.player.mapper.PlayerBlockMapper;
import bracket.tetring.domain.player.mapper.PlayerRelicMapper;
import bracket.tetring.domain.player.repository.PlayerBlockRepository;
import bracket.tetring.domain.player.repository.PlayerRelicRepository;
import bracket.tetring.domain.store.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static bracket.tetring.global.util.CalculateSystem.getStageGoal;
import static bracket.tetring.global.util.CalculateSystem.getTotalMoney;

@Component
@RequiredArgsConstructor
public class StageMapper {

    private final PlayerBlockMapper playerBlockMapper;
    private final PlayerRelicMapper playerRelicMapper;

    public StartStageDto toStartStageDto(Game game, Store store, List<PlayerBlock> playerBlocks, List<PlayerRelic> playerRelics) {
        StartStageDto startStageDto = new StartStageDto();

        startStageDto.setStageNumber(game.getStageNumber());
        startStageDto.setStageGoal(getStageGoal(game.getStageNumber()));
        startStageDto.setMoney(store.getMoney());
        List<PlayerBlockDto> playerBlockDtos = playerBlockMapper.playerBlocksToPlayerBlockDtos(playerBlocks);
        List<PlayerRelicDto> playerRelicDtos = playerRelicMapper.playerRelicsToPlayerRelicDtos(playerRelics);
        startStageDto.setPlayerBlocks(playerBlockDtos);
        startStageDto.setPlayerRelics(playerRelicDtos);

        return startStageDto;
    }

    public EndStageDto toEndStageDto(boolean win, Integer stageNumber, Integer money) {
        EndStageDto endStageDto = new EndStageDto();
        //스테이지 종료시 승패 여부 작성
        endStageDto.setWin(win);
        endStageDto.setStageNumber(stageNumber);
        endStageDto.setStageGoal(getStageGoal(stageNumber));
        endStageDto.setMoney(money);

        return endStageDto;
    }
}
