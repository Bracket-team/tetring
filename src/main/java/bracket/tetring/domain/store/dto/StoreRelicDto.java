package bracket.tetring.domain.store.dto;

import bracket.tetring.domain.game.domain.Rarity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StoreRelicDto {
    private Integer relicNumber;
    private String relicName;
    private String rarity;
    private String effect;
    private Integer price;
    private Integer coef;
    private Integer slotNumber;
}
