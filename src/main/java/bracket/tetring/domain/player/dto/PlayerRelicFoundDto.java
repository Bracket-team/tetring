package bracket.tetring.domain.player.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRelicFoundDto {
    private Integer relicNumber;
    private String name;
    private String rarity;
    private String effect;
    private Integer price;
    private boolean found;
}
