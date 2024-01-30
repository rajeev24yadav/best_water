package water.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bestone.water.R;
import com.bestone.water.databinding.LayoutTopItemsForYouListBinding;


import java.util.List;

import water.model.TopItemModel;

public class TopitemForyouAdapter extends RecyclerView.Adapter<TopitemForyouAdapter.topitemforyouViewholder> {
    private List<TopItemModel> list;
    private Context context;
    private List<TopItemModel> topItemModels;

    public TopitemForyouAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public TopitemForyouAdapter.topitemforyouViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutTopItemsForYouListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_top_items_for_you_list, parent, false);
        TopitemForyouAdapter.topitemforyouViewholder viewHolder = new TopitemForyouAdapter.topitemforyouViewholder(binding.getRoot());
        viewHolder.setBinding(binding);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TopitemForyouAdapter.topitemforyouViewholder holder, int position) {
        holder.setmodel(position);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class topitemforyouViewholder extends RecyclerView.ViewHolder {
        LayoutTopItemsForYouListBinding binding;

        public topitemforyouViewholder(@NonNull View itemView) {
            super(itemView);
        }



        public void setBinding(LayoutTopItemsForYouListBinding binding){
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
