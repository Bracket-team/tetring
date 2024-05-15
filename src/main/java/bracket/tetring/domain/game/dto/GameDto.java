package bracket.tetring.domain.game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {

    @JsonProperty("game_id")
    private Long gameId;

    @JsonProperty("stage_number")
    private Integer stageNumber;

    @JsonProperty("stage_best_score")
    private Long stageBestScore;

    @JsonProperty("is_store")
    private Boolean isStore;
}
