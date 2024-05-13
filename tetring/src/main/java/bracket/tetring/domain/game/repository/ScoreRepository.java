package bracket.tetring.domain.game.repository;

import bracket.tetring.domain.game.domain.Score;
import bracket.tetring.domain.game.domain.ScoreId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, ScoreId> {
}
