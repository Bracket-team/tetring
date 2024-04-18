package bracket.tetring.domain.game.dto;

import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ScoreDto {
    @Min(value = 0, message = "점수가 최소 0이상어야 합니다.")
    private Long score;
}
