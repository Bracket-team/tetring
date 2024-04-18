package bracket.tetring.domain.game.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import bracket.tetring.domain.game.domain.Rarity;
import bracket.tetring.domain.game.domain.Relic;
import bracket.tetring.domain.game.dto.RelicCheckDto;
import bracket.tetring.domain.game.repository.RelicRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
class RelicServiceTest {

    @Mock
    private RelicRepository relicRepository;

    @InjectMocks
    private RelicService relicService;

//    @Test
//    @DisplayName("유물 조회")
//    public void testGetAllRelics() {
//        // Given
//        Relic relic1 = new Relic();
//        relic1.setRelicNumber(1);
//        relic1.setRelicName("Sword of Truth");
//        relic1.setRarity(Rarity.NORMAL);
//        relic1.setPrice(100);
//        relic1.setEffect("Grants the wielder the ability to see through lies.");
//
//        Relic relic2 = new Relic();
//        relic2.setRelicNumber(2);
//        relic2.setRelicName("Staff of Wisdom");
//        relic2.setRarity(Rarity.RARE);
//        relic2.setPrice(200);
//        relic2.setEffect("Enhances the wielder's intellect and grants profound insights.");
//
//        List<Relic> relics = new ArrayList<>();
//        relics.add(relic1);
//        relics.add(relic2);
//
//        // Mock Repository의 findAll() 메서드가 호출될 때 반환할 데이터 설정
//        when(relicRepository.findAll()).thenReturn(relics);
//
//        // When
//        List<Relic> foundRelics = relicService.getAllRelics();
//
//        // Then
//        assertThat(foundRelics).isNotNull();
//        assertThat(foundRelics.size()).isEqualTo(relics.size());
//        assertThat(foundRelics.get(0).getRelicName()).isEqualTo(relic1.getRelicName());
//        assertThat(foundRelics.get(1).getRelicName()).isEqualTo(relic2.getRelicName());
//    }
}