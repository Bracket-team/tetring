package bracket.tetring.domain.store.service;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.repository.GameRepository;
import bracket.tetring.domain.game.service.GameServiceHelper;
import bracket.tetring.domain.player.domain.PlayerBlock;
import bracket.tetring.domain.player.dto.PlayerBlockDto;
import bracket.tetring.domain.player.mapper.PlayerBlockMapper;
import bracket.tetring.domain.player.repository.PlayerBlockRepository;
import bracket.tetring.domain.store.domain.Store;
import bracket.tetring.domain.store.domain.StoreBlock;
import bracket.tetring.domain.store.dto.PurchaseBlockDto;
import bracket.tetring.domain.store.repository.StoreBlockRepository;
import bracket.tetring.domain.store.repository.StoreRelicRepository;
import bracket.tetring.domain.store.repository.StoreRepository;
import bracket.tetring.global.error.ErrorCode;
import bracket.tetring.global.exception.CustomException;
import bracket.tetring.global.util.CalculateSystem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static bracket.tetring.global.error.ErrorCode.*;
import static bracket.tetring.global.util.CalculateSystem.*;

@Service
@RequiredArgsConstructor
public class StoreBlockService {

    private final GameServiceHelper gameServiceHelper;
    private final StoreBlockRepository storeBlockRepository;
    private final PlayerBlockRepository playerBlockRepository;
    private final PlayerBlockMapper playerBlockMapper;

    //블록 구매 시 메소드
    //살 수 있는 경우에는 돈 계산, 블록은 산 블록에 대한 정보
    //만약에 살 수 없는 경우에는 돈은 그대로, 블록에 대한 정보는 null로 보냄
    @Transactional
    public PurchaseBlockDto purchaseBlock(UUID playerId, Integer slotNumber) {
        Game game = gameServiceHelper.getGame(playerId);
        Store store = gameServiceHelper.getStore(game);
        StoreBlock storeBlock = storeBlockRepository.findByStoreAndSlotNumber(store, slotNumber).orElseThrow(
                () -> new CustomException(STORE_BLOCK_NOT_FOUND)
        ); //슬롯 넘버는 컨트롤러 쪽에서 유효성 체크
        PlayerBlockDto playerBlockDto = null;

        Integer playerMoney = store.getMoney();
        Integer blockMoney = getBlockMoney();
        boolean canBuy = false;

        if(playerMoney >= blockMoney) {
            canBuy = true;
            //돈 계산
            playerMoney -= blockMoney;
            store.setMoney(playerMoney);
            //플레이어 블록에 추가
            PlayerBlock playerBlock = playerBlockRepository.save(new PlayerBlock(game, storeBlock.getBlockShape(), storeBlock.getBlockColor()));
            //mapper를 이용해서 dto로 변환
            playerBlockDto = playerBlockMapper.playerBlockToPlayerBlockDto(playerBlock);

            //상점에 블록 삭제
            storeBlockRepository.delete(storeBlock);
        }
        return new PurchaseBlockDto(canBuy, playerMoney, playerBlockDto);
    }
}
