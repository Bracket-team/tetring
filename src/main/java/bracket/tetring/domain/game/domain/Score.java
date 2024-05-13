package bracket.tetring.domain.game.domain;

import bracket.tetring.domain.player.domain.Player;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@IdClass(ScoreId.class)
@Table(name = "SCORES")
@Getter
@Setter
public class Score {

    @Id
    @OneToOne
    @JoinColumn(name = "GAME_ID")
    private Game game;

    @Id
    @ManyToOne
    @JoinColumn(name = "PLAYER_ID")
    private Player player;

    @Column(nullable = false)
    @ColumnDefault(value = "0")
    private Long score;

    @Column(nullable = false)
    @ColumnDefault(value = "1")
    private Integer stage;

    public Score() {
    }

    public Score(Game game, Player player, Long score, Integer stage) {
        this.game = game;
        this.player = player;
        this.score = score;
        this.stage = stage;
    }
}
