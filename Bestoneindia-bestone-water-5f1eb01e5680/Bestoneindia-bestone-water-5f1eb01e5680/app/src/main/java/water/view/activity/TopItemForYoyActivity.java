package water.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.bestone.water.R;
import com.bestone.water.databinding.ActivityTopItemForYoyBinding;

import water.viewmodel.TopitemforYouViewModel;
import water.viewmodelfactory.ViewModelFactory;

public class TopItemForYoyActivity extends AppCompatActivity {
    ActivityTopItemForYoyBinding binding;
    TopitemforYouViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_top_item_for_yoy);
        viewModel = ViewModelProviders.of(this, new ViewModelFactory(new TopitemforYouViewModel(this,TopItemForYoyActivity.this)).createFor()).get(TopitemforYouViewModel.class);

        intialize();
    }
    private void intialize() {
        binding.setViewModel(viewModel);
    }
}
