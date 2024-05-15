package bracket.tetring.domain.store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RerollRelicsDto {

    @JsonProperty("can_reroll")
    boolean canReroll;

    @JsonProperty("next_reroll_price")
    Integer nextRerollPrice;

    @JsonProperty("last_money")
    Integer lastMoney;

    List<StoreRelicDto> relics;
}
