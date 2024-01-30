package water.viewmodel;

import android.app.Activity;
import android.content.Context;

import androidx.lifecycle.ViewModel;

public class ScheduleViewModel extends ViewModel {
    private Context context;
    private Activity activity;

    public ScheduleViewModel(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void onBackPressed() {
        activity.onBackPressed();
    }
}
