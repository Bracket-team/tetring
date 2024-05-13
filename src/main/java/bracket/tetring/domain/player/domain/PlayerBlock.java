package bracket.tetring.domain.player.domain;

import bracket.tetring.domain.game.domain.BlockColor;
import bracket.tetring.domain.game.domain.Game;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PLAYER_BLOCK")
@Getter
@Setter
public class PlayerBlock {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long blockId;

    @ManyToOne
    @JoinColumn(name = "GAME_ID")
    private Game game;

    @Column(nullable = false)
    private String blockShape;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BlockColor blockColor;

    public PlayerBlock() {
    }

    public PlayerBlock(Game game, String blockShape, BlockColor blockColor) {
        this.game = game;
        this.blockShape = blockShape;
        this.blockColor = blockColor;
    }
}
