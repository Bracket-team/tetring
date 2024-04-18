package bracket.tetring.domain.player.domain;

import bracket.tetring.domain.game.domain.Relic;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@IdClass(PlayerRelicFoundId.class)
@Table(name = "PLAYER_RELIC_FOUND")
@Getter
@Setter
public class PlayerRelicFound {

    @Id
    @ManyToOne
    @JoinColumn(name = "PLAYER_ID")
    private Player player;

    @Id
    @ManyToOne
    @JoinColumn(name = "RELIC_NUBMER")
    private Relic relic;

    @Column(nullable = false)
    private boolean found;

    public PlayerRelicFound() {
    }

    public PlayerRelicFound(Player player, Relic relic, boolean found) {
        this.player = player;
        this.relic = relic;
        this.found = found;
    }
}
