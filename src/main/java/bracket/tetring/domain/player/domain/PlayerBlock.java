package bracket.tetring.domain.player.domain;

import bracket.tetring.domain.game.domain.BlockColor;
import bracket.tetring.domain.game.domain.Game;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PLAYER_BLOCK")
@Getter
@Setter
public class PlayerBlock {

    @JsonProperty("block_id")
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long blockId;

    @ManyToOne
    @JoinColumn(name = "GAME_ID")
    private Game game;

    @JsonProperty("block_shape")
    @Column(nullable = false)
    private String blockShape;

    @JsonProperty("block_color")
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
