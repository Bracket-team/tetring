package bracket.tetring.domain.game.repository;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.player.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface GameRepository extends JpaRepository<Game, UUID> {
    @Query("SELECT g.gameId FROM Game g WHERE g.player.playerId = :playerId")
    Optional<UUID> findGameIdByPlayerId(@Param("playerId") String playerId);

    @Query("SELECT g FROM Game g WHERE g.player.playerId = :playerId")
    Optional<Game> findGameByPlayerId(@Param("playerId") String playerId);

    Optional<Game> findByPlayer(Player player);
}
