package bracket.tetring.domain.game.dto;

import bracket.tetring.domain.player.dto.PlayerRelicDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GameOverDto {

    @JsonProperty("stage_number")
    private Integer stageNumber;

    @JsonProperty("best_score")
    private Long bestScore;

    @JsonProperty("block_count")
    private Integer blockCount;

    private Integer money;

    List<PlayerRelicDto> relics;
}
