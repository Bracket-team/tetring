package bracket.tetring.domain.player.service;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.service.GameServiceHelper;
import bracket.tetring.domain.player.domain.PlayerRelic;
import bracket.tetring.domain.player.dto.PlayerRelicDto;
import bracket.tetring.domain.player.mapper.PlayerRelicMapper;
import bracket.tetring.domain.player.repository.PlayerRelicRepository;
import bracket.tetring.domain.store.domain.Store;
import bracket.tetring.global.error.ErrorCode;
import bracket.tetring.global.exception.CustomException;
import bracket.tetring.global.util.RelicNameMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerRelicService {

    private final GameServiceHelper gameServiceHelper;

    private final PlayerRelicRepository playerRelicRepository;

    private final PlayerRelicMapper playerRelicMapper;

    //플레이어 전체 유물 조회
    @Transactional
    public List<PlayerRelicDto> getPlayerRelics(UUID playerId) {
        Game game = gameServiceHelper.getGame(playerId);
        List<PlayerRelic> playerRelics = playerRelicRepository.findAllByGameOrderBySlotNumberAsc(game);
        return playerRelicMapper.playerRelicsToPlayerRelicDtos(playerRelics);
    }

    //플레이어 유물 버리기
    @Transactional
    public void throwPlayerRelic(UUID playerId, Integer slotNumber) {
        Game game = gameServiceHelper.getGame(playerId);

        Integer relicNumber = playerRelicRepository.getRelicNumberByGameAndSlotNumber(game, slotNumber).orElseThrow(
                () -> new CustomException(ErrorCode.PLAYER_RELIC_NOT_FOUND)
        );
        //쿠폰 블록일 경우 리롤 횟수 -1
        if(relicNumber == RelicNameMapper.getRelicNumber("쿠폰 블록")) {
            Store store = gameServiceHelper.getStore(game);
            if(store.getRelicRerollTime() >= 1) {
                store.setRelicRerollTime(store.getRelicRerollTime() - 1);
            }
        }
        playerRelicRepository.deletePlayerRelic(game, slotNumber);
    }

    //플레이어 유물 확인
    @Transactional
    public boolean existsRelic(UUID playerId, Integer relicNumber) {
        Game game = gameServiceHelper.getGame(playerId);
        return playerRelicRepository.existsByGameAndRelicNumber(game, relicNumber);
    }
}
