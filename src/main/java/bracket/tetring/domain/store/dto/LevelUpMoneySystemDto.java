package bracket.tetring.domain.store.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LevelUpMoneySystemDto {
    boolean canBuy;
    Integer levelUpPrice;
    Integer lastMoney;
}
