package bracket.tetring.domain.game.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "RELICS")
@Getter
@Setter
@ToString
public class Relic {

    @Id
    private Integer relicNumber; //유물 번호

    @Column(nullable = false, length = 20)
    private String relicName; //유물 이름

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Rarity rarity; //희귀도

    @Lob
    private String effect; //효과 설명

    @Column(nullable = false)
    private Integer price; //가격

    @Column
    private Integer coef; //계수

    public Relic() {
    }

    public Relic(Integer relicNumber, String relicName, Rarity rarity, Integer price, String effect, Integer coef) {
        this.relicNumber = relicNumber;
        this.relicName = relicName;
        this.rarity = rarity;
        this.price = price;
        this.effect = effect;
        this.coef = coef;
    }
}
