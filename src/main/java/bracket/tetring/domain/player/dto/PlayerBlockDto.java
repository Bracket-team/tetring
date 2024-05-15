package bracket.tetring.domain.player.dto;

import bracket.tetring.domain.game.domain.BlockColor;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PlayerBlockDto {

    @JsonProperty("block_id")
    private Long blockId;

    @NotNull(message = "블록 모양은 null이 아닙니다.")
    @Size(min = 16, max = 16, message = "블록의 모양의 길이는 정확히 16이어야 합니다.")
    @Pattern(regexp = "^[01]+$", message = "블록 모양은 0혹은 1만 포함해야 합니다.")
    @JsonProperty("block_shape")
    private String blockShape;

    @NotNull(message = "블록 색깔은 null이 아닙니다.")
    @JsonProperty("block_color")
    private BlockColor blockColor;
}
