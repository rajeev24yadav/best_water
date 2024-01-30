package water.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.bestone.water.R;
import com.bestone.water.databinding.CartItemListBinding;


import java.util.ArrayList;
import java.util.List;
import water.model.CartModel;


public class AddCartAdapter extends RecyclerView.Adapter<AddCartAdapter.TopItemViewHolder> {
    private List<CartModel> list;
    private Context context;
    private int quantity = 0;
    public AddCartAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();

    }

    @NonNull
    @Override
    public TopItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartItemListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.cart_item_list, parent, false);
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
        return 4;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void update(List<CartModel> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class TopItemViewHolder extends RecyclerView.ViewHolder {
        CartItemListBinding binding;

        public TopItemViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            if (binding != null) {
                binding.executePendingBindings();
            }
        }

        public void setmodel(int position) {

            //    binding.setChaknamodel(list.get(position));
//            if (list.get(position).getImage() != null) {
//         //       ImageLoaderUtils.load(context,list.get(position).getImage(), binding.ivPopularBrandImage);
//
//            }
        }
        public void setBinding(CartItemListBinding cartItemListBinding){
            this.binding = cartItemListBinding;
           /* binding.btnAddMore.setOnClickListener(v -> {
                quantity = quantity + 1;
                binding.tvItemcount.setText(quantity);
                Log.e("=====","Add Item");
            });
            binding.btnAddLess.setOnClickListener(v -> {
                Log.e("=====","Less Item");
                if (quantity>0){
                    quantity = quantity - 1;
                    binding.tvItemcount.setText(quantity);
                }
            });*/

        }

    }
}
