package bracket.tetring.domain.player.service;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.service.GameServiceHelper;
import bracket.tetring.domain.player.domain.PlayerBlock;
import bracket.tetring.domain.player.dto.PlayerBlockDto;
import bracket.tetring.domain.player.mapper.PlayerBlockMapper;
import bracket.tetring.domain.player.repository.PlayerBlockRepository;
import bracket.tetring.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static bracket.tetring.global.error.ErrorCode.PLAYER_BLOCK_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerBlockService {

    private final GameServiceHelper gameServiceHelper;
    private final PlayerBlockMapper playerBlockMapper;
    private final PlayerBlockRepository playerBlockRepository;

    //플레이어가 가지고 있는 블록 정보들 반환하는 메소드
    @Transactional
    public List<PlayerBlockDto> getPlayerBlocks(UUID playerId) {
        Game game = gameServiceHelper.getGame(playerId);
        //플레이 블록 목록들 가져오기
        List<PlayerBlock> playerBlocks = playerBlockRepository.findAllByGameOrderByBlockIdAsc(game);
        //mapper 이용해서 dto로 변환
        return playerBlockMapper.playerBlocksToPlayerBlockDtos(playerBlocks);
    }

    //블록 모양 변형할 때 사용하는 메소드
    @Transactional
    public void changeBlock(UUID playerId, PlayerBlockDto playerBlockDto) {
        Game game = gameServiceHelper.getGame(playerId);

        //바뀐 블록 모양을 저장
        PlayerBlock playerBlock = playerBlockRepository.findByGameAndBlockId(game, playerBlockDto.getBlockId()).orElseThrow(
                () -> new CustomException(PLAYER_BLOCK_NOT_FOUND)
        );

        playerBlock.setBlockShape(playerBlockDto.getBlockShape());
        playerBlock.setBlockColor(playerBlockDto.getBlockColor());
    }
}
