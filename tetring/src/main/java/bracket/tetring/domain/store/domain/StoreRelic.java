package bracket.tetring.domain.store.domain;

import bracket.tetring.domain.game.domain.Relic;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@IdClass(StoreRelicId.class)
@Table(name = "STORE_RELICS")
@Getter
@Setter
public class StoreRelic {

    @Id
    private Integer slotNumber;

    @Id
    @ManyToOne
    @JoinColumn(name = "STORE_ID")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "RELIC_NUMBER")
    private Relic relic;


    public StoreRelic() {
    }

    public StoreRelic(Store store, Relic relic, Integer slotNumber) {
        this.store = store;
        this.relic = relic;
        this.slotNumber = slotNumber;
    }
}
