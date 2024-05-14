package bracket.tetring.domain.store.service;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.domain.Relic;
import bracket.tetring.domain.game.service.GameServiceHelper;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.domain.PlayerRelic;
import bracket.tetring.domain.player.domain.PlayerRelicFound;
import bracket.tetring.domain.player.dto.PlayerRelicDto;
import bracket.tetring.domain.player.mapper.PlayerRelicMapper;
import bracket.tetring.domain.player.repository.PlayerRelicFoundRepository;
import bracket.tetring.domain.player.repository.PlayerRelicRepository;
import bracket.tetring.domain.store.domain.Store;
import bracket.tetring.domain.store.domain.StoreRelic;
import bracket.tetring.domain.store.dto.PurchaseRelicDto;
import bracket.tetring.domain.store.dto.RerollRelicsDto;
import bracket.tetring.domain.store.mapper.StoreRelicMapper;
import bracket.tetring.domain.store.repository.StoreRelicRepository;
import bracket.tetring.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static bracket.tetring.global.error.ErrorCode.STORE_RELIC_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class StoreRelicService {

    private final GameServiceHelper gameServiceHelper;
    private final RerollPriceHelper rerollPriceHelper;
    private final RandomStoreHelper randomStoreHelper;

    private final StoreRelicRepository storeRelicRepository;
    private final PlayerRelicRepository playerRelicRepository;
    private final PlayerRelicFoundRepository playerRelicFoundRepository;

    private final PlayerRelicMapper playerRelicMapper;
    private final StoreRelicMapper storeRelicMapper;

    //플레이어 유물 구입
    //구매 못할 경우, 돈은 그대로, 유물 정보는 null로
    @Transactional
    public PurchaseRelicDto purchaseRelic(UUID playerId, Integer slotNumber) {
        Player player = gameServiceHelper.getPlayer(playerId);
        Game game = gameServiceHelper.getGame(playerId);
        Store store = gameServiceHelper.getStore(game);
        //구입할 유물
        StoreRelic storeRelic = storeRelicRepository.findByStoreAndSlotNumber(store, slotNumber).orElseThrow(
                () -> new CustomException(STORE_RELIC_NOT_FOUND)
        );
        Relic relic = storeRelic.getRelic();

        Integer playerMoney = store.getMoney(); //플레이어 머니
        Integer relicMoney = relic.getPrice(); //유물 가격
        Optional<Integer> smallestEmptySlotNumber = findSmallestEmptySlotNumber(game); //비어있는 유물 슬롯 번호
        PlayerRelicDto playerRelicDto = null;
        boolean canBuy = false;

        //유물 구입이 가능한 경우
        if(playerMoney >= relicMoney && smallestEmptySlotNumber.isPresent()) {
            canBuy = true;
            //돈 계산
            playerMoney -= relicMoney;
            if(relic.getRelicNumber() == 18)
                playerMoney *= 2;
            store.setMoney(playerMoney);

            //플레이어 유물에 추가
            PlayerRelic playerRelic = new PlayerRelic(game, relic, smallestEmptySlotNumber.orElseThrow());
            playerRelic = playerRelicRepository.save(playerRelic);

            playerRelicDto = playerRelicMapper.playerRelicToPlayerRelicDto(playerRelic);

            //상점에서 유물 삭제
            storeRelicRepository.delete(storeRelic);

            //유저 유물 잠금 해제
            PlayerRelicFound relicFound = playerRelicFoundRepository.findByPlayerAndRelic(player, relic)
                    .orElseThrow( () -> new CustomException(STORE_RELIC_NOT_FOUND));
            relicFound.setFound(true);
        }
        return new PurchaseRelicDto(canBuy, playerMoney, playerRelicDto);
    }

    //유물 리롤
    @Transactional
    public RerollRelicsDto rerollRelics(UUID playerId) {
        Game game = gameServiceHelper.getGame(playerId);
        Store store = gameServiceHelper.getStore(game);
        //플레이어 머니
        Integer playerMoney = store.getMoney();
        //리롤 가격
        Integer rerollPrice = rerollPriceHelper.getRerollPriceWithRelics(game, store);
        List<StoreRelic> relics = storeRelicRepository.findAllByStoreOrderBySlotNumberAsc(store);

        //리롤 여부
        boolean canReroll = false;

        if(playerMoney >= rerollPrice) {
            canReroll = true;
            //돈 계산
            playerMoney -= rerollPrice;
            store.setMoney(playerMoney);
            //유물 리롤
            randomStoreHelper.saveRandomRelic(game, store);
            //유물 목록들
            relics = storeRelicRepository.findAllByStoreOrderBySlotNumberAsc(store);
            //리롤 횟수 증가
            rerollPrice = rerollPriceHelper.getRerollPriceWithRelics(game, store);
            store.setRelicRerollTime(store.getRelicRerollTime() + 1);
        }
        return new RerollRelicsDto(canReroll, rerollPrice, playerMoney, storeRelicMapper.storeRelicsToStoreRelicDtos(relics));
    }

    private Optional<Integer> findSmallestEmptySlotNumber(Game game) {
        List<Integer> occupiedSlots = playerRelicRepository.findSlotNumbersByGame(game);
        for (int i = 1; i <= 5; i++) {
            if (!occupiedSlots.contains(i)) {
                return Optional.of(i);
            }
        }
        return Optional.empty(); // 모든 슬롯이 차있는 경우
    }
}
