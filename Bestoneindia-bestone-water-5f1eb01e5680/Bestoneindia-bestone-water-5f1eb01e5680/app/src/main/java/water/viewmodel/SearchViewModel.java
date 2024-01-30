package water.viewmodel;

import android.app.Activity;
import android.content.Context;

import androidx.lifecycle.ViewModel;

import water.adapter.PopularBrandAdapter;
import water.adapter.RecentAdapter;
import water.adapter.SearchItemAdapter;

public class SearchViewModel extends ViewModel {
    public Context context;
    public Activity activity;

    public RecentAdapter adapter = new RecentAdapter(context);
    public PopularBrandAdapter popularBrandAdapter = new PopularBrandAdapter(context);
    public SearchItemAdapter searchItemAdapter = new SearchItemAdapter(context);


    public SearchViewModel(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void onBackPressed() {
        activity.onBackPressed();
    }
}
