package bracket.tetring.domain.store.dto;

import bracket.tetring.domain.game.domain.BlockColor;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StoreBlockDto {
    private String blockShape;
    private BlockColor blockColor;
    private Integer slotNumber;
}
