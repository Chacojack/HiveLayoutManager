package jack.hive;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by zjchai on 16/9/10.
 */
public class ImageViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView textView ;

    public ImageViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.img_img);
        textView = (TextView) itemView.findViewById(R.id.number);
    }

    public void bind(Integer resId,int position) {
        Bitmap bitmap = BitmapCache.INSTANCE.getBitmap(resId);
        HiveDrawable drawable = new HiveDrawable(HiveLayoutManager.HORIZONTAL,bitmap);
        imageView.setImageDrawable(drawable);
        textView.setText(String.valueOf(position));
        textView.setVisibility(View.GONE);
    }
}
