package water.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bestone.water.R;
import com.bestone.water.databinding.LayoutPopularBrandListBinding;
import com.bumptech.glide.Glide;


import java.util.ArrayList;
import java.util.List;

import water.model.PickupModelList;
import water.model.PopularBrandList;

public class PopularBrandAdapter extends RecyclerView.Adapter<PopularBrandAdapter.TopItemViewHolder> {
    private static final String TAG = "PopularBrandAdapter";
    private List<PickupModelList> list;
    private Context mcontext;

    public PopularBrandAdapter(Context context) {
        this.mcontext = context;
        this.list = new ArrayList<>();

    }

    @NonNull
    @Override
    public TopItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutPopularBrandListBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_popular_brand_list, parent, false);
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
        LayoutPopularBrandListBinding binding;

        public TopItemViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            if (binding != null) {
                binding.executePendingBindings();
            }
        }

        @SuppressLint("SetTextI18n")
        public void setmodel(int position) {

            binding.setPopularmodel(list.get(position));
            if (list.get(position).getPrice() != null) {
                binding.productPrice.setText("RS " + list.get(position).getPrice());
                binding.productName.setText(list.get(position).getProduct_name());
                Glide
                        .with(mcontext)
                        .load(list.get(position).getImage_url())
                        .centerCrop()
                        .placeholder(R.drawable.cart)
                        .into(binding.ivPopularBrandImage);

                binding.ivPopularBrandImage.setOnClickListener(v -> {

                    if (mItemClickListener != null)
                        mItemClickListener.onItemClick(list.get(position).getProduct_id());
                });
            }
        }

        public void setBinding(LayoutPopularBrandListBinding layoutTopItemsListBinding) {
            this.binding = layoutTopItemsListBinding;
        }
    }

    PopularBrandAdapter.OnItemClickListener mItemClickListener;
    public void setOnItemClickListener(final PopularBrandAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {

        void onItemClick(String product_id);
    }
}
