package bracket.tetring.domain.player.domain;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.domain.Relic;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@IdClass(PlayerRelicId.class)
@Table(name = "player_relics")
@Getter
@Setter
public class PlayerRelic {

    @Id
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Id
    @Column(nullable = false)
    @JsonProperty("slot_number")
    private Integer slotNumber;

    @ManyToOne
    @JoinColumn(name = "relic_number")
    private Relic relic;

    public PlayerRelic() {
    }

    public PlayerRelic(Game game, Relic relic, Integer slotNumber) {
        this.game = game;
        this.relic = relic;
        this.slotNumber = slotNumber;
    }
}
