package bracket.tetring.global.util;

import java.util.*;

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
        List<Integer> getMoneyPrice = new ArrayList<>(Arrays.asList(1, 3, 5, 10, 20));
        if(haveInvestBlock) {
          money += money / 4;
        }
        return money + getMoneyPrice.get(moneySystemLevel - 1);
    }

    //리롤 가격 계산
    public static int getRerollPrice(Integer rerollTime) {
        return 2 + rerollTime;
    }

    //머니 시스템 레벨업 계산
    public static int getMoneyLevelUpPrice(int moneyLevel) {
        List<Integer> levelUpPrice = new ArrayList<>(Arrays.asList(3, 5, 10, 20));
        return levelUpPrice.get(moneyLevel - 1);
    }

    //블록 가격 반환
    public static int getBlockMoney() {
        return 5;
    }
}
