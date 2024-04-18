package bracket.tetring.domain.player.mapper;

import bracket.tetring.domain.player.domain.PlayerRelic;
import bracket.tetring.domain.player.dto.PlayerRelicDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlayerRelicMapper {
    public PlayerRelicDto playerRelicToPlayerRelicDto (PlayerRelic playerRelic) {
        PlayerRelicDto playerRelicDto = new PlayerRelicDto();

        playerRelicDto.setRelicName(playerRelic.getRelic().getRelicName());
        playerRelicDto.setRelicNumber(playerRelic.getRelic().getRelicNumber());
        playerRelicDto.setPrice(playerRelic.getRelic().getPrice());
        playerRelicDto.setCoef(playerRelic.getRelic().getCoef());
        playerRelicDto.setRarity(playerRelic.getRelic().getRarity().toString());
        playerRelicDto.setEffect(playerRelic.getRelic().getEffect());
        playerRelicDto.setSlotNumber(playerRelic.getSlotNumber());

        return playerRelicDto;
    }

    public List<PlayerRelicDto> playerRelicsToPlayerRelicDtos(List<PlayerRelic> playerRelics) {
        if (playerRelics == null) {
            return null;
        }

        return playerRelics.stream()
                .map(this::playerRelicToPlayerRelicDto)
                .collect(Collectors.toList());
    }
}
