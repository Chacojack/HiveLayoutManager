package jack.hive;

import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by zjchai on 16/9/11.
 */
public class HiveMathUtils implements IHiveMathUtils {

    private static final String TAG = HiveMathUtils.class.getSimpleName();

    public static IHiveMathUtils getInstance() {
        return new HiveMathUtils();
    }

    @Override
    public RectF calculateVerticalItemBounds(@NonNull RectF current, @HiveConstants.VerticalNumber int number, float length) {
        PointF pointF = calculateVerticalCenterPoint(new PointF(current.centerX(), current.centerY()), number, length);
        float width = current.width();
        float height = current.height();
        float left = pointF.x - width / 2;
        float right = pointF.x + width / 2;
        float top = pointF.x - height / 2;
        float bottom = pointF.x + height / 2;
        return new RectF(left, top, right, bottom);
    }

    @Override
    public PointF calculateVerticalCenterPoint(@NonNull PointF current, @HiveConstants.VerticalNumber int number, float length) {
        double distance = getDistanceOfNeighbourCenter(length);
        double x = distance * Math.cos(number * Math.PI / 6);
        double y = distance * Math.sin(number * Math.PI / 6);
        Log.d(TAG, String.format("calculateVerticalCenterPoint: x : %f, y : %f.",x,y));
        PointF result = clone(current);
        result.offset((float) x, (float) y);
        return result;
    }

    @Override
    public double getDistanceOfNeighbourCenter(float length) {
        return length * Math.sqrt(3);
    }

    @Override
    public PointF clone(@NonNull PointF pointF) {
        PointF result = new PointF();
        result.set(pointF.x, pointF.y);
        return result;
    }

    @Override
    public
    @HiveConstants.VerticalNumber
    int getVerticalNumber(int i) {
        switch (i) {
            case 0:
                return HiveConstants.VERTICAL_ONE;
            case 1:
                return HiveConstants.VERTICAL_TWO;
            case 2:
                return HiveConstants.VERTICAL_THREE;
            case 3:
                return HiveConstants.VERTICAL_FOUR;
            case 4:
                return HiveConstants.VERTICAL_FIVE;
            case 5:
                return HiveConstants.VERTICAL_SIX;
            default:
                Log.e(TAG, "i must < 6 and > 0.");
                return HiveConstants.VERTICAL_ONE;
        }
    }

}
