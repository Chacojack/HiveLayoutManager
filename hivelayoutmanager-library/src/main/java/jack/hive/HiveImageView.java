package jack.hive;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by zjchai on 16/9/9.
 */
public class HiveImageView extends ImageView {

    public HiveImageView(Context context) {
        super(context);
    }

    public HiveImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HiveImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
