package water.viewmodel;

import android.app.Activity;
import android.content.Context;

import androidx.lifecycle.ViewModel;

import water.adapter.TopitemForyouAdapter;

public class TopitemforYouViewModel extends ViewModel {
    private Context context;
    private Activity activity;
    public TopitemForyouAdapter Topitemforyou_adapter = new TopitemForyouAdapter(context);
    public TopitemforYouViewModel(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void onBackPressed() {
        activity.onBackPressed();
    }
}
