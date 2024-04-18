package bracket.tetring.domain.store.web;

import bracket.tetring.domain.game.dto.SlotNumberDto;
import bracket.tetring.domain.store.dto.PurchaseBlockDto;
import bracket.tetring.domain.store.dto.StoreBlockDto;
import bracket.tetring.domain.store.service.StoreBlockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores/{player-id}/blocks")
public class StoreBlockController {

    private final StoreBlockService storeBlockService;

    @PostMapping
    public ResponseEntity<PurchaseBlockDto> buyStoreBlock(@PathVariable("player-id") UUID playerId, @Valid @RequestBody SlotNumberDto slotNumber) {
        PurchaseBlockDto purchaseBlockDto = storeBlockService.purchaseBlock(playerId, slotNumber.getSlotNumber());
        return ResponseEntity.ok(purchaseBlockDto);
    }
}
