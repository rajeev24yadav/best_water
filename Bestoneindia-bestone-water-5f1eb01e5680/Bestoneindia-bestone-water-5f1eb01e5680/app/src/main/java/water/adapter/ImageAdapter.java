package water.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.PagerAdapter;

import com.bestone.water.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ImageAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<String> arrayList;
    private LayoutInflater layoutInflater;

    public ImageAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == ((View) o);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.row_slider_image, container, false);

        AppCompatImageView ivProductImage = view.findViewById(R.id.ivProductImage);

        if (!TextUtils.isEmpty(arrayList.get(position))) {
            Glide.with(context)
                    .load(arrayList.get(position))
                    .apply(new RequestOptions().placeholder(R.drawable.ic_cart_icon).error(R.drawable.ic_cart_icon))
                    .into(ivProductImage);
        }

      //  Glide.with(view.getContext()).load(arrayList.get(position)).placeholder(R.drawable.ic_cart_icon).dontAnimate().into(ivProductImage);


        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}