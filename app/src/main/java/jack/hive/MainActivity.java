package jack.hive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final int[] resIds = new int[]{
            R.drawable.img_0
            , R.drawable.img_1
            , R.drawable.img_2
            , R.drawable.img_3
            , R.drawable.img_4
            , R.drawable.img_5
            , R.drawable.img_6
            , R.drawable.img_7
            , R.drawable.img_8
            , R.drawable.img_9
            , R.drawable.img_10
            , R.drawable.img_11
            , R.drawable.img_12
    };
    private static final String TAG = MainActivity.class.getSimpleName();

    RecyclerView recyclerView;
    HiveAdapter adapter;
    int index = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initObjects();

        initViews();
        afterViews();

    }

    private void initObjects() {
        adapter = new HiveAdapter();
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.list);
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int r = new Random().nextInt(adapter.getItemCount());
                Log.d(TAG, "onClick: r" + r);
                adapter.addData(resIds[index % resIds.length], r);
                adapter.notifyItemInserted(r);
                index++;
            }
        });
        findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int r = new Random().nextInt(adapter.getItemCount());
                Log.d(TAG, "onClick: r" + r);
                adapter.remove(r);
                adapter.notifyItemRemoved(r);
                index--;
            }
        });
        findViewById(R.id.move).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int r = new Random().nextInt(adapter.getItemCount());
                int r2 = new Random().nextInt(adapter.getItemCount());
                Log.d(TAG, "onClick: r" + r);
                adapter.move(r,r2);
                adapter.notifyItemMoved(r,r2);
            }
        });
    }

    private void afterViews() {
        recyclerView.setLayoutManager(new HiveLayoutManager(HiveLayoutManager.HORIZONTAL));
        recyclerView.setAdapter(adapter);
        for (int i = 0; i < index; i++) {
            adapter.addData(resIds[i]);
        }

    }


}
