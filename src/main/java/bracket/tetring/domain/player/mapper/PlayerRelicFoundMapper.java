package bracket.tetring.domain.player.mapper;

import bracket.tetring.domain.player.domain.PlayerRelicFound;
import bracket.tetring.domain.player.dto.PlayerRelicFoundDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlayerRelicFoundMapper {

    public PlayerRelicFoundDto playerRelicFoundToPlayerRelicFoundDto(PlayerRelicFound playerRelicFound) {
        PlayerRelicFoundDto playerRelicFoundDto = new PlayerRelicFoundDto();

        playerRelicFoundDto.setRelicNumber(playerRelicFound.getRelic().getRelicNumber());
        playerRelicFoundDto.setName(playerRelicFound.getRelic().getRelicName());
        playerRelicFoundDto.setRarity(playerRelicFound.getRelic().getRarity().toString());
        playerRelicFoundDto.setEffect(playerRelicFound.getRelic().getEffect());
        playerRelicFoundDto.setPrice(playerRelicFound.getRelic().getPrice());

        return playerRelicFoundDto;
    }

    public List<PlayerRelicFoundDto> playerRelicFoundsToPlayerRelicFoundDtos(List<PlayerRelicFound> playerRelicFounds) {
        if (playerRelicFounds == null) {
            return null;
        }

        return playerRelicFounds.stream()
                .map(this::playerRelicFoundToPlayerRelicFoundDto)
                .collect(Collectors.toList());
    }
}
