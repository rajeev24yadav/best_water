package water.viewmodel;

import android.app.Activity;
import android.content.Context;

import androidx.lifecycle.ViewModel;

public class NearbyViewModel  extends ViewModel {
    private Context context;
    private Activity activity;

    public NearbyViewModel(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void onBackPressed() {
        activity.onBackPressed();
    }
}
