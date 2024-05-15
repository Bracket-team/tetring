package bracket.tetring.domain.store.domain;

import bracket.tetring.domain.game.domain.Relic;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@IdClass(StoreRelicId.class)
@Table(name = "store_relics")
@Getter
@Setter
public class StoreRelic {

    @Id
    private Integer slotNumber;

    @Id
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "relic_number")
    private Relic relic;

    public StoreRelic() {
    }

    public StoreRelic(Store store, Relic relic, Integer slotNumber) {
        this.store = store;
        this.relic = relic;
        this.slotNumber = slotNumber;
    }
}
