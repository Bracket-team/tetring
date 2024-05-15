package bracket.tetring.domain.player.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRelicFoundDto {

    @JsonProperty("relic_number")
    private Integer relicNumber;

    private String name;

    private String rarity;

    private String effect;

    private Integer price;

    private boolean found;
}
