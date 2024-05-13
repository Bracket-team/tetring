package bracket.tetring.domain.store.mapper;

import bracket.tetring.domain.store.domain.Store;
import bracket.tetring.domain.store.dto.LevelUpMoneySystemDto;
import bracket.tetring.global.util.CalculateSystem;
import org.springframework.stereotype.Component;

import static bracket.tetring.global.util.CalculateSystem.*;

@Component
public class LevelUpMoneySystemMapper {
    public LevelUpMoneySystemDto toLevelUpMoneySystemDto(Store store, boolean canBuy) {
        LevelUpMoneySystemDto levelUpMoneySystemDto = new LevelUpMoneySystemDto();

        levelUpMoneySystemDto.setCanBuy(canBuy);
        levelUpMoneySystemDto.setLevelUpPrice(getMoneyLevelUpPrice(store.getMoneyLevel()));
        levelUpMoneySystemDto.setLastMoney(store.getMoney());

        return levelUpMoneySystemDto;
    }

}
//private LevelUpMoneySystemDto getLevelUpMoneySystemDto(Store store) {
//    Integer playerMoney = store.getMoney();
//    Integer moneyLevel = store.getMoneyLevel();
//    Integer moneyLevelUpPrice = getMoneyLevelUpPrice(store.getMoneyLevel());
//    Integer money = store.getMoney();
//    boolean canBuy = false;
//
//    if(playerMoney >= moneyLevelUpPrice && moneyLevel < 5) { //머니 시스템 레벨업이 가능한 경우
//        canBuy = true;
//
//        money -= getMoneyLevelUpPrice(moneyLevel);
//        store.setMoneyLevel(money);
//
//        moneyLevel += 1;
//        store.setMoneyLevel(moneyLevel);
//    }
//    return new LevelUpMoneySystemDto(canBuy, getMoneyLevelUpPrice(moneyLevel), money);
//}