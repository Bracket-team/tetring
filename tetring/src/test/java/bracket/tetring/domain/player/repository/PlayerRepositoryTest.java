package bracket.tetring.domain.player.repository;

import bracket.tetring.domain.player.domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

//    @BeforeEach
//    public void setUp() {
//        Player player1 = new Player(123L, "admin", "427shg@gmali.com");
//        Player player2 = new Player(12L, "person", "427shg@gmali.com");
//        playerRepository.save(player1);
//        playerRepository.save(player2);
//    }
//
//    @Test
//    public void testFindAll() {
//        List<Player> players = playerRepository.findAll();
//        assertNotNull(players);
//        assertEquals(2, players.size());
//    }
//
//    @Test
//    public void testFindByUsername() {
//        Optional<Player> optionalUser = Optional.ofNullable(playerRepository.findByName("admin"));
//        Player player = optionalUser.orElse(null);
//        assertNotNull(player);
//        assertEquals("admin", player.getName());
//    }
}