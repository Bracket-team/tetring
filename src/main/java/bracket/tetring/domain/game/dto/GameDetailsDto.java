package bracket.tetring.domain.game.dto;

import bracket.tetring.domain.player.dto.PlayerBlockDto;
import bracket.tetring.domain.player.dto.PlayerRelicDto;
import bracket.tetring.domain.store.dto.StoreBlockDto;
import bracket.tetring.domain.store.dto.StoreDto;
import bracket.tetring.domain.store.dto.StoreRelicDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GameDetailsDto {
    private GameDto game;
    private List<PlayerBlockDto> playerBlock;
    private List<PlayerRelicDto> playerRelic;
    private StoreDto store;
    private List<StoreBlockDto> storeBlock;
    private List<StoreRelicDto> storeRelic;
}
