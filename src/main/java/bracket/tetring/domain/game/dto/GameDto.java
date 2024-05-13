package bracket.tetring.domain.game.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {
    private Long gameId;
    private Integer stageNumber;
    private Long stageBestScore;
    private Boolean isStore;
}
