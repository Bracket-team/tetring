package bracket.tetring.domain.player.service;

import bracket.tetring.domain.game.service.GameServiceHelper;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.domain.PlayerRelicFound;
import bracket.tetring.domain.player.dto.PlayerRelicFoundDto;
import bracket.tetring.domain.player.mapper.PlayerRelicFoundMapper;
import bracket.tetring.domain.player.repository.PlayerRelicFoundRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerRelicFoundService {

    private final GameServiceHelper gameServiceHelper;

    private final PlayerRelicFoundRepository playerRelicFoundRepository;

    private final PlayerRelicFoundMapper playerRelicFoundMapper;

    //플레이어가 찾는 유물들 조회
    //플레이어마다 유물 목록 조회할 때 쓰임
    @Transactional
    public List<PlayerRelicFoundDto> getRelicFound(UUID playerId) {
        Player player = gameServiceHelper.getPlayer(playerId);

        // 조회된 Player 엔티티를 사용하여 PlayerRelicFound 엔티티를 조회
        List<PlayerRelicFound> foundRelics = playerRelicFoundRepository.findByPlayer(player);

        // mapper을 이용해서 dto로 변환
        return playerRelicFoundMapper.playerRelicFoundsToPlayerRelicFoundDtos(foundRelics);
    }
}
