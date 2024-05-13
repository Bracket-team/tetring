package bracket.tetring.domain.player.domain;

import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.domain.Relic;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class PlayerRelicId implements Serializable {

    private Long game;
    private Integer slotNumber;

    public PlayerRelicId() {
    }

    public PlayerRelicId(Long game, Integer slotNumber) {
        this.game = game;
        this.slotNumber = slotNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGame(), getSlotNumber());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerRelicId that)) return false;
        return Objects.equals(getGame(), that.getGame()) &&
                Objects.equals(getSlotNumber(), that.getSlotNumber());
    }
}
