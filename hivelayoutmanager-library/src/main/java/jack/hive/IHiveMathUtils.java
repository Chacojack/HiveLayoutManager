package jack.hive;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by zjchai on 16/9/11.
 */
public interface IHiveMathUtils {

    RectF calculateItemBounds(@NonNull RectF current, int number, float length);

    /**
     * calculate center point of the number hexagon beside of the current point
     *
     * @param current
     * @param number
     * @param length
     * @return
     */
    PointF calculateCenterPoint(@NonNull PointF current, int number, float length);

    double getDistanceOfNeighbourCenter(float length);

    PointF clone(@NonNull PointF pointF);

    @HiveConstants.VerticalNumber
    int getVerticalNumber(int i);

    @HiveConstants.HorizontalNumber
    int getHorizontalNumber(int i);

    HivePositionInfo getFloorOfPosition(int position);

    int getNumberOfFloor(int floor);

    float calculateLength(@NonNull RectF rectF, @HiveLayoutManager.Orientation int orientation);

    float calculateLength(@NonNull Rect rect, @HiveLayoutManager.Orientation int orientation);

    List<RectF> getRectListOfFloor(@NonNull List<RectF> lastFloorRects, float length, int floor, @HiveLayoutManager.Orientation int orientation);
}
