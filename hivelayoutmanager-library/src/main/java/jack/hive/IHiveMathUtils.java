package jack.hive;

import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * A Math Util for HiveLayoutManager.
 * <p>
 * this interface include many method for calculate the position of view in RV.
 */
interface IHiveMathUtils {

    /**
     * calculate a item's bounds that beside of current item .
     *
     * @param current current item bounds
     * @param number  the public edge number
     * @param length  the length of regular hexagon
     * @return the item bounds
     */
    RectF calculateItemBounds(@NonNull RectF current, int number, float length);

    /**
     * calculate a item's center point that beside of current item .
     *
     * @param current current item center point
     * @param number  the public edge number
     * @param length  the length of regular hexagon
     * @return the center point
     */
    PointF calculateCenterPoint(@NonNull PointF current, int number, float length);

    /**
     * get the distance of neighbour item' center point.
     *
     * @param length the length of regular hexagon
     * @return the distance of neighbour item' center point
     */
    double getDistanceOfNeighbourCenter(float length);

    /**
     * clone a PointF object from current
     *
     * @param pointF resource
     * @return
     */
    PointF clone(@NonNull PointF pointF);

    /**
     * get {@link jack.hive.HiveConstants.VerticalNumber} from the edge number
     *
     * @param i edge number
     * @return the {@link jack.hive.HiveConstants.VerticalNumber}
     */
    @HiveConstants.VerticalNumber
    int getVerticalNumber(int i);

    /**
     * get {@link jack.hive.HiveConstants.HorizontalNumber} from the edge number
     *
     * @param i edge number
     * @return the {@link jack.hive.HiveConstants.HorizontalNumber}
     */
    @HiveConstants.HorizontalNumber
    int getHorizontalNumber(int i);

    /**
     * calculate the floor of the position
     *
     * @param position item position
     * @return the floor
     */
    HivePositionInfo getFloorOfPosition(int position);

    /**
     * calculate all item number int the floor
     *
     * @param floor floor
     * @return number of the floor
     */
    int getNumberOfFloor(int floor);

    /**
     * calculate the Length of regular hexagon
     *
     * @param rectF       the item view bounds
     * @param orientation the layout orientation
     * @return the length of regular hexagon
     */
    float calculateLength(@NonNull RectF rectF, @HiveLayoutManager.Orientation int orientation);

    /**
     * calculate the Length of regular hexagon
     *
     * @param rect        the item view bounds
     * @param orientation the layout orientation
     * @return the length of regular hexagon
     */
    float calculateLength(@NonNull Rect rect, @HiveLayoutManager.Orientation int orientation);

    /**
     * calculate current floor Rects by last floor
     *
     * @param lastFloorRects Rects at last floor
     * @param length         the length of regular hexagon
     * @param floor          last floor
     * @param orientation    orientation {@link jack.hive.HiveLayoutManager.Orientation}
     * @return RectFs at current floor
     */
    List<RectF> getRectListOfFloor(@NonNull List<RectF> lastFloorRects, float length, int floor
            , @HiveLayoutManager.Orientation int orientation);
}
