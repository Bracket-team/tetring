package bracket.tetring.domain.store.web;

import bracket.tetring.domain.game.dto.SlotNumberDto;
import bracket.tetring.domain.store.dto.PurchaseRelicDto;
import bracket.tetring.domain.store.dto.RerollRelicsDto;
import bracket.tetring.domain.store.service.StoreRelicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores/{player-id}/relics")
public class StoreRelicController {

    private final StoreRelicService storeRelicService;

    @PostMapping
    public ResponseEntity<PurchaseRelicDto> purchaseRelic(@PathVariable("player-id") UUID playerId, @Valid @RequestBody SlotNumberDto slotNumber) {
        PurchaseRelicDto purchaseResult = storeRelicService.purchaseRelic(playerId, slotNumber.getSlotNumber());
        return ResponseEntity.ok(purchaseResult);
    }

    @GetMapping
    public ResponseEntity<RerollRelicsDto> rerollRelics(@PathVariable("player-id") UUID playerId) {
        RerollRelicsDto rerollResult = storeRelicService.rerollRelics(playerId);
        return ResponseEntity.ok(rerollResult);
    }
}
