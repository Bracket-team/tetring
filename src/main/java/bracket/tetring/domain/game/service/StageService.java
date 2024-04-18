package bracket.tetring.domain.game.service;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.dto.EndStageDto;
import bracket.tetring.domain.game.dto.StartStageDto;
import bracket.tetring.domain.game.mapper.StageMapper;
import bracket.tetring.domain.game.repository.GameRepository;
import bracket.tetring.domain.game.repository.RelicRepository;
import bracket.tetring.domain.player.domain.PlayerBlock;
import bracket.tetring.domain.player.domain.PlayerRelic;
import bracket.tetring.domain.player.dto.PlayerBlockDto;
import bracket.tetring.domain.player.dto.PlayerRelicDto;
import bracket.tetring.domain.player.repository.PlayerBlockRepository;
import bracket.tetring.domain.player.repository.PlayerRelicRepository;
import bracket.tetring.domain.store.domain.Store;
import bracket.tetring.domain.store.repository.StoreRepository;
import bracket.tetring.domain.store.service.StoreService;
import bracket.tetring.global.error.ErrorCode;
import bracket.tetring.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static bracket.tetring.global.util.CalculateSystem.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class StageService {

    private final GameServiceHelper gameServiceHelper;
    private final PlayerBlockRepository playerBlockRepository;
    private final PlayerRelicRepository playerRelicRepository;
    private final RelicRepository relicRepository;
    private final StoreService storeService;
    private final StageMapper stageMapper;

    @Transactional
    public StartStageDto playerStartStage(UUID playerId) {
        log.info("플레이어 스테이지 시작. 플레이어 아이디={}", playerId);
        Game game = gameServiceHelper.getGame(playerId);
        Store store = gameServiceHelper.getStore(game);

        if(!(game.getIsStore())) {
            throw new CustomException(ErrorCode.PLAYER_ALREADY_PLAYING_STAGE);
        }

        store.setRelicRerollTime(0); //스테이지 시작 시 리롤 회수 초기화
        game.setIsStore(false); //상점 아니라는 표시

        //스테이지 시작 전 사용자 블록, 유물 정보 전송
        List<PlayerBlock> playerBlocks = playerBlockRepository.findAllByGameOrderByBlockIdAsc(game);
        List<PlayerRelic> playerRelics = playerRelicRepository.findAllByGameOrderBySlotNumberAsc(game);

        return stageMapper.toStartStageDto(game, store, playerBlocks, playerRelics); //스테이지 정보 담아서 반환
    }

    @Transactional
    public EndStageDto playerEndStage(UUID playerId, Long playerScore) {
        log.info("플레이어 스테이지 종료. 플레이어 아이디={}", playerId);
        Game game = gameServiceHelper.getGame(playerId);
        Store store = gameServiceHelper.getStore(game);

        if(game.getIsStore()) {
            throw new CustomException(ErrorCode.PLAYER_ALREADY_IS_STORE);
        }
        Integer stageNumber = game.getStageNumber();
        Integer money = store.getMoney();

        //게임에서 최고 점수일 경우 점수 갱신
        checkBestScore(playerScore, game);

        //승리 여부 판단
        boolean win = false;
        if (playerScore >= getStageGoal(game.getStageNumber())) {
            win = true;
            //이겼을 시 다음 스테이지로 설정
            stageNumber += 1;
            game.setStageNumber(stageNumber);
            //이겼을 시 돈 획득
            boolean existsInvestRelic = relicRepository.existsById(20);
            money = getTotalMoney(store.getMoneyLevel(), money, existsInvestRelic);
            store.setMoney(money);
            // 스테이지 끝나면 상점 갈 것이기에 상점 미리 불러서 상점 정보 초기화
            storeService.getStoreInfo(playerId);
        } else {
            //졌을 때 게임 끝난 걸로 처리
            game.setCheckFinished(true);
        }
        return stageMapper.toEndStageDto(win, stageNumber, money);
    }

    private void checkBestScore(Long playerScore, Game game) {
        if(playerScore > game.getStageBestScore())
            game.setStageBestScore(playerScore);
    }
}
