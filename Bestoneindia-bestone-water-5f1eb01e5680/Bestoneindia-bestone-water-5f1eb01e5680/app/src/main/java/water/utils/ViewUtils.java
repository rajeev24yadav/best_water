package water.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.WindowManager;

public class ViewUtils {
    public static void hideKeyboardOnStartActivity(Context context) {
        ((Activity) context).getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
    }
    public static int dpToPx(int dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    public static void loadFragment(){

    }


}
