package bracket.tetring.domain.game.repository;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    private Player testPlayer;

    private Game testGame;

//    @BeforeEach
//    public void setUp() {
//        // Player 데이터 준비
//        testPlayer = new Player(123L, "admin", "427shg@gmali.com");
//        playerRepository.save(testPlayer);
//
//        // Game 데이터 준비
//        testGame = new Game(UUID.randomUUID(), 1, 100, false, true, testPlayer);
//        gameRepository.save(testGame);
//    }
//
//    @Test
//    public void testFindGameIdByPlayerId() {
//        Long playerId = testPlayer.getPlayerId();
//        Optional<UUID> optionalGameId = gameRepository.findGameIdByPlayerId(playerId);
//        assertTrue(optionalGameId.isPresent());
//        UUID gameId = optionalGameId.get();
//        assertEquals(testGame.getGameId(), gameId);
//    }

}