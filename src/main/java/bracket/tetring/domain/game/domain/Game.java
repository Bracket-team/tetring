package bracket.tetring.domain.game.domain;

import bracket.tetring.domain.player.domain.Player;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "GAMES")
@Getter
@Setter
@ToString
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;

    @Column(nullable = false)
    private Integer stageNumber;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long stageBestScore;

    @Column(nullable = false)
    private Boolean isStore;

    @Column(nullable = false)
    private Boolean checkFinished;

    @ManyToOne
    @JoinColumn(name = "PLAYER_ID")
    private Player player;

    public Game() {
    }

    public Game(Integer stageNumber, Long stageBestScore, Boolean isStore, Boolean checkFinished, Player player) {
        this.stageNumber = stageNumber;
        this.stageBestScore = stageBestScore;
        this.isStore = isStore;
        this.checkFinished = checkFinished;
        this.player = player;
    }
}
