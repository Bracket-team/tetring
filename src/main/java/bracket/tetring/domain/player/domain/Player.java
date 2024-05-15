package bracket.tetring.domain.player.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "players")
@Getter
@Setter
public class Player {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    @JsonProperty("player_id")
    private UUID playerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    public Player() {
    }

    public Player(String name, String email) {
        this.name = name;
        this.email = email;
    }

}
