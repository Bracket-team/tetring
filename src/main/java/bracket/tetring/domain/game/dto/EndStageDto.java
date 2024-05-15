package bracket.tetring.domain.game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EndStageDto {

    Boolean win;

    @JsonProperty("stage_number")
    Integer stageNumber;

    @JsonProperty("stage_goal")
    Long stageGoal;

    Integer money;
}
