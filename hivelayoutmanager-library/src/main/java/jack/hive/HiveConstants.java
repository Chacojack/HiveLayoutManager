package jack.hive;

import android.support.annotation.IntDef;

/**
 * Created by zjchai on 16/9/11.
 */
public class HiveConstants {

    public static final int VERTICAL_ONE = 0 ;
    public static final int VERTICAL_TWO = 2 ;
    public static final int VERTICAL_THREE = 4 ;
    public static final int VERTICAL_FOUR = 6 ;
    public static final int VERTICAL_FIVE = 8 ;
    public static final int VERTICAL_SIX = 10 ;

    @IntDef({VERTICAL_ONE,VERTICAL_TWO,VERTICAL_THREE,VERTICAL_FOUR,VERTICAL_FIVE,VERTICAL_SIX})
    public @interface VerticalNumber {}

}
