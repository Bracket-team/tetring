package bracket.tetring.domain.player.domain;

import bracket.tetring.domain.game.domain.BlockColor;
import bracket.tetring.domain.game.domain.Game;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "player_block")
@Getter
@Setter
public class PlayerBlock {

    @JsonProperty("block_id")
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long blockId;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(nullable = false)
    @JsonProperty("block_shape")
    private String blockShape;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @JsonProperty("block_color")
    private BlockColor blockColor;

    public PlayerBlock() {
    }

    public PlayerBlock(Game game, String blockShape, BlockColor blockColor) {
        this.game = game;
        this.blockShape = blockShape;
        this.blockColor = blockColor;
    }
}
