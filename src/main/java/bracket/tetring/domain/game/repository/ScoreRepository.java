package bracket.tetring.domain.game.repository;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.domain.Score;
import bracket.tetring.domain.game.domain.ScoreId;
import bracket.tetring.domain.player.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, ScoreId> {
    @Query("SELECT s FROM Score s ORDER BY s.score DESC, s.stage DESC LIMIT 50")
    List<Score> findAllRankings();

    boolean existsScoreByGameAndPlayer(Game game, Player player);
}
