package water.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.bestone.water.R;
import com.bestone.water.databinding.LayoutRecentItemListBinding;


import java.util.ArrayList;
import java.util.List;

import water.model.RecentListModel;

public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.TopItemViewHolder> {
    private List<RecentListModel> list;
    private Context context;

    public SearchItemAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();

    }

    @NonNull
    @Override
    public TopItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutRecentItemListBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_search_list_item_list, parent, false);
        TopItemViewHolder viewHolder = new TopItemViewHolder(binding.getRoot());
        viewHolder.setrecentBinding(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TopItemViewHolder holder, int position) {
        holder.setmodel(position);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void update(List<RecentListModel> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class TopItemViewHolder extends RecyclerView.ViewHolder {
        LayoutRecentItemListBinding binding;

        public TopItemViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            if (binding != null) {
                binding.executePendingBindings();
            }
        }

        public void setmodel(int position) {

//          //  binding.setCopunmodel(list.get(position));
//            if (list.get(position).getName() != null) {
//           //     binding.tvPickup.setText(list.get(position).getName());
//
//            }
        }

        public void setrecentBinding(LayoutRecentItemListBinding layoutTopItemsListBinding) {
            this.binding = layoutTopItemsListBinding;
        }
    }

}
