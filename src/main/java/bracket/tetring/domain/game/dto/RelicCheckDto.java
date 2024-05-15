package bracket.tetring.domain.game.dto;

import bracket.tetring.domain.game.domain.Rarity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RelicCheckDto {

    @JsonProperty("relic_name")
    private String relicName;

    private Rarity rarity;

    private String effect;

    private Integer price;
}
