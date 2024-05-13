package bracket.tetring.domain.game.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RankingDto {
    private UUID playerId;
    private String name;
    private Long score;
    private Integer stage;
}
