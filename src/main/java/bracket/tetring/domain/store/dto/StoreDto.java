package bracket.tetring.domain.store.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {
    private Integer relicRerollTime;
    private Integer rerollPrice;
    private Integer money;
    private Integer moneyLevel;
    private Integer moneyLevelUpPrice;
}
