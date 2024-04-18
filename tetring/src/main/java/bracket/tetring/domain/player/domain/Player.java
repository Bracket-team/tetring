package bracket.tetring.domain.player.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "PLAYERS")
@Getter
@Setter
public class Player {
    @Id
    private String playerId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    public Player() {
    }
    public Player(String playerId, String name, String email) {
        this.playerId = playerId;
        this.name = name;
        this.email = email;
    }
}
