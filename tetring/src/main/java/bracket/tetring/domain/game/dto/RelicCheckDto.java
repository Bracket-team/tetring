package bracket.tetring.domain.game.dto;

import bracket.tetring.domain.game.domain.Rarity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelicCheckDto {
    private String relicName;
    private Rarity rarity;
    private String effect;
    private Integer price;

    public RelicCheckDto() {
    }

    public RelicCheckDto(String relicName, Rarity rarity, String effect, Integer price) {
        this.relicName = relicName;
        this.rarity = rarity;
        this.effect = effect;
        this.price = price;
    }
}
