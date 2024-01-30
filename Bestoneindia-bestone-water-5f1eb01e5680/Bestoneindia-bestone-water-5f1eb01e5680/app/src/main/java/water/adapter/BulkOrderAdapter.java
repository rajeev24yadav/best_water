package water.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bestone.water.R;
import com.bestone.water.databinding.BulkorderListBinding;
import com.bestone.water.databinding.LayoutTopItemsForYouListBinding;

import java.util.List;

import water.model.TopItemModel;

public class BulkOrderAdapter extends RecyclerView.Adapter<BulkOrderAdapter.bulkOrderAdapter> {
    private List<TopItemModel> list;
    private Context context;
    private List<TopItemModel> topItemModels;

    public BulkOrderAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public BulkOrderAdapter.bulkOrderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // LayoutTopItemsForYouListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.bulkorder_list, parent, false);
        BulkorderListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.bulkorder_list, parent, false);
        BulkOrderAdapter.bulkOrderAdapter viewHolder = new BulkOrderAdapter.bulkOrderAdapter(binding.getRoot());
        viewHolder.setBinding(binding);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BulkOrderAdapter.bulkOrderAdapter holder, int position) {
        holder.setmodel(position);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class bulkOrderAdapter extends RecyclerView.ViewHolder {
        BulkorderListBinding binding;

        public bulkOrderAdapter(@NonNull View itemView) {
            super(itemView);
        }



        public void setBinding(BulkorderListBinding binding){
            this.binding = binding;

        }

        public void setmodel(int position) {


            //  binding.setModel(list.get(position));
        }
    }
    private String getbyte(){


        return "";
    }
}
