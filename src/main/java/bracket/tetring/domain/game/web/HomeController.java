package bracket.tetring.domain.game.web;

import bracket.tetring.domain.game.dto.GameDetailsDto;
import bracket.tetring.domain.game.dto.RankingDto;
import bracket.tetring.domain.game.service.GameStartService;
import bracket.tetring.domain.game.service.RankingService;
import bracket.tetring.domain.player.dto.PlayerRelicFoundDto;
import bracket.tetring.domain.player.service.PlayerRelicFoundService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final PlayerRelicFoundService playerRelicFoundService;
    private final RankingService rankingService;
    private final GameStartService gameStartService;

    @GetMapping("/{player-id}/relics")
    public ResponseEntity<List<PlayerRelicFoundDto>> getRelicFound(@PathVariable("player-id") UUID playerId) {
        List<PlayerRelicFoundDto> relics = playerRelicFoundService.findRelicsInfoByPlayerId(playerId);
        return ResponseEntity.ok(relics);
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<RankingDto>> getRankings() {
        List<RankingDto> rankings = rankingService.getRankings();
        return ResponseEntity.ok(rankings);
    }

    @GetMapping("/{player-id}/game/check")
    public ResponseEntity<Boolean> checkFinished(@PathVariable("player-id") UUID playerId) {
        boolean isGaming = gameStartService.isPlayerGaming(playerId);
        return ResponseEntity.ok(isGaming);
    }

    @GetMapping("/{player-id}/game")
    public ResponseEntity<GameDetailsDto> getGameDetails(@PathVariable("player-id") UUID playerId) {
        GameDetailsDto gameDetails = gameStartService.getGameDetailsForPlayers(playerId);
        return ResponseEntity.ok(gameDetails);
    }
}
