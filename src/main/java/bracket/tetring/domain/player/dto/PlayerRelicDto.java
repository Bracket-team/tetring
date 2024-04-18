package bracket.tetring.domain.player.dto;

import bracket.tetring.domain.game.domain.Rarity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRelicDto {
    private Integer relicNumber;
    private String relicName;
    private String rarity;
    private String effect;
    private Integer price;
    private Integer coef;
    private Integer slotNumber;
}
