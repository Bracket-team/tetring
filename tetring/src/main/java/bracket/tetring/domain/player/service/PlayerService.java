package bracket.tetring.domain.player.service;

import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.domain.PlayerRelicFound;
import bracket.tetring.domain.player.dto.PlayerRelicFoundDto;
import bracket.tetring.domain.player.repository.PlayerRelicFoundRepository;
import bracket.tetring.domain.player.repository.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRelicFoundRepository playerRelicFoundRepository;
    private final PlayerRepository playerRepository;

    public List<PlayerRelicFoundDto> findRelicsByPlayerId(String playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new EntityNotFoundException("해당 유저는 없습니다.: " + playerId));

        // 조회된 Player 엔티티를 사용하여 PlayerRelicFound 엔티티를 조회
        List<PlayerRelicFound> foundRelics = playerRelicFoundRepository.findByPlayer(player);

        // 결과 변환 로직
        return foundRelics.stream()
                .map(relicFound -> new PlayerRelicFoundDto(
                        relicFound.getRelic().getRelicNumber(),
                        relicFound.getRelic().getRelicName(),
                        relicFound.getRelic().getRarity().toString(),
                        relicFound.getRelic().getEffect(),
                        relicFound.getRelic().getPrice(),
                        true))
                .collect(Collectors.toList());
    }
}
