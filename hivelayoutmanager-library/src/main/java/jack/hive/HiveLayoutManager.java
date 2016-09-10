package jack.hive;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zjchai on 16/9/9.
 */
public class HiveLayoutManager extends RecyclerView.LayoutManager{



    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {

        detachAndScrapAttachedViews(recycler);

        int itemCount = state.getItemCount();

        int left , top ;
        left = top = 0 ;

        for (int i = 0; i < itemCount; i++) {
            View view = recycler.getViewForPosition(i);
            addView(view);
            measureChildWithMargins(view,0,0);

            int width = getDecoratedMeasuredWidth(view);
            int height = getDecoratedMeasuredHeight(view);

            layoutDecoratedWithMargins(view,left,top,left+width,top+height);

            left += width ;
            top += height ;

        }

    }
}
