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
    final HiveBucket booleanMap = new HiveBucket();
    int mOrientation;


    public HiveLayoutManager(int orientation) {
        mOrientation = orientation;
        init();
    }

    private void init() {
        helper = HiveLayoutHelper.getInstance(this);
        hiveMathUtils = HiveMathUtils.getInstance();
        layoutState = new LayoutState();

        booleanMap.reset();
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
        initEdgeDistance();
        detachAndScrapAttachedViews(recycler);

        booleanMap.reset();

        fill(recycler, state);
    }

    private void initEdgeDistance() {
        layoutState.edgeDistance.set(getWidth() / 2, getHeight() / 2, getWidth() / 2, getHeight() / 2);
    }

    private void updateLayoutState() {
        layoutState.containerRect.set(0, 0, getWidth(), getHeight());
        layoutState.containerRect.offset(-layoutState.offsetX, -layoutState.offsetY);
    }

    private void fill(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int itemCount = state.getItemCount();
        if (itemCount <= 0) {
            return;
        }

        checkAllRect(itemCount);
        updateLayoutState();

        for (int i = 0; i < itemCount; i++) {
            RectF bounds = getBounds(i);

            if (!booleanMap.get(i) && RectF.intersects(bounds, layoutState.containerRect)) {
                View view = recycler.getViewForPosition(i);
                addView(view);
                booleanMap.set(i);
                measureChildWithMargins(view, 0, 0);

                bounds.offset(layoutState.offsetX, layoutState.offsetY);

                calculateEdgeDistance(bounds.left, bounds.top, bounds.right, bounds.bottom);

                layoutDecoratedWithMargins(view, (int) bounds.left, (int) bounds.top, (int) bounds.right, (int) bounds.bottom);
            }
        }
    }

    private void calculateEdgeDistance(float left, float top, float right, float bottom) {
        RectF temp = layoutState.edgeDistance;
        float eLeft = Math.min(temp.left, left);
        float eTop = Math.min(temp.top, top);
        float eRight = Math.min(temp.right, getWidth() - right);
        float eBottom = Math.min(temp.bottom, getHeight() - bottom);
        layoutState.edgeDistance.set(eLeft, eTop, eRight, eBottom);
    }

    public RectF getBounds(int index) {
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
                List<RectF> temp = hiveMathUtils.getRectListOfFloor(floors.get(i1), anchorInfo.length, floor, mOrientation);
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
        if ((layoutState.edgeDistance.left < 0 && dx < 0) || (layoutState.edgeDistance.right < 0 && dx > 0)) {
            float distance = dx < 0 ? Math.max(layoutState.edgeDistance.left ,dx) : Math.min(-layoutState.edgeDistance.right,dx) ;
            doScrollHorizontalBx(recycler, state, distance);
            return (int) distance;
        } else if( layoutState.offsetX != 0){
            if (layoutState.offsetX*dx > 0) {
                float distance = (Math.abs(dx) / dx ) * Math.min(Math.abs(layoutState.offsetX),Math.abs(dx)) ;
                doScrollHorizontalBx(recycler,state,distance);
                return (int) distance;
            } else {
                return 0;
            }
        } else {
            return 0 ;
        }
    }

    private void doScrollHorizontalBx(RecyclerView.Recycler recycler, RecyclerView.State state, float distance) {
        layoutState.edgeDistance.left -= distance ;
        layoutState.edgeDistance.right += distance ;
        offsetChildrenHorizontal((int) -distance);
        layoutState.offsetX -= distance;
        layoutState.lastScrollDeltaX = (int) distance;

        scrapOutSetViews(recycler);
        scrollBy((int) distance, recycler, state);
    }

    private void scrapOutSetViews(RecyclerView.Recycler recycler) {
        int count = getChildCount();
        for (int i = count - 1; i >= 0; i--) {
            View view = getChildAt(i);
            if (!RectF.intersects(new RectF(0, 0, getWidth(), getHeight()), new RectF(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()))) {
                int position = getPosition(view);
                booleanMap.clear(position);
                detachAndScrapView(view, recycler);
            }
        }
    }

    private int scrollBy(int distance, RecyclerView.Recycler recycler, RecyclerView.State state) {
        fill(recycler, state);
        return distance;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {

        if ((layoutState.edgeDistance.top < 0 && dy < 0) || (layoutState.edgeDistance.bottom < 0 && dy > 0)) {
            float distance = dy < 0 ? Math.max(layoutState.edgeDistance.top ,dy) : Math.min(-layoutState.edgeDistance.bottom,dy) ;
            doScrollVerticalBy(recycler, state, distance);
            return (int) distance;
        } else if( layoutState.offsetY != 0){
            if (layoutState.offsetY*dy > 0) {
                float distance = (Math.abs(dy) / dy ) * Math.min(Math.abs(layoutState.offsetY),Math.abs(dy)) ;
                doScrollVerticalBy(recycler,state,distance);
                return (int) distance;
            } else {
                return 0;
            }
        } else {
            return 0 ;
        }

//        offsetChildrenVertical(-dy);
//        layoutState.offsetY += -dy;
//        layoutState.lastScrollDeltaY = dy;
//
//        scrapOutSetViews(recycler);
//
//        return scrollBy(dy, recycler, state);
    }

    private void doScrollVerticalBy(RecyclerView.Recycler recycler, RecyclerView.State state, float distance){
        layoutState.edgeDistance.top -= distance ;
        layoutState.edgeDistance.bottom += distance ;
        offsetChildrenVertical((int) -distance);
        layoutState.offsetY -= distance;
        layoutState.lastScrollDeltaY = (int) distance;

        scrapOutSetViews(recycler);
        scrollBy((int) distance, recycler, state);
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
        float extraX;
        float extraY;
        int lastScrollDeltaX;
        int lastScrollDeltaY;

        final RectF edgeDistance = new RectF();


        final RectF containerRect = new RectF();


    }


}
