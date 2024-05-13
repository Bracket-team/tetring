package bracket.tetring.global.util;

import java.util.HashMap;
import java.util.Map;

public final class CalculateSystem {

    private static final Map<Integer, Long> stageGoals = new HashMap<>();

    static {
        for(int i = 1; i <= 12; i++) {
            stageGoals.put(i, i * 100L);
        }
    }

    private CalculateSystem() {
    }

    //스테이지 목표 점수 계산
    public static Long getStageGoal(int stageNumber) {
        return stageGoals.getOrDefault(stageNumber, 0L);
    }

    //스테이지 종료 후 돈 계산
    public static int getTotalMoney(int moneySystemLevel, int money, boolean haveInvestBlock) {
        if(haveInvestBlock) {
          money += money / 4;
        }
        return money + moneySystemLevel;
    }

    //리롤 가격 계산
    public static int getRerollPrice(Integer rerollTime) {
        return 5 + rerollTime * 5;
    }

    //머니 시스템 레벨업 계산
    public static int getMoneyLevelUpPrice(int moneyLevel) {
        return moneyLevel * 5;
    }

    //블록 가격 반환
    public static int getBlockMoney() {
        return 5;
    }
}
