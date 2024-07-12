package bracket.tetring.domain.store.service;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.player.repository.PlayerRelicRepository;
import bracket.tetring.domain.store.domain.Store;
import bracket.tetring.global.util.CalculateSystem;
import bracket.tetring.global.util.RelicNameMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class RerollPriceHelper {
    private final PlayerRelicRepository playerRelicRepository;

    public Integer getRerollPriceWithRelics(Game game, Store store) {
        boolean haveRerollBlock = playerRelicRepository.existsByGameAndRelicNumber(game, RelicNameMapper.getRelicNumber("리롤 블록"));
        boolean haveCouponBlock = playerRelicRepository.existsByGameAndRelicNumber(game, RelicNameMapper.getRelicNumber("쿠폰 블록"));
        int rerollTime = store.getRelicRerollTime();
        if(haveCouponBlock && !store.getUseCoupon()) {
            store.setUseCoupon(true);
            return 0;
        }
        int rerollPrice = CalculateSystem.getRerollPrice(rerollTime);
        if(haveRerollBlock) {
            rerollPrice -= 1;
        }
        return Math.max(rerollPrice, 0);
    }
}
