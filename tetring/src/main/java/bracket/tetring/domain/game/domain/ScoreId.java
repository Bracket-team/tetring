package bracket.tetring.domain.game.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class ScoreId implements Serializable {

    private UUID game;
    private Long player;

    public ScoreId() {
    }

    public ScoreId(UUID game, Long player) {
        this.game = game;
        this.player = player;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGame(), getPlayer());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScoreId that)) return false;
        return Objects.equals(getGame(), that.getGame()) &&
                Objects.equals(getPlayer(), that.getPlayer());
    }
}
