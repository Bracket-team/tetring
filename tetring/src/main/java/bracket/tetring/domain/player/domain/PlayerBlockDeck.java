package bracket.tetring.domain.player.domain;

import bracket.tetring.domain.game.domain.BlockColor;
import bracket.tetring.domain.game.domain.Game;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@IdClass(PlayerBlockDeckId.class)
@Table(name = "PLAYER_BLOCK_DECK")
@Getter
@Setter
public class PlayerBlockDeck {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long blockId;

    @Id
    @ManyToOne
    @JoinColumn(name = "GAME_ID")
    private Game game;

    @Column(nullable = false)
    private String blockShape;

    @Column(nullable = false)
    private BlockColor blockColor;

    public PlayerBlockDeck() {
    }

    public PlayerBlockDeck(Game game, String blockShape, BlockColor blockColor) {
        this.game = game;
        this.blockShape = blockShape;
        this.blockColor = blockColor;
    }
}
