package bracket.tetring.domain.store.mapper;

import bracket.tetring.domain.store.domain.StoreRelic;
import bracket.tetring.domain.store.dto.StoreRelicDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StoreRelicMapper {
    public StoreRelicDto storeRelicToStoreRelicDto(StoreRelic storeRelic) {
        StoreRelicDto storeRelicDto = new StoreRelicDto();

        storeRelicDto.setRelicNumber(storeRelic.getRelic().getRelicNumber());
        storeRelicDto.setRelicName(storeRelic.getRelic().getRelicName());
        storeRelicDto.setRarity(storeRelic.getRelic().getRarity().toString());
        storeRelicDto.setPrice(storeRelic.getRelic().getPrice());
        storeRelicDto.setEffect(storeRelic.getRelic().getEffect());
        storeRelicDto.setCoef(storeRelic.getRelic().getCoef());
        storeRelicDto.setSlotNumber(storeRelic.getSlotNumber());

        return storeRelicDto;
    }

    public List<StoreRelicDto> storeRelicsToStoreRelicDtos(List<StoreRelic> storeRelics) {
        if (storeRelics == null) {
            return null;
        }

        return storeRelics.stream()
                .map(this::storeRelicToStoreRelicDto)
                .collect(Collectors.toList());
    }
}
