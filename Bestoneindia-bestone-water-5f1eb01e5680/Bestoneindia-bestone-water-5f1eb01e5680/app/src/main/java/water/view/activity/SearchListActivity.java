package water.view.activity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.bestone.water.R;
import com.bestone.water.databinding.SearchListActiivtyBinding;

import water.base.BaseActivity;
import water.viewmodel.SearchViewModel;

public class SearchListActivity extends BaseActivity {
    SearchListActiivtyBinding binding;
    SearchViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.search_list_actiivty);


    }
}
