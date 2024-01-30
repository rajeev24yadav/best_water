package water.viewmodel;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bestone.water.R;
import com.bumptech.glide.Glide;

public class Binders {

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).placeholder(R.drawable.login_logo).into(imageView);
    }
}
