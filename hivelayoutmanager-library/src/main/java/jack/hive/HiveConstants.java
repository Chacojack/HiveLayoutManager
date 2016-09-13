package jack.hive;

import android.support.annotation.IntDef;

/**
 * Created by zjchai on 16/9/11.
 */
public class HiveConstants {

    public static final int VERTICAL_ONE = 0;
    public static final int VERTICAL_TWO = 2;
    public static final int VERTICAL_THREE = 4;
    public static final int VERTICAL_FOUR = 6;
    public static final int VERTICAL_FIVE = 8;
    public static final int VERTICAL_SIX = 10;

    public static final int HORIZONTAL_ONE = 3;
    public static final int HORIZONTAL_TWO = 5;
    public static final int HORIZONTAL_THREE = 7;
    public static final int HORIZONTAL_FOUR = 9;
    public static final int HORIZONTAL_FIVE = 11;
    public static final int HORIZONTAL_SIX = 1;

    @IntDef({VERTICAL_ONE, VERTICAL_TWO, VERTICAL_THREE, VERTICAL_FOUR, VERTICAL_FIVE, VERTICAL_SIX})
    public @interface VerticalNumber {
    }


    @IntDef({HORIZONTAL_ONE, HORIZONTAL_TWO, HORIZONTAL_THREE, HORIZONTAL_FOUR, HORIZONTAL_SIX, HORIZONTAL_FIVE})
    public @interface HorizontalNumber {
    }

}
