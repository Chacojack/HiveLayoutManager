package jack.hive;

import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by zjchai on 16/9/10.
 */
public class ImageViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView ;

    public ImageViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.img_img);
    }

    public void bind(Integer resId) {
        imageView.setImageDrawable(new HiveDrawable(BitmapFactory.decodeResource(imageView.getResources(),resId)));
    }
}
