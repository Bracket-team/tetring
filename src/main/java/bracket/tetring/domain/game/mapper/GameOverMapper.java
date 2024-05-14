package bracket.tetring.domain.game.mapper;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.dto.GameOverDto;
import bracket.tetring.domain.player.domain.PlayerRelic;
import bracket.tetring.domain.player.dto.PlayerRelicDto;
import bracket.tetring.domain.player.mapper.PlayerRelicMapper;
import bracket.tetring.domain.store.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GameOverMapper {

    private final PlayerRelicMapper playerRelicMapper;

    public GameOverDto toGameOverDto(Game game, Store store, Integer blockCount, List<PlayerRelic> playerRelics) {
        GameOverDto gameOverDto = new GameOverDto();

        gameOverDto.setStageNumber(game.getStageNumber());
        gameOverDto.setBestScore(game.getStageBestScore());
        gameOverDto.setBlockCount(blockCount);
        gameOverDto.setMoney(store.getMoney());
        List<PlayerRelicDto> playerRelicDtos = playerRelicMapper.playerRelicsToPlayerRelicDtos(playerRelics);
        gameOverDto.setRelics(playerRelicDtos);

        return gameOverDto;
    }
}
