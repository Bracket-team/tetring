package bracket.tetring.domain.game.service;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.domain.Score;
import bracket.tetring.domain.game.dto.GameOverDto;
import bracket.tetring.domain.game.mapper.GameOverMapper;
import bracket.tetring.domain.game.repository.ScoreRepository;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.domain.PlayerRelic;
import bracket.tetring.domain.player.repository.PlayerBlockRepository;
import bracket.tetring.domain.player.repository.PlayerRelicRepository;
import bracket.tetring.domain.store.domain.Store;
import bracket.tetring.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static bracket.tetring.global.error.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class GameOverService {

    private final GameServiceHelper gameServiceHelper;
    private final PlayerBlockRepository playerBlockRepository;
    private final PlayerRelicRepository playerRelicRepository;
    private final ScoreRepository scoreRepository;
    private final GameOverMapper gameOverMapper;

    //게임 종료 시 결과 정보 전달
    //해당 기능을 사용할 시 게임 종료 처리
    @Transactional
    public GameOverDto getGameResult(UUID playerId) {
        Game game = gameServiceHelper.getGame(playerId);
        Store store = gameServiceHelper.getStore(game);

        game.setCheckFinished(true);

        saveScore(playerId, game); //게임 종료시 점수 저장

        //블록 총 개수 전송
        Integer blockCount = playerBlockRepository.countPlayerBlockByGame(game);
        //유물 목록 반환
        List<PlayerRelic> playerRelics = playerRelicRepository.findAllByGameOrderBySlotNumberAsc(game);

        //mapper 사용해서 게임 결과 화면 전송
        return gameOverMapper.toGameOverDto(game, store, blockCount, playerRelics);
    }

    //플레이어가 게임 그만두기 할 시
    @Transactional
    public GameOverDto quitGame(UUID playerId) {
        return getGameResult(playerId);
    }

    private void saveScore(UUID playerId, Game game) {
        Player player = gameServiceHelper.getPlayer(playerId);
        if(scoreRepository.existsScoreByGameAndPlayer(game, player)) {
            throw new CustomException(SCORE_ALREADY_SAVED);
        }

        Score score = new Score(game, player, game.getStageBestScore(), game.getStageNumber());
        scoreRepository.save(score);
    }
}
