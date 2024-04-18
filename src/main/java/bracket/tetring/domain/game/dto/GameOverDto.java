package bracket.tetring.domain.game.dto;

import bracket.tetring.domain.player.dto.PlayerRelicDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GameOverDto {
    private Integer stageNumber;
    private Long bestScore;
    private Integer blockCount;
    private Integer money;
    List<PlayerRelicDto> relics;
}
