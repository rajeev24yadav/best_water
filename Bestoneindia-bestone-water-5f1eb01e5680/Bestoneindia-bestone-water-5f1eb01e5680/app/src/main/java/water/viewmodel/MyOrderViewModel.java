package water.viewmodel;

import android.app.Activity;
import android.content.Context;

import androidx.lifecycle.ViewModel;

import water.adapter.OrderAdapter;

public class MyOrderViewModel extends ViewModel {
    private Context context;
    private Activity activity;
    public OrderAdapter adapter = new OrderAdapter(context);

    public MyOrderViewModel(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void onBackPressed() {
        activity.onBackPressed();
    }
}
