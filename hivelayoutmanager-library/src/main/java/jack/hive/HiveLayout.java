package jack.hive;

import android.content.Context;
import android.content.res.TypedArray;
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

import jack.hivelayoutmanager_library.R;

/**
 * Created by zjchai on 16/9/12.
 */
public class HiveLayout extends RelativeLayout {


    private static final String TAG = HiveLayout.class.getSimpleName();

    private int orientation ;

    public HiveLayout(Context context) {
        this(context,null);
    }

    public HiveLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HiveLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.HiveLayout, defStyleAttr,0) ;
        orientation = array.getInt(R.styleable.HiveLayout_orientation,HiveLayoutManager.VERTICAL) ;

        array.recycle();
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        Bitmap.Config config = Bitmap.Config.ARGB_8888 ;
        Bitmap bitmap = Bitmap.createBitmap(getWidth(),getHeight(),config) ;
        Canvas temp = new Canvas(bitmap) ;
        super.dispatchDraw(temp);
        Drawable drawable = new HiveDrawable(orientation,bitmap);
        drawable.setBounds(getPaddingLeft(),getPaddingTop(),getWidth()-getPaddingRight(),getHeight()-getPaddingBottom());
        drawable.draw(canvas);
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(@HiveLayoutManager.Orientation int orientation) {
        this.orientation = orientation;
    }
}
