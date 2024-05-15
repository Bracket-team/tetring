package bracket.tetring.domain.game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RankingDto {

    @JsonProperty("player_id")
    private UUID playerId;

    private String name;

    private Long score;

    private Integer stage;
}
