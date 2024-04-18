package bracket.tetring.domain.store.web;

import bracket.tetring.domain.store.dto.LevelUpMoneySystemDto;
import bracket.tetring.domain.store.dto.RerollRelicsDto;
import bracket.tetring.domain.store.dto.StoreDetailsDto;
import bracket.tetring.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store/{player-id}")
public class StoreController {
    private final StoreService storeService;

    @GetMapping
    public ResponseEntity<StoreDetailsDto> getStoreDetails(@PathVariable("player-id") UUID playerId) {
        StoreDetailsDto storeDetails = storeService.getStoreInfo(playerId);
        return ResponseEntity.ok(storeDetails);
    }

    @GetMapping("/money")
    public ResponseEntity<Integer> getPlayerMoney(@PathVariable("player-id") UUID playerId) {
        Integer money = storeService.getPlayerMoney(playerId);
        return ResponseEntity.ok(money);
    }

    @PostMapping("/money/level-up")
    public ResponseEntity<LevelUpMoneySystemDto> levelUpMoneySystem(@PathVariable("player-id") UUID playerId) {
        LevelUpMoneySystemDto levelUpResult = storeService.levelUpMoneySystem(playerId);
        return ResponseEntity.ok(levelUpResult);
    }
}
