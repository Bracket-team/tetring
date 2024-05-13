package bracket.tetring.domain.game.service;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.repository.GameRepository;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.repository.PlayerRepository;
import bracket.tetring.domain.store.domain.Store;
import bracket.tetring.domain.store.repository.StoreRepository;
import bracket.tetring.global.error.ErrorCode;
import bracket.tetring.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

import static bracket.tetring.global.error.ErrorCode.*;

@Component
@RequiredArgsConstructor
@Transactional
public class GameServiceHelper {
    private final GameRepository gameRepository;
    private final StoreRepository storeRepository;
    private final PlayerRepository playerRepository;

    public Game getGame(UUID playerId) {
        return gameRepository.findPlayingGameByPlayerId(playerId)
                .orElseThrow(() -> new CustomException(GAME_NOT_FOUND));
    }

    public Store getStore(Game game) {
        return storeRepository.findByGame(game)
                .orElseThrow(() -> new CustomException(STORE_NOT_FOUND));
    }

    public Player getPlayer(UUID playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new CustomException(PLAYER_NOT_FOUND));
    }
}
