package bracket.tetring.domain.game.dto;

import bracket.tetring.domain.game.domain.Rarity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RelicCheckDto {
    private String relicName;
    private Rarity rarity;
    private String effect;
    private Integer price;
}
