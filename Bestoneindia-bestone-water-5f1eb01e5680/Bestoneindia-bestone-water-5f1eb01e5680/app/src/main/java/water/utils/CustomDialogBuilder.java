package water.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.Window;

import androidx.databinding.DataBindingUtil;

import com.bestone.water.R;
import com.bestone.water.databinding.DailogLoaderBinding;

public class CustomDialogBuilder {
    private Context mContext;
    private Dialog mBuilder = null;

    public CustomDialogBuilder(Context context) {
        this.mContext = context;
        if (mContext != null) {
            mBuilder = new Dialog(mContext);
            mBuilder.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mBuilder.setCancelable(false);
            mBuilder.setCanceledOnTouchOutside(false);

            if (mBuilder.getWindow() != null) {
                mBuilder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
        }
    }
    public void showLoadingDialog() {
        if (mContext == null)
            return;
        mBuilder.setCancelable(true);
        mBuilder.setCanceledOnTouchOutside(true);
        DailogLoaderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dailog_loader, null, false);

        mBuilder.setContentView(binding.getRoot());
        mBuilder.show();
    }

    public void hideLoadingDialog() {
        try {
            mBuilder.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
