package water.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bestone.water.R;
import com.bestone.water.databinding.LayoutPickupItemListBinding;

import java.util.ArrayList;
import java.util.List;

import water.model.PickupModelList;

public class PickupcategoriesAdapter extends RecyclerView.Adapter<PickupcategoriesAdapter.TopItemViewHolder> {
    private List<PickupModelList> list;
    private Context context;

    public PickupcategoriesAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();

    }

    @NonNull
    @Override
    public TopItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutPickupItemListBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_pickup_item_list, parent, false);
        TopItemViewHolder viewHolder = new TopItemViewHolder(binding.getRoot());
        viewHolder.setBinding(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TopItemViewHolder holder, int position) {
        holder.setmodel(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void update(List<PickupModelList> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class TopItemViewHolder extends RecyclerView.ViewHolder {
        LayoutPickupItemListBinding binding;

        public TopItemViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            if (binding != null) {
                binding.executePendingBindings();
            }
        }

        public void setmodel(int position) {
          binding.setPickupmodel(list.get(position));
            if (list.get(position).getPrice() != null) {
                binding.tvPickup.setText(list.get(position).getPrice());

            }
        }

        public void setBinding(LayoutPickupItemListBinding layoutTopItemsListBinding) {
            this.binding = layoutTopItemsListBinding;
        }
    }
}
