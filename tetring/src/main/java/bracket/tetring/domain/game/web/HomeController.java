package bracket.tetring.domain.game.web;

import bracket.tetring.domain.game.domain.Relic;
import bracket.tetring.domain.game.dto.RelicCheckDto;
import bracket.tetring.domain.game.service.BlockService;
import bracket.tetring.domain.game.service.RelicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {
//
//    private final BlockService blockService;
//    private final RelicService relicService;
//
//    @GetMapping("/relics")
//    public List<Relic> getRelics() {
//        return relicService.getAllRelics();
//    }
/////*
//    @GetMapping("/ranking")
//    public List<RankingDto> getRankings() {
//        return RankingService.getRanking();
//    }*/
}
