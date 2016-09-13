package jack.hive;

import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjchai on 16/9/11.
 */
public class HiveMathUtils implements IHiveMathUtils {

    private static final String TAG = HiveMathUtils.class.getSimpleName();

    public static IHiveMathUtils getInstance() {
        return new HiveMathUtils();
    }

    @Override
    public RectF calculateItemBounds(@NonNull RectF current, int number, float length) {
        PointF pointF = calculateCenterPoint(new PointF(current.centerX(), current.centerY()), number, length);
        float width = current.width();
        float height = current.height();
        float left = pointF.x - width / 2;
        float right = pointF.x + width / 2;
        float top = pointF.y - height / 2;
        float bottom = pointF.y + height / 2;
        return new RectF(left, top, right, bottom);
    }

    @Override
    public PointF calculateCenterPoint(@NonNull PointF current, int number, float length) {
        double distance = getDistanceOfNeighbourCenter(length);
        double x = distance * Math.cos(number * Math.PI / 6);
        double y = distance * Math.sin(number * Math.PI / 6);
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
                throw new IllegalArgumentException("i must >=0 and <6.");
        }
    }

    @Override
    public int getHorizontalNumber(int i) {
        switch (i) {
            case 0:
                return HiveConstants.HORIZONTAL_ONE;
            case 1:
                return HiveConstants.HORIZONTAL_TWO;
            case 2:
                return HiveConstants.HORIZONTAL_THREE;
            case 3:
                return HiveConstants.HORIZONTAL_FOUR;
            case 4:
                return HiveConstants.HORIZONTAL_FIVE;
            case 5:
                return HiveConstants.HORIZONTAL_SIX;
            default:
                throw new IllegalArgumentException("i must >=0 and <6.");
        }
    }

    @Override
    public HivePositionInfo getFloorOfPosition(int position) {
        if (position < 0) {
            throw new IllegalArgumentException("position must be >= 0");
        } else if (position == 0) {
            return new HivePositionInfo(0, 0);
        } else {
            int i = 0;
            position -= 1; //减去第0层的一个
            int number;
            do {
                i++;
                number = getNumberOfFloor(i);
                position -= number;
            } while (position >= 0);
            return new HivePositionInfo(i, position + number);
        }
    }

    @Override
    public int getNumberOfFloor(int floor) {
        if (floor < 0) {
            throw new IllegalArgumentException("floor must be >= 0");
        } else if (floor == 0) {
            return 1;
        } else {
            return floor * 6;
        }
    }

    @Override
    public float calculateLength(@NonNull RectF rectF, @HiveLayoutManager.Orientation int orientation) {
        if (orientation == HiveLayoutManager.HORIZONTAL) {
            return rectF.width() / 2;
        } else {
            return rectF.height() / 2;
        }
    }

    @Override
    public float calculateLength(@NonNull Rect rect, @HiveLayoutManager.Orientation int orientation) {
        return calculateLength(new RectF(rect.left, rect.top, rect.right, rect.bottom), orientation);
    }


    @Override
    public List<RectF> getRectListOfFloor(@NonNull List<RectF> lastFloorRects, float length, int floor, @HiveLayoutManager.Orientation int orientation) {
        Log.d(TAG, String.format("getRectListOfFloor: length : %f, floor : %d", length, floor));
        if (floor <= 0) {
            throw new IllegalArgumentException("floor must > 0 .");
        } else if (floor == 1) { //第一层特殊处理
            List<RectF> result = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                result.add(calculateItemBounds(lastFloorRects.get(0), getNumber(i,orientation), length));
            }
            return result;
        } else { // 2~N层采用下面的方法
            int lastFloor = floor - 1;
            List<RectF> result = new ArrayList<>();
            int number = getNumberOfFloor(lastFloor);

            for (int i = 0; i < number; i++) {
                if (isCorner(lastFloor, i)) {
                    result.addAll(getNextRectListOfCorner(lastFloorRects.get(i), lastFloor, length, i,orientation));
                } else {
                    result.add(getNextRectOfMiddle(lastFloorRects.get(i), lastFloor, length, i,orientation));
                }
            }

            return result;
        }
    }

    private List<RectF> getNextRectListOfCorner(RectF cornerRectF, int cornerFloor, float length, int index, @HiveLayoutManager.Orientation int orientation) {
        List<RectF> result = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            int number = getNumber((i + index / cornerFloor) % 6, orientation);
            result.add(calculateItemBounds(cornerRectF, number, length));
        }
        return result;
    }

    private RectF getNextRectOfMiddle(RectF middleRectF, int middleFloor, float length, int index, @HiveLayoutManager.Orientation int orientation) {
        int number = getNumber((index / middleFloor + 1) % 6, orientation);
        RectF result = calculateItemBounds(middleRectF, number, length);
        return result;
    }

    private boolean isCorner(int floor, int index) {
        if (floor < 0 || index < 0) {
            throw new IllegalArgumentException("floor and index must >= 0");
        }
        return index % floor == 0;
    }

    public int getNumber(int index, @HiveLayoutManager.Orientation int orientation) {
        if (orientation == HiveLayoutManager.HORIZONTAL) {
            return getHorizontalNumber(index);
        } else if (orientation == HiveLayoutManager.VERTICAL) {
            return getVerticalNumber(index);
        } else {
            throw new IllegalArgumentException("orientation must be HiveLayoutManager.VERTICAL or HiveLayoutManager.HORIZONTAL");
        }
    }


}
