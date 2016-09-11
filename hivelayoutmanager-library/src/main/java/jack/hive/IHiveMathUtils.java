package jack.hive;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.NonNull;

/**
 * Created by zjchai on 16/9/11.
 */
public interface IHiveMathUtils {

    RectF calculateVerticalItemBounds(@NonNull RectF current, @HiveConstants.VerticalNumber int number, float length);

    /**
     * calculate center point of the number hexagon beside of the current point
     *
     * @param current
     * @param number
     * @param length
     * @return
     */
    PointF calculateVerticalCenterPoint(@NonNull PointF current, @HiveConstants.VerticalNumber int number, float length);

    double getDistanceOfNeighbourCenter(float length);

    PointF clone(@NonNull PointF pointF);

    @HiveConstants.VerticalNumber
    int getVerticalNumber(int i);
}
