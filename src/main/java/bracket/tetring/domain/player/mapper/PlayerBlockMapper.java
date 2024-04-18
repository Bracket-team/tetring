package bracket.tetring.domain.player.mapper;

import bracket.tetring.domain.player.domain.PlayerBlock;
import bracket.tetring.domain.player.dto.PlayerBlockDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlayerBlockMapper {
    public PlayerBlockDto playerBlockToPlayerBlockDto (PlayerBlock playerBlock) {
        PlayerBlockDto playerBlockDto = new PlayerBlockDto();

        playerBlockDto.setBlockId(playerBlock.getBlockId());
        playerBlockDto.setBlockShape(playerBlock.getBlockShape());
        playerBlockDto.setBlockColor(playerBlock.getBlockColor());

        return playerBlockDto;
    }

    public List<PlayerBlockDto> playerBlocksToPlayerBlockDtos(List<PlayerBlock> playerBlocks) {
        if (playerBlocks == null) {
            return null;
        }

        return playerBlocks.stream()
                .map(this::playerBlockToPlayerBlockDto)
                .collect(Collectors.toList());
    }
}
