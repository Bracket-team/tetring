package bracket.tetring.domain.store.domain;

import bracket.tetring.domain.game.domain.Game;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "stores")
@Getter
@Setter
public class Store {

    @Id
    private Long gameId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(nullable = false)
    private Integer relicRerollTime;

    @Column(nullable = false)
    private Integer money;

    @Column(nullable = false)
    private Integer moneyLevel;

    public Store() {
    }

    public Store(Game game, Integer relicRerollTime, Integer money, Integer moneyLevel) {
        this.game = game;
        this.relicRerollTime = relicRerollTime;
        this.money = money;
        this.moneyLevel = moneyLevel;
    }
}
