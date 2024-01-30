package water.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.bestone.water.R;
import com.bestone.water.databinding.NewHomeBannerPagerAdapterBinding;
import com.bumptech.glide.Glide;


import java.util.ArrayList;
import java.util.List;

import water.model.AllBanner;

public class BannerAdapter extends PagerAdapter {
    private Context context;
    private List<String> banners = new ArrayList<>();

    NewHomeBannerPagerAdapterBinding binding;
    private static final String TAG = "BannerAdapter";

    public BannerAdapter(Context context, ArrayList<String> banners) {
        this.context = context;
        this.banners = banners;
    }

    @Override
    public int getCount() {
        return banners.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View sliderView = LayoutInflater.from(context).inflate(R.layout.new_home_banner_pager_adapter, container, false);

        ImageView imageView = sliderView.findViewById(R.id.iv_banner);
        String url = banners.get(position);
        Log.e(TAG, "instantiateItem: "+banners.get(position));
//        imageView.setBackgroundResource(R.drawable.banner_1);
        Glide.with(context).load(url).placeholder(R.drawable.banner_1).into(imageView);
        Log.e(TAG, "url: "+url);
        return sliderView;
    }

    public void update(List<String> list) {
        Log.e(TAG, "list: "+list.size());
      //  this.banners.clear();
        this.banners.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }


}
