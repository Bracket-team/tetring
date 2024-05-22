package bracket.tetring.domain.player.domain;

import bracket.tetring.domain.game.domain.Relic;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@IdClass(PlayerRelicFoundId.class)
@Table(name = "player_relic_found")
@Getter
@Setter
public class PlayerRelicFound {

    @Id
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Id
    @ManyToOne
    @JoinColumn(name = "relic_number")
    private Relic relic;

    public PlayerRelicFound() {
    }

    public PlayerRelicFound(Player player, Relic relic) {
        this.player = player;
        this.relic = relic;
    }
}
