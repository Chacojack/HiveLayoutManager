package jack.hive;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * Created by zjchai on 16/9/9.
 */
public class HiveDrawable extends Drawable {

    Rect mRect = new Rect();
    Paint mPaint;
    Path mPath ;
    BitmapShader shader ;
    Bitmap mBitmap ;

    public HiveDrawable() {
        this(null) ;
    }

    public HiveDrawable(Bitmap bitmap) {
        init();
        setBitmap(bitmap);
    }

    private void init() {
        initPaint() ;
        initPath() ;
    }

    private void ensurePaint(){
        if (mPaint == null) {
            mPaint = new Paint() ;
        }
    }

    private void ensurePath(){
        if (mPath == null) {
            mPath = new Path() ;
        }
    }

    private void initPaint() {
        ensurePaint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(3f);
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
        if (bitmap == null) {
            shader =null ;
        } else {
            shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP) ;
            mPaint.setShader(shader) ;
        }
    }

    private void initPath() {
        ensurePath();
        float mLength = (float) (mRect.width() / 2);
        float height = (float) (Math.sqrt(3)*mLength);
        float top = mLength - height / 2  ;
        mPath.reset();
        mPath.moveTo(mLength/2,top);
        mPath.lineTo(0,height/2+top);
        mPath.lineTo(mLength/2,height+top);
        mPath.lineTo((float) (mLength*1.5),height+top);
        mPath.lineTo(2*mLength,height/2+top);
        mPath.lineTo((float) (mLength*1.5),top);
        mPath.lineTo(mLength/2,top);
        mPath.close();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawPath(mPath,mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        if (mPaint != null) {
            mPaint.setAlpha(alpha);
        }
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        if (mPaint != null) {
            mPaint.setColorFilter(colorFilter) ;
        }
    }

    @Override
    public int getOpacity() {
        return 0 ;
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        mRect.set(left, top, right, bottom);
        initPath();
    }

    @Override
    public int getIntrinsicWidth() {
        if (mBitmap != null) {
            return mBitmap.getWidth();
        } else {
            return super.getIntrinsicWidth() ;
        }
    }

    @Override
    public int getIntrinsicHeight() {
        if (mBitmap != null) {
            return mBitmap.getHeight() ;
        }
        return super.getIntrinsicHeight();
    }
}
