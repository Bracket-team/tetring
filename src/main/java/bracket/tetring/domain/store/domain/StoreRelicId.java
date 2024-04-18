package bracket.tetring.domain.store.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class StoreRelicId implements Serializable {

    private Integer slotNumber;
    private Long store;

    public StoreRelicId() {
    }

    public StoreRelicId(Integer slotNumber, Long store) {
        this.slotNumber = slotNumber;
        this.store = store;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSlotNumber(), getStore());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StoreRelicId that)) return false;
        return Objects.equals(getSlotNumber(), that.getSlotNumber()) &&
                Objects.equals(getStore(), that.getStore());
    }
}
