package bracket.tetring.domain.store.mapper;

import bracket.tetring.domain.store.domain.Store;
import bracket.tetring.domain.store.dto.StoreDto;
import org.springframework.stereotype.Component;

import static bracket.tetring.global.util.CalculateSystem.getMoneyLevelUpPrice;

@Component
public class StoreMapper {

    public StoreDto toStoreDto(Store store, Integer rerollPrice) {
        StoreDto storeDto = new StoreDto();

        storeDto.setRerollPrice(rerollPrice);
        storeDto.setMoney(store.getMoney());
        storeDto.setMoneyLevel(store.getMoneyLevel());
        storeDto.setMoneyLevelUpPrice(getMoneyLevelUpPrice(store.getMoneyLevel()));

        return storeDto;
    }
}
