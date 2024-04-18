package bracket.tetring.global.util;

import java.util.HashMap;
import java.util.Map;

public class BlockShapeMapper {
    private static final Map<Integer, String> NumberToBlockShape = new HashMap<>();

    private BlockShapeMapper() {
    }

    static  {
        NumberToBlockShape.put(1, "0000111100000000");
        NumberToBlockShape.put(2, "00000010000111100");
        NumberToBlockShape.put(3, "0000001011100000");
        NumberToBlockShape.put(4, "0000011001100000");
        NumberToBlockShape.put(5, "0000001101100000");
        NumberToBlockShape.put(6, "0000001001110000");
        NumberToBlockShape.put(7, "0000110001100000");
    }

    public static String getBlockShape(Integer key) {
        return NumberToBlockShape.getOrDefault(key, "0000111100000000");
    }

}
