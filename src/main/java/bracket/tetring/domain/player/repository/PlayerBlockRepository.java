package bracket.tetring.domain.player.repository;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.player.domain.PlayerBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlayerBlockRepository extends JpaRepository<PlayerBlock, Long> {
    List<PlayerBlock> findAllByGameOrderByBlockIdAsc(Game game);

    @Query("select count(pb) from PlayerBlock pb where pb.game = :game")
    Integer countPlayerBlockByGame(@Param("game") Game game);


    @Query("select pb from PlayerBlock pb where pb.game = :game and pb.blockId = :blockId")
    Optional<PlayerBlock> findByGameAndBlockId(@Param("game") Game game, @Param("blockId") Long blockId);
}
