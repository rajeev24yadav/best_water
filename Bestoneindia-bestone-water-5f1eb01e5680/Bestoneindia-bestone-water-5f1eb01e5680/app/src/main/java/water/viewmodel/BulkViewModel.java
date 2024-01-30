package water.viewmodel;

import android.app.Activity;
import android.content.Context;

import androidx.lifecycle.ViewModel;

import water.adapter.BulkOrderAdapter;
import water.adapter.TopitemForyouAdapter;

public class BulkViewModel extends ViewModel {
    private Context context;
    private Activity activity;
    public BulkOrderAdapter Bulkorderadapter = new BulkOrderAdapter(context);
    public BulkViewModel(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void onBackPressed() {
        activity.onBackPressed();
    }
}
