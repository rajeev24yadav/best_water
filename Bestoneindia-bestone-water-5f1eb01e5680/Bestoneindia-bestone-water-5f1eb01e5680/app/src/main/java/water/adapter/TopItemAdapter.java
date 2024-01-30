package water.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bestone.water.R;
import com.bestone.water.databinding.LayoutTopItemsListBinding;

import java.util.ArrayList;
import java.util.List;

import water.model.TopItemModel;
import water.view.activity.TopItemForYoyActivity;

public class TopItemAdapter extends RecyclerView.Adapter<TopItemAdapter.TopItemViewHolder> {
    private List<TopItemModel> list=new ArrayList<>();
    private Context context;
    private List<TopItemModel> topItemModels;

    public TopItemAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TopItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutTopItemsListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_top_items_list, parent, false);
        TopItemViewHolder viewHolder = new TopItemViewHolder(binding.getRoot());
        viewHolder.setBinding(binding);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TopItemViewHolder holder, int position) {
//      holder.setmodel(list.get(position));
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void update(List<TopItemModel> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

  public static class TopItemViewHolder extends RecyclerView.ViewHolder {
        LayoutTopItemsListBinding binding;

      public TopItemViewHolder(@NonNull View itemView) {
          super(itemView);
      }

      public void setBinding(LayoutTopItemsListBinding layoutTopItemsListBinding){
          this.binding = layoutTopItemsListBinding;
          binding.cardView2.setOnClickListener(v -> {
              Intent intent = new Intent(binding.getRoot().getContext(), TopItemForYoyActivity.class);
              binding.getRoot().getContext().startActivity(intent);
          });
        }
        public void setmodel(TopItemModel topItemModel) {
          //  binding.setModel(topItemModel);
        }

    }
}
