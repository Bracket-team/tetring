package bracket.tetring.domain.store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {

    @JsonProperty("reroll_price")
    private Integer rerollPrice;

    private Integer money;

    @JsonProperty("money_level")
    private Integer moneyLevel;

    @JsonProperty("money_level_up_price")
    private Integer moneyLevelUpPrice;
}
