package bracket.tetring.domain.game.repository;

import bracket.tetring.domain.game.domain.Relic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelicRepository extends JpaRepository<Relic, Integer> {
}
