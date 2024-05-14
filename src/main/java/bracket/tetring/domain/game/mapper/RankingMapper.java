package bracket.tetring.domain.game.mapper;

import bracket.tetring.domain.game.domain.Score;
import bracket.tetring.domain.game.dto.RankingDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RankingMapper {
    public RankingDto scoreToRankingDto(Score score) {
        RankingDto rankingDto = new RankingDto();

        rankingDto.setPlayerId(score.getPlayer().getPlayerId());
        rankingDto.setName(score.getPlayer().getName());
        rankingDto.setScore(score.getScore());
        rankingDto.setStage(score.getStage());

        return rankingDto;
    }

    public List<RankingDto> scoresToRankingDtos(List<Score> scores) {
        if (scores == null) {
            return null;
        }

        return scores.stream()
                .map(this::scoreToRankingDto)
                .collect(Collectors.toList());
    }
}
