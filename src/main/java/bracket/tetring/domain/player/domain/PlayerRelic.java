package bracket.tetring.domain.player.domain;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.domain.Relic;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@IdClass(PlayerRelicId.class)
@Table(name = "PLAYER_RELICS")
@Getter
@Setter
public class PlayerRelic {

    @Id
    @ManyToOne
    @JoinColumn(name = "GAME_ID")
    private Game game;

    @Id
    @Column(nullable = false)
    private Integer slotNumber;

    @ManyToOne
    @JoinColumn(name = "RELIC_NUMBER")
    private Relic relic;

    public PlayerRelic() {
    }

    public PlayerRelic(Game game, Relic relic, Integer slotNumber) {
        this.game = game;
        this.relic = relic;
        this.slotNumber = slotNumber;
    }
}
