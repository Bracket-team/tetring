package bracket.tetring.domain.store.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RerollRelicsDto {
    boolean canReroll;
    Integer nextRerollPrice;
    Integer lastMoney;
    List<StoreRelicDto> relics;
}
