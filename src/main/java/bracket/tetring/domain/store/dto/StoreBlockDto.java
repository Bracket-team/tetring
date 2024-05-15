package bracket.tetring.domain.store.dto;

import bracket.tetring.domain.game.domain.BlockColor;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StoreBlockDto {

    @JsonProperty("block_shape")
    private String blockShape;

    @JsonProperty("block_color")
    private BlockColor blockColor;

    @JsonProperty("stage_number")
    private Integer slotNumber;
}
