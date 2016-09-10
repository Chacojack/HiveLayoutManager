package jack.hive;

import android.support.annotation.IntDef;

/**
 * Created by zjchai on 16/9/10.
 */
public class HiveLayoutHelper {

    public static final int HORIZONTAL = 0 ;

    public static final int VERTICAL = 1;

    @IntDef({HORIZONTAL,VERTICAL})
    public @interface Orientation{}

    public static HiveLayoutHelper getInstance(){
        return new HiveLayoutHelper() ;
    }


}
