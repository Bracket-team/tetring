package bracket.tetring.domain.player.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class PlayerBlockDeckId implements Serializable {

    private Long blockId;
    private UUID game;

    public PlayerBlockDeckId() {
    }

    public PlayerBlockDeckId(Long blockId, UUID game) {
        this.blockId = blockId;
        this.game = game;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBlockId(), getGame());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerBlockDeckId that)) return false;
        return Objects.equals(getBlockId(), that.getBlockId()) &&
                Objects.equals(getGame(), that.getGame());
    }
}
