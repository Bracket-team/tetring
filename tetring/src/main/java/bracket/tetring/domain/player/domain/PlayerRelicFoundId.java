package bracket.tetring.domain.player.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class PlayerRelicFoundId implements Serializable {
    private Long player;
    private Integer relic;

    public PlayerRelicFoundId() {
    }

    public PlayerRelicFoundId(Long player, Integer relic) {
        this.player = player;
        this.relic = relic;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayer(), getRelic());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerRelicFoundId that)) return false;
        return Objects.equals(getPlayer(), that.getPlayer()) &&
                Objects.equals(getRelic(), that.getRelic());
    }

}
