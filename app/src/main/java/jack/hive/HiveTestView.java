package jack.hive;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by zjchai on 16/9/11.
 */
public class HiveTestView extends View {

    private static final String TAG = HiveTestView.class.getSimpleName();
    IHiveMathUtils hiveMathUtils ;


    PointF current = new PointF() ;
    Paint paint ;

    public HiveTestView(Context context) {
        super(context);
        init() ;
    }

    private void init() {
        hiveMathUtils = HiveMathUtils.getInstance() ;
        paint = new Paint() ;
        paint.setStrokeWidth(10);
        paint.setColor(Color.RED);
    }

    public HiveTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init() ;
    }

    public HiveTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init() ;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int height = getHeight();
        int width = getWidth();
        current.set(width / 2f,height / 2f );

        for (int i = 0; i < 6; i++) {
            PointF pointF = hiveMathUtils.calculateVerticalCenterPoint(current, hiveMathUtils.getVerticalNumber(i), 100);
            Log.d(TAG, "onDraw: "+pointF);
            canvas.drawPoint(pointF.x,pointF.y,paint);
        }
        canvas.drawPoint(current.x,current.y,paint);
    }
}
