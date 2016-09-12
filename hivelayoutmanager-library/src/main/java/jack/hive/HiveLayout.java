package jack.hive;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

/**
 * Created by zjchai on 16/9/12.
 */
public class HiveLayout extends RelativeLayout {


    private static final String TAG = HiveLayout.class.getSimpleName();

    Xfermode xfermode = new Xfermode() ;

    public HiveLayout(Context context) {
        super(context);
        init() ;
    }

    private void init() {

    }

    public HiveLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HiveLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void dispatchDraw(Canvas canvas) {
//        Log.d(TAG, String.format("onDraw: %d,%d", canvas.getWidth(),canvas.getHeight()));
        Bitmap.Config config = Bitmap.Config.ARGB_8888 ;
        Bitmap bitmap = Bitmap.createBitmap(getWidth(),getHeight(),config) ;
        Canvas temp = new Canvas(bitmap) ;
        super.dispatchDraw(temp);
//        super.dispatchDraw(canvas);

        Drawable drawable = new HiveDrawable(HiveLayoutManager.VERTICAL,bitmap);
        drawable.setBounds(getPaddingLeft(),getPaddingTop(),getWidth()-getPaddingRight(),getHeight()-getPaddingBottom());
//        setBackground(drawable);
        drawable.draw(canvas);
//        canvas.drawBitmap(bitmap,0,0,null);

    }
}
