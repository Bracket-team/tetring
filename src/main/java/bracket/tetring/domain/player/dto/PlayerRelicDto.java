package bracket.tetring.domain.player.dto;

import bracket.tetring.domain.game.domain.Rarity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRelicDto {

    @JsonProperty("relic_number")
    private Integer relicNumber;

    @JsonProperty("relic_name")
    private String relicName;

    private String rarity;

    private String effect;

    private Integer price;

    private Float rate;

    @JsonProperty("slot_number")
    private Integer slotNumber;
}
