package water.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bestone.water.R;
import com.bestone.water.databinding.GridItemListBinding;


import java.util.ArrayList;
import java.util.List;

import water.response.GridResponse;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridAdapterViewHolder> {

    private List<GridResponse> list;
    private Context context;
    private OnClickListener clickListener;


    public GridAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();


    }

    @NonNull
    @Override
    public GridAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GridItemListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.grid_item_list, parent, false);
        GridAdapterViewHolder viewHolder = new GridAdapterViewHolder(binding.getRoot());
        viewHolder.setBinding(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GridAdapterViewHolder holder, int position) {
        holder.setmodel(position);
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    public interface OnClickListener {
        void onClick(GridAdapter adapter, int position);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.clickListener = listener;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void update(List<GridResponse> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class GridAdapterViewHolder extends RecyclerView.ViewHolder {
        GridItemListBinding binding;

        public GridAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            if (binding != null) {
                binding.executePendingBindings();
            }
        }
        public void setmodel(int position) {


//            binding.gridlayout.setOnClickListener(v -> {
//               // clickListener.onClick(GridAdapter.this, 0);
//                Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show();
//
//               });
        }
        public void setBinding(GridItemListBinding gridItemListBinding) {
            this.binding = gridItemListBinding;
          /*  binding.rlMenuList.setOnClickListener(v -> {
               *//* Intent intent = new Intent(binding.getRoot().getContext(), TopItemForYoyActivity.class);
                binding.getRoot().getContext().startActivity(intent);*//*

                Toast.makeText(context, "Brand LIst", Toast.LENGTH_SHORT).show();
            });*/
        }
    }
}
