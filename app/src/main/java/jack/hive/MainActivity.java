package jack.hive;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int[] resIds = new int[]{R.drawable.img_1,R.drawable.img_1,R.drawable.img_1,R.drawable.img_1,R.drawable.img_1} ;

    RecyclerView recyclerView ;
    HiveAdapter adapter ;

//    HiveImageView hiveImageView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initObjects() ;

        initViews() ;
        afterViews() ;

    }

    private void initObjects() {
        adapter = new HiveAdapter() ;
    }

    private void initViews() {
//        hiveImageView = (HiveImageView) findViewById(R.id.img_hive) ;
        recyclerView = (RecyclerView) findViewById(R.id.list) ;
    }

    private void afterViews() {
        recyclerView.setLayoutManager(new HiveLayoutManager()) ;
        recyclerView.setAdapter(adapter);
        for (int i = 0; i < resIds.length; i++) {
            adapter.addData(resIds[i]);
        }



//        hiveImageView.setImageDrawable(new HiveDrawable(BitmapFactory.decodeResource(getResources(),R.drawable.img_1)));

    }


}
