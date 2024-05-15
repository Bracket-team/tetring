package bracket.tetring.domain.game.dto;

import bracket.tetring.domain.player.domain.PlayerBlock;
import bracket.tetring.domain.player.dto.PlayerBlockDto;
import bracket.tetring.domain.player.dto.PlayerRelicDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StartStageDto {

    @JsonProperty("stage_number")
    private Integer stageNumber;

    @JsonProperty("stage_goal")
    private Long stageGoal;

    private Integer money;

    @JsonProperty("player_blocks")
    private List<PlayerBlockDto> playerBlocks;

    @JsonProperty("player_relics")
    private List<PlayerRelicDto> playerRelics;
}
