package bracket.tetring.domain.game.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EndStageDto {
    Boolean win;
    Integer stageNumber;
    Long stageGoal;
    Integer money;
}
