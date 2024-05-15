package bracket.tetring.domain.store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StoreDetailsDto {

    StoreDto store;

    @JsonProperty("store_blocks")
    List<StoreBlockDto> storeBlocks;

    @JsonProperty("store_relics")
    List<StoreRelicDto> storeRelics;
}
