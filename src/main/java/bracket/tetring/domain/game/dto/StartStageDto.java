package bracket.tetring.domain.game.dto;

import bracket.tetring.domain.player.domain.PlayerBlock;
import bracket.tetring.domain.player.dto.PlayerBlockDto;
import bracket.tetring.domain.player.dto.PlayerRelicDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StartStageDto {
    private Integer stageNumber;
    private Long stageGoal;
    private Integer money;
    private List<PlayerBlockDto> playerBlocks;
    private List<PlayerRelicDto> playerRelics;
}
