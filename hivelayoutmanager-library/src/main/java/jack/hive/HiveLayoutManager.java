package jack.hive;

import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjchai on 16/9/9.
 */
public class HiveLayoutManager extends RecyclerView.LayoutManager {


    private static final String TAG = HiveLayoutManager.class.getSimpleName();

    public static final int HORIZONTAL = HiveLayoutHelper.HORIZONTAL;

    public static final int VERTICAL = HiveLayoutHelper.VERTICAL;

    @IntDef({HORIZONTAL, VERTICAL})
    public @interface Orientation {
    }

    IHiveMathUtils hiveMathUtils;
    HiveLayoutHelper helper;
    AnchorInfo anchorInfo;
    LayoutState layoutState;
    final List<List<RectF>> floors = new ArrayList<>();

    int mOrientation;


    public HiveLayoutManager(int orientation) {
        mOrientation = orientation;
        init();
    }

    private void init() {
        helper = HiveLayoutHelper.getInstance(this);
        hiveMathUtils = HiveMathUtils.getInstance();
        layoutState = new LayoutState();
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {

        detachAndScrapAttachedViews(recycler);
        int itemCount = state.getItemCount();
        if (itemCount <= 0) {
            return;
        }
        initAnchorInfo(recycler);
        initFloors();
        updateLayoutState() ;

        fill(recycler, state);
    }

    private void updateLayoutState() {
        layoutState.containerRect.set(0,0,getWidth(),getHeight());
        layoutState.containerRect.offset(layoutState.offsetX,layoutState.offsetY);
    }

    private void fill(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int itemCount = state.getItemCount();
        if (itemCount <= 0) {
            return;
        }

        checkAllRect(itemCount);



        for (int i = 0; i < itemCount; i++) {
            RectF bounds = getBounds(i) ;

            if (RectF.intersects(bounds,layoutState.containerRect)) {
                View view = recycler.getViewForPosition(i);
                addView(view);
                measureChildWithMargins(view, 0, 0);

                bounds.offset(layoutState.offsetX, layoutState.offsetY);
                layoutDecoratedWithMargins(view, (int) bounds.left, (int) bounds.top, (int) bounds.right, (int) bounds.bottom);
            }
        }
    }

    public RectF getBounds(int index){
        HivePositionInfo positionInfo = hiveMathUtils.getFloorOfPosition(index);
        List<RectF> floor = floors.get(positionInfo.floor);
        return new RectF(floor.get(positionInfo.getPosition()));
    }

    private void initFloors() {
        if (floors.size() == 0) {
            List<RectF> list = new ArrayList<>();
            list.add(anchorInfo.anchorRect);
            floors.add(list);
        }
    }

    public void checkAllRect(int itemCount) {
        HivePositionInfo positionInfo = hiveMathUtils.getFloorOfPosition(itemCount - 1);
        checkFloor(positionInfo.floor);
    }

    private void checkFloor(int floor) {
        if (floor < 0) {
            return;
        }
        if (floors.size() > floor) {
            // this floor has init.
            return;
        } else {
            for (int i = floors.size(); i <= floor; i++) {
                int i1 = i - 1;
                Log.d(TAG, "checkFloor: i1 : " + i1 + " , i : " + i);
                List<RectF> temp = hiveMathUtils.getRectListOfFloor(floors.get(i1), anchorInfo.length, floor);
                floors.add(temp);
            }
        }
    }

    private void initAnchorInfo(RecyclerView.Recycler recycler) {
        if (anchorInfo == null) {
            anchorInfo = new AnchorInfo();
            anchorInfo.anchorPoint.set(getWidth() / 2, getHeight() / 2);

            View view = recycler.getViewForPosition(0);
            addView(view);
            measureChildWithMargins(view, 0, 0);

            int height = view.getMeasuredHeight();
            int width = view.getMeasuredWidth();

            float left = anchorInfo.anchorPoint.x - width / 2f;
            float right = anchorInfo.anchorPoint.x + width / 2f;
            float top = anchorInfo.anchorPoint.y - height / 2f;
            float bottom = anchorInfo.anchorPoint.y + height / 2f;

            anchorInfo.anchorRect.set(left, top, right, bottom);
            anchorInfo.length = hiveMathUtils.calculateLength(anchorInfo.anchorRect, mOrientation);
        }
    }


    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.d(TAG, String.format("scrollHorizontallyBy: dx : %d", dx));

        offsetChildrenHorizontal(-dx);
        layoutState.offsetX += -dx;

        return dx;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.d(TAG, String.format("scrollHorizontallyBy: dy : %d", dy));

        offsetChildrenVertical(-dy);
        layoutState.offsetY += -dy;

        return dy;
    }

    @Override
    public boolean canScrollHorizontally() {
        return true;
    }


    @Override
    public boolean canScrollVertically() {
        return true;
    }


    public int getmOrientation() {
        return mOrientation;
    }

    class AnchorInfo {

        final PointF anchorPoint = new PointF();
        final RectF anchorRect = new RectF();
        float length;

    }

    class LayoutState {

        int offsetX;
        int offsetY;

        RectF containerRect = new RectF();


    }


}
