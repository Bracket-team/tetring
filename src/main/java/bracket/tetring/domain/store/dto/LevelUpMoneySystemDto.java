package bracket.tetring.domain.store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LevelUpMoneySystemDto {

    @JsonProperty("can_buy")
    boolean canBuy;

    @JsonProperty("level_up_price")
    Integer levelUpPrice;

    @JsonProperty("last_money")
    Integer lastMoney;
}
