package jack.hive;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

//    RecyclerView recyclerView ;
    HiveImageView hiveImageView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews() ;
        afterViews() ;

    }

    private void initViews() {
        hiveImageView = (HiveImageView) findViewById(R.id.img_hive) ;
//        recyclerView = (RecyclerView) findViewById(R.id.list) ;
    }

    private void afterViews() {
//        recyclerView.setLayoutManager(new HiveLayoutManager()) ;
        hiveImageView.setImageDrawable(new HiveDrawable(BitmapFactory.decodeResource(getResources(),R.drawable.img_1)));
    }


}
