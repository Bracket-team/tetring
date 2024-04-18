package bracket.tetring.domain.player.web;

import bracket.tetring.domain.player.dto.PlayerBlockDto;
import bracket.tetring.domain.player.service.PlayerBlockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/games/{player-id}")
public class PlayerBlockController {

    private final PlayerBlockService playerBlockService;

    @GetMapping("/blocks")
    public ResponseEntity<List<PlayerBlockDto>> getPlayerBlocks(@PathVariable("player-id") UUID playerId) {
        List<PlayerBlockDto> blocks = playerBlockService.getPlayerBlocks(playerId);
        return ResponseEntity.ok(blocks);
    }

    @PutMapping("/blocks/change")
    public ResponseEntity<Void> changeBlock(@PathVariable("player-id") UUID playerId, @Valid @RequestBody PlayerBlockDto playerBlockDto) {
        playerBlockService.changeBlock(playerId, playerBlockDto);
        return ResponseEntity.ok().build();
    }
}
