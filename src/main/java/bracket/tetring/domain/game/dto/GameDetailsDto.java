package bracket.tetring.domain.game.dto;

import bracket.tetring.domain.player.dto.PlayerBlockDto;
import bracket.tetring.domain.player.dto.PlayerRelicDto;
import bracket.tetring.domain.store.dto.StoreBlockDto;
import bracket.tetring.domain.store.dto.StoreDto;
import bracket.tetring.domain.store.dto.StoreRelicDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GameDetailsDto {
    private GameDto game;

    @JsonProperty("player_block")
    private List<PlayerBlockDto> playerBlock;

    @JsonProperty("player_relic")
    private List<PlayerRelicDto> playerRelic;

    private StoreDto store;

    @JsonProperty("store_block")
    private List<StoreBlockDto> storeBlock;

    @JsonProperty("store_relic")
    private List<StoreRelicDto> storeRelic;
}
