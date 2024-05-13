package bracket.tetring.domain.game.web;

import bracket.tetring.domain.game.dto.EndStageDto;
import bracket.tetring.domain.game.dto.ScoreDto;
import bracket.tetring.domain.game.dto.StartStageDto;
import bracket.tetring.domain.game.service.StageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/games/{player-id}/stage")
public class StageController {
    private final StageService stageService;

    @GetMapping("/start")
    public ResponseEntity<StartStageDto> getStage(@PathVariable("player-id") UUID playerId) {
        StartStageDto startStage = stageService.playerStartStage(playerId);
        return ResponseEntity.ok(startStage);
    }

    @PostMapping("/end")
    public ResponseEntity<EndStageDto> endStage(@PathVariable("player-id") UUID playerId, @Valid @RequestBody ScoreDto score) {
        EndStageDto endStage = stageService.playerEndStage(playerId, score.getScore());
        return ResponseEntity.ok(endStage);
    }
}
