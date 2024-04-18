package bracket.tetring.domain.store.service;

import bracket.tetring.domain.game.domain.BlockColor;
import bracket.tetring.domain.game.domain.Game;
import bracket.tetring.domain.game.domain.Relic;
import bracket.tetring.domain.game.repository.RelicRepository;
import bracket.tetring.domain.player.domain.PlayerRelic;
import bracket.tetring.domain.player.repository.PlayerRelicRepository;
import bracket.tetring.domain.store.domain.Store;
import bracket.tetring.domain.store.domain.StoreBlock;
import bracket.tetring.domain.store.domain.StoreRelic;
import bracket.tetring.domain.store.repository.StoreBlockRepository;
import bracket.tetring.domain.store.repository.StoreRelicRepository;
import bracket.tetring.global.error.ErrorCode;
import bracket.tetring.global.exception.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static bracket.tetring.global.util.BlockShapeMapper.getBlockShape;

@Component
@RequiredArgsConstructor
public class RandomStoreHelper {

    private final StoreBlockRepository storeBlockRepository;
    private final StoreRelicRepository storeRelicRepository;
    private final PlayerRelicRepository playerRelicRepository;
    private final RelicRepository relicRepository;

    @Transactional
    public void saveRandomBlock(Store store) {
        List<Integer> randomNumbers = getRandomNumbers(7);
        for(int i = 0; i < 3; i++) {
            Integer slotNumber = i + 1;
            Integer blockNumber = randomNumbers.get(i);
            StoreBlock storeBlock = new StoreBlock(slotNumber, store, getBlockShape(blockNumber), BlockColor.values()[blockNumber - 1]);
            storeBlockRepository.save(storeBlock);
        }
    }

    @Transactional
    public void saveRandomRelic(Game game, Store store) {
        List<PlayerRelic> playerRelics = playerRelicRepository.findAllByGameOrderBySlotNumberAsc(game);
        Set<Integer> playerRelicNumbers = playerRelics.stream()
                .map(playerRelic -> playerRelic.getRelic().getRelicNumber())
                .collect(Collectors.toSet());

        //기존에 플레이어가 가지고 있던 relic과 겹치지 않게 추가
        int addedCount = 0;
        while (addedCount < 2) {
            int randomNumber = generateRandomNumberWithProbability();
            if (!playerRelicNumbers.contains(randomNumber)) {
                Integer slotNumber = addedCount + 1;
                Relic relic = relicRepository.findById(randomNumber).orElseThrow(
                        () -> new CustomException(ErrorCode.RELIC_NOT_FOUND)
                );
                StoreRelic storeRelic = new StoreRelic(store, relic, slotNumber);
                storeRelicRepository.save(storeRelic);
                playerRelicNumbers.add(randomNumber); // 추가한 유물 번호를 집합에 추가
                addedCount++;
            }
        }
    }

    private int generateRandomNumberWithProbability() {
        double rand = Math.random();
        if (rand < 0.5) { // 1~15번까지 50% 확률
            return 1 + (int)(Math.random() * 15);
        } else if (rand < 0.9) { // 16~25번까지 40% 확률
            return 16 + (int)(Math.random() * 10);
        } else { // 26~30번까지 10% 확률
            return 26 + (int)(Math.random() * 5);
        }
    }

    private List<Integer> getRandomNumbers(int maxNumber) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= maxNumber; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers;
    }
}
