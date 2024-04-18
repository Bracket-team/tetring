package bracket.tetring.domain.store.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StoreDetailsDto {
    StoreDto store;
    List<StoreBlockDto> storeBlocks;
    List<StoreRelicDto> storeRelics;
}
