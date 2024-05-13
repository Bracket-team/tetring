package bracket.tetring.domain.player.web;

import bracket.tetring.domain.game.dto.RelicNumberDto;
import bracket.tetring.domain.game.dto.SlotNumberDto;
import bracket.tetring.domain.player.dto.PlayerRelicDto;
import bracket.tetring.domain.player.service.PlayerRelicService;
import bracket.tetring.global.error.ErrorCode;
import bracket.tetring.global.exception.CustomException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static bracket.tetring.global.error.ErrorCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/games/{player-id}/relics")
public class PlayerRelicController {

    private final PlayerRelicService playerRelicService;

    @GetMapping
    public ResponseEntity<List<PlayerRelicDto>> getPlayerRelics(@PathVariable("player-id") UUID playerId) {
        List<PlayerRelicDto> relics = playerRelicService.getPlayerRelics(playerId);
        return ResponseEntity.ok(relics);
    }

    @PostMapping("/check")
    public ResponseEntity<Boolean> existsRelic(@PathVariable("player-id") UUID playerId, @Valid @RequestBody RelicNumberDto relicNumber) {
        boolean exists = playerRelicService.existsRelic(playerId, relicNumber.getRelicNumber());
        return ResponseEntity.ok(exists);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteRelic(@PathVariable("player-id") UUID playerId, @Valid @RequestBody SlotNumberDto slotNumber) {
        playerRelicService.throwPlayerRelic(playerId, slotNumber.getSlotNumber());
        return ResponseEntity.ok().build();
    }
}
