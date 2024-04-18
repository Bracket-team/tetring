package bracket.tetring.domain.store.dto;

import bracket.tetring.domain.player.dto.PlayerRelicDto;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRelicDto {
    boolean canBuy;
    Integer lastMoney;
    PlayerRelicDto playerRelic;
}
