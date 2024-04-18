package bracket.tetring.domain.game.domain;

import bracket.tetring.domain.player.domain.Player;
import bracket.tetring.domain.store.domain.Store;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "GAMES")
@Getter
@Setter
@ToString
public class Game {

    //게임 아이디 uuid로 자동 생성
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID gameId;

    @Column(nullable = false)
    private Integer stageNumber;

    @Column(nullable = false)
    private Integer stageBestScore;

    @Column(nullable = false)
    private Boolean checkStore;

    @Column(nullable = false)
    private Boolean checkFinished;

    @OneToOne
    @JoinColumn(name = "PLAYER_ID")
    private Player player;

    public Game() {
    }

    public Game(Integer stageNumber, Integer stageBestScore, Boolean checkStore, Boolean checkFinished, Player player) {
        this.stageNumber = stageNumber;
        this.stageBestScore = stageBestScore;
        this.checkStore = checkStore;
        this.checkFinished = checkFinished;
        this.player = player;
    }
}
