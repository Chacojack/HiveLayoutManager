package jack.hive;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjchai on 16/9/10.
 */
public class HiveAdapter extends RecyclerView.Adapter<ImageViewHolder>{


    List<Integer> resId = new ArrayList<>() ;

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vh_img, parent,false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        holder.bind(resId.get(position),position) ;
    }

    @Override
    public int getItemCount() {
        return resId.size();
    }

    public void addData(Integer data,int index) {
        resId.add(index,data) ;
    }

    public void addData(Integer data) {
        resId.add(data) ;
    }

    public void remove(int index){
        resId.remove(index) ;
    }

    public void move(int r, int r2) {
        Integer id = resId.remove(r);
        resId.add(r2,id) ;
    }
}
