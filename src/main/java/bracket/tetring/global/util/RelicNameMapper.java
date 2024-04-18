package bracket.tetring.global.util;

import java.util.HashMap;
import java.util.Map;

public final class RelicNameMapper {
    private static final Map<String, Integer> RelicNameToNumber = new HashMap<>();

    private RelicNameMapper() {
    }

    static  {
        RelicNameToNumber.put("노을 사진", 1);
        RelicNameToNumber.put("T블록", 2);
        RelicNameToNumber.put("보물 상자", 3);
        RelicNameToNumber.put("리롤 블록", 4);
        RelicNameToNumber.put("재활용 블록", 5);
        RelicNameToNumber.put("돈 주머니", 6);
        RelicNameToNumber.put("초과 업무", 7);
        RelicNameToNumber.put("금 블록", 8);
        RelicNameToNumber.put("반지 블록", 9);
        RelicNameToNumber.put("쿠폰 블록", 10);
        RelicNameToNumber.put("망치 블록", 11);
        RelicNameToNumber.put("딸기", 12);
        RelicNameToNumber.put("썩은 귤", 13);
        RelicNameToNumber.put("트리플 블록", 14);
        RelicNameToNumber.put("테트라 블록", 15);
        RelicNameToNumber.put("화이트 블록", 16);
        RelicNameToNumber.put("다이아 블록", 17);
        RelicNameToNumber.put("수표 블록", 18);
        RelicNameToNumber.put("해골 블록", 19);
        RelicNameToNumber.put("투자 블록", 20);
        RelicNameToNumber.put("클론 블록", 21);
        RelicNameToNumber.put("원 볼록", 22);
        RelicNameToNumber.put("콤보 블록", 23);
        RelicNameToNumber.put("먹보 블록", 24);
        RelicNameToNumber.put("파이브 블록", 25);
        RelicNameToNumber.put("블랭크 블록", 26);
        RelicNameToNumber.put("별 블록", 27);
        RelicNameToNumber.put("파괴 블록", 28);
        RelicNameToNumber.put("우산 블록", 29);
        RelicNameToNumber.put("세 배로 블록", 30);
    }

    public static int getRelicNumber(String key) {
        return RelicNameToNumber.getOrDefault(key, 1);
    }
}
