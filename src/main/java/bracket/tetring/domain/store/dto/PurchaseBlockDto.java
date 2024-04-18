package bracket.tetring.domain.store.dto;

import bracket.tetring.domain.player.dto.PlayerBlockDto;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseBlockDto {
    boolean canBuy;
    Integer lastMoney;
    PlayerBlockDto playerBlock;
}
