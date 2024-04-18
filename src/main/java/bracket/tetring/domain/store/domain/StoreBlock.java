package bracket.tetring.domain.store.domain;

import bracket.tetring.domain.game.domain.BlockColor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@IdClass(StoreBlockId.class)
@Table(name = "STORE_BLOCKS")
@Getter
@Setter
public class StoreBlock {

    @Id
    @Column(nullable = false)
    private Integer slotNumber;

    @Id
    @ManyToOne
    @JoinColumn(name = "STORE_ID")
    private Store store;

    @Column(nullable = false, length = 20)
    private String blockShape;

    @Column(nullable = false)
    private BlockColor blockColor;

    public StoreBlock() {
    }

    public StoreBlock(Integer slotNumber, Store store, String blockShape, BlockColor blockColor) {
        this.slotNumber = slotNumber;
        this.store = store;
        this.blockShape = blockShape;
        this.blockColor = blockColor;
    }
}
