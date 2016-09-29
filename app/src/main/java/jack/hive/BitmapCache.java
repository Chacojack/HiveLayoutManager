package jack.hive;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;

/**
 * Created by zjchai on 16/9/29.
 */

public enum BitmapCache {

    INSTANCE;

    LruCache<Integer, Bitmap> mCache;
    Context mContext;

    public void init(Context context, int size) {
        mCache = new LruCache<>(size);
        mContext = context.getApplicationContext();
    }

    public Bitmap getBitmap(int resId) {
        Bitmap bitmap = mCache.get(resId);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(mContext.getResources(),resId) ;
            mCache.put(resId,bitmap) ;
        }
        return bitmap;
    }
}
