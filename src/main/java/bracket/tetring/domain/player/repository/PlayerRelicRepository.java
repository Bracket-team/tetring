package bracket.tetring.domain.player.repository;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.domain.Relic;
import bracket.tetring.domain.player.domain.PlayerRelic;
import bracket.tetring.domain.player.domain.PlayerRelicId;
import org.hibernate.sql.Delete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlayerRelicRepository extends JpaRepository<PlayerRelic, PlayerRelicId> {
    List<PlayerRelic> findAllByGameOrderBySlotNumberAsc(Game game);

    @Modifying
    @Query("DELETE FROM PlayerRelic r WHERE r.game = :game and r.slotNumber = :slotNumber")
    void deletePlayerRelic(@Param("game") Game game, @Param("slotNumber") Integer slotNumber);

    @Query("SELECT COUNT(pr) > 0 FROM PlayerRelic pr WHERE pr.game = :game AND pr.relic.relicNumber = :relicNumber")
    boolean existsByGameAndRelicNumber(@Param("game") Game game, @Param("relicNumber") Integer relicNumber);

    boolean existsByGameAndSlotNumber(Game game, Integer slotNumber);

    @Query("SELECT pr.slotNumber FROM PlayerRelic pr WHERE pr.game = :game")
    List<Integer> findSlotNumbersByGame(@Param("game") Game game);

    @Query("SELECT pr.relic.relicNumber FROM PlayerRelic pr WHERE pr.game = :game AND pr.slotNumber = :slotNumber")
    Optional<Integer> getRelicNumberByGameAndSlotNumber(@Param("game") Game game, @Param("slotNumber") Integer slotNumber);

    @Query("SELECT pr FROM PlayerRelic pr WHERE pr.game = :game AND pr.relic.relicNumber = :relicNumber")
    Optional<PlayerRelic> getPlayerRelicByGameAndRelicNumber(@Param("game") Game game, @Param("relicNumber") Integer relicNumber);
}
