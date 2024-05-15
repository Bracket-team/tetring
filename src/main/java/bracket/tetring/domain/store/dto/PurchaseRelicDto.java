package bracket.tetring.domain.store.dto;

import bracket.tetring.domain.player.dto.PlayerRelicDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRelicDto {

    @JsonProperty("can_buy")
    boolean canBuy;

    @JsonProperty("last_money")
    Integer lastMoney;

    @JsonProperty("player_relic")
    PlayerRelicDto playerRelic;
}
