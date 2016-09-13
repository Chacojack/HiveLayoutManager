package jack.hive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
                adapter.addData(resIds[index % resIds.length]);
                adapter.notifyDataSetChanged();
                index++;
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
