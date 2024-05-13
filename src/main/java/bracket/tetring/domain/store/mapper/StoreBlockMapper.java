package bracket.tetring.domain.store.mapper;

import bracket.tetring.domain.store.domain.StoreBlock;
import bracket.tetring.domain.store.dto.StoreBlockDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StoreBlockMapper {
    public StoreBlockDto storeBlockToStoreBlockDto(StoreBlock storeBlock) {
        StoreBlockDto storeBlockDto = new StoreBlockDto();

        storeBlockDto.setSlotNumber(storeBlock.getSlotNumber());
        storeBlockDto.setBlockShape(storeBlock.getBlockShape());
        storeBlockDto.setBlockColor(storeBlock.getBlockColor());

        return storeBlockDto;
    }

    public List<StoreBlockDto> storeBlocksToStoreBlockDtos(List<StoreBlock> storeBlocks) {
        if (storeBlocks == null) {
            return null;
        }

        return storeBlocks.stream()
                .map(this::storeBlockToStoreBlockDto)
                .collect(Collectors.toList());
    }
}
