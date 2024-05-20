package bracket.tetring.domain.player.repository;

import bracket.tetring.domain.game.domain.Rarity;
import bracket.tetring.domain.game.domain.Relic;
import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.player.domain.PlayerRelicFound;
import bracket.tetring.domain.player.domain.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PlayerRelicFoundRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PlayerRelicFoundRepository playerRelicFoundRepository;

    @Test
    @DisplayName("특정 플레이어의 유물 발견 목록을 조회한다.")
    public void findByPlayer() {
        // given
        Player player = new Player("TestPlayer", "test@player.com", null, Role.USER);
        entityManager.persist(player);

        Relic relic1 = new Relic(1, "RelicOne", Rarity.NORMAL, 10, "EffectOne", null);
        Relic relic2 = new Relic(2, "RelicTwo", Rarity.RARE, 20, "EffectTwo", null);
        entityManager.persist(relic1);
        entityManager.persist(relic2);

        PlayerRelicFound playerRelicFound1 = new PlayerRelicFound(player, relic1, true);
        PlayerRelicFound playerRelicFound2 = new PlayerRelicFound(player, relic2, false);
        entityManager.persist(playerRelicFound1);
        entityManager.persist(playerRelicFound2);

        // when
        List<PlayerRelicFound> foundRelics = playerRelicFoundRepository.findByPlayer(player);

        // then
        assertThat(foundRelics).hasSize(2);
        assertThat(foundRelics).extracting("found").containsOnly(true, false);
        assertThat(foundRelics).extracting("relic").containsExactlyInAnyOrder(relic1, relic2);
    }
}