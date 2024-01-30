package water.viewmodel;

import android.app.Activity;
import android.content.Context;

import androidx.lifecycle.ViewModel;

public class OfferViewModel extends ViewModel {
    private Context context;
    private Activity activity;

    public OfferViewModel(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void onBackPressed() {
        activity.onBackPressed();
    }
}
