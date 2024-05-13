package bracket.tetring.domain.game.web;

import bracket.tetring.domain.game.dto.GameOverDto;
import bracket.tetring.domain.game.service.GameOverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/games/{player-id}")
public class GameController {

    private final GameOverService gameOverService;

    @GetMapping("/result")
    public ResponseEntity<GameOverDto> getGameOver(@PathVariable("player-id") UUID playerId) {
        log.info("게임 결과 조회 시작: playerId={}", playerId);
        GameOverDto gameOverDto = gameOverService.getGameResult(playerId);
        return new ResponseEntity<>(gameOverDto, HttpStatus.OK);
    }

    @GetMapping("/quit")
    public ResponseEntity<GameOverDto> quitGame(@PathVariable("player-id") UUID playerId) {
        GameOverDto gameOverDto = gameOverService.quitGame(playerId);
        return new ResponseEntity<>(gameOverDto, HttpStatus.OK);
    }
}
