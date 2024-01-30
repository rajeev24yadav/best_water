package water.viewmodel;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.ViewModel;

import java.util.Observable;

import water.adapter.GridAdapter;
import water.view.activity.SearchListActivity;

import static water.view.fragment.GridFragment.BUNDLE_KEY;

public class GridViewModel extends ViewModel {
    private Context context;
    public Observable banner;
    public GridAdapter adapter = new GridAdapter(context);

    public GridViewModel(Context context) {
        this.context = context;
    }

    public void onItemClick() {
        adapter.setOnClickListener((adapter1, position) -> {
            Intent intent = new Intent(context, SearchListActivity.class);
            intent.putExtra(BUNDLE_KEY, "name");
            context.startActivity(intent);
        });
    }
}

