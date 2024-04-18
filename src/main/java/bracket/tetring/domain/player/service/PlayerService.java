package bracket.tetring.domain.player.service;

import bracket.tetring.domain.player.repository.PlayerRepository;
import bracket.tetring.global.error.ErrorCode;
import bracket.tetring.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerService {

    public final PlayerRepository playerRepository;

    @Transactional
    public UUID getPlayerId(String email) {
        return playerRepository.findByEmail(email).orElseThrow(
                () -> new CustomException(ErrorCode.PLAYER_NOT_FOUND)
        ).getPlayerId();
    }
}
