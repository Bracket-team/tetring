package bracket.tetring.domain.player.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "players")
@NoArgsConstructor
@AllArgsConstructor
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

    @Column
    private String picture;

    @Enumerated(EnumType.STRING) // Enum 값 저장
    @Column(nullable = false)
    private Role role;

    @Builder // Builder Pattern 사용
    public Player(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    // update 함수 구현
    public Player update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
