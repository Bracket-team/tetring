package bracket.tetring.domain.store.mapper;

import bracket.tetring.domain.store.domain.Store;
import bracket.tetring.domain.store.domain.StoreBlock;
import bracket.tetring.domain.store.domain.StoreRelic;
import bracket.tetring.domain.store.dto.StoreBlockDto;
import bracket.tetring.domain.store.dto.StoreDetailsDto;
import bracket.tetring.domain.store.dto.StoreDto;
import bracket.tetring.domain.store.dto.StoreRelicDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StoreDetailsMapper {

    private final StoreBlockMapper storeBlockMapper;
    private final StoreRelicMapper storeRelicMapper;
    private final StoreMapper storeMapper;

    public StoreDetailsDto toStoreDetailsDto(Store store, List<StoreBlock> storeBlocks, List<StoreRelic> storeRelics, Integer rerollPrice) {
        StoreDetailsDto storeDetailsDto = new StoreDetailsDto();

        StoreDto storeDto = storeMapper.toStoreDto(store, rerollPrice);
        List<StoreBlockDto> storeBlockDtos = storeBlockMapper.storeBlocksToStoreBlockDtos(storeBlocks);
        List<StoreRelicDto> storeRelicDtos = storeRelicMapper.storeRelicsToStoreRelicDtos(storeRelics);

        storeDetailsDto.setStore(storeDto);
        storeDetailsDto.setStoreBlocks(storeBlockDtos);
        storeDetailsDto.setStoreRelics(storeRelicDtos);

        return storeDetailsDto;
    }
}
