package bracket.tetring.domain.store.service;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.service.GameServiceHelper;
import bracket.tetring.domain.store.domain.Store;
import bracket.tetring.domain.store.domain.StoreBlock;
import bracket.tetring.domain.store.domain.StoreRelic;
import bracket.tetring.domain.store.dto.LevelUpMoneySystemDto;
import bracket.tetring.domain.store.dto.StoreDetailsDto;
import bracket.tetring.domain.store.mapper.LevelUpMoneySystemMapper;
import bracket.tetring.domain.store.mapper.StoreDetailsMapper;
import bracket.tetring.domain.store.repository.StoreBlockRepository;
import bracket.tetring.domain.store.repository.StoreRelicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static bracket.tetring.global.util.CalculateSystem.getMoneyLevelUpPrice;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final GameServiceHelper gameServiceHelper;
    private final RerollPriceHelper rerollPriceHelper;
    private final RandomStoreHelper randomStoreHelper;

    private final StoreBlockRepository storeBlockRepository;
    private final StoreRelicRepository storeRelicRepository;

    private final StoreDetailsMapper storeDetailsMapper;
    private final LevelUpMoneySystemMapper levelUpMoneySystemMapper;

    //상점 정보 가져오는 메소드
    @Transactional
    public StoreDetailsDto getStoreInfo(UUID playerId) {
        Game game = gameServiceHelper.getGame(playerId);
        Store store = gameServiceHelper.getStore(game);

        //상점에 처음 들어오는 경우, 블록과 유물을 초기화
        setStoreBlockAndRelic(game, store);

        List<StoreBlock> storeBlocks = storeBlockRepository.findAllByStoreOrderBySlotNumberAsc(store);
        List<StoreRelic> storeRelics = storeRelicRepository.findAllByStoreOrderBySlotNumberAsc(store);

        //리롤 비용 가져오기
        Integer rerollPrice = rerollPriceHelper.getRerollPriceWithRelics(game, store);

        return storeDetailsMapper.toStoreDetailsDto(store, storeBlocks, storeRelics, rerollPrice);
    }

    // 상점 블록하고 유물 랜덤 세팅
    @Transactional
    public void setStoreBlockAndRelic(Game game, Store store) {
        if (!game.getIsStore()) {
            game.setIsStore(true);
            //storeBlock 정보 랜덤으로 저장
            randomStoreHelper.saveRandomBlock(store);
            //storeRelic 정보 랜덤으로 저장
            randomStoreHelper.saveRandomRelic(game, store);
        }
    }

    //사용자 돈 확인
    @Transactional
    public Integer getPlayerMoney(UUID playerId) {
        Game game = gameServiceHelper.getGame(playerId);
        Store store = gameServiceHelper.getStore(game);

        return store.getMoney();
    }

    //머니 시스템 레벨 업
    @Transactional
    public LevelUpMoneySystemDto levelUpMoneySystem(UUID playerId) {
        Game game = gameServiceHelper.getGame(playerId);
        Store store = gameServiceHelper.getStore(game);

        Integer playerMoney = store.getMoney();
        Integer moneyLevel = store.getMoneyLevel();
        Integer moneyLevelUpPrice = getMoneyLevelUpPrice(store.getMoneyLevel());
        boolean canBuy = false;

        if(playerMoney >= moneyLevelUpPrice && moneyLevel < 5) {
            canBuy = true;
            //남은 돈을 계산하여 저장
            store.setMoney(store.getMoney() - getMoneyLevelUpPrice(moneyLevel));
            //레벨업한 머니 레벨을 저장
            moneyLevel += 1;
            store.setMoneyLevel(moneyLevel);
        }
        return levelUpMoneySystemMapper.toLevelUpMoneySystemDto(store, canBuy);
    }
}
