package bracket.tetring.domain.store.repository;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.store.domain.Store;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByGame(Game game);
}
