package water.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bestone.water.R;
import com.bestone.water.databinding.ActivityNearByBinding;

import water.viewmodel.NearbyViewModel;
import water.viewmodelfactory.ViewModelFactory;

public class ShopNearByActivity extends AppCompatActivity {
    ActivityNearByBinding binding;
    NearbyViewModel viewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_near_by);
        viewModel = ViewModelProviders.of(this, new ViewModelFactory(new NearbyViewModel(this,ShopNearByActivity.this)).createFor()).get(NearbyViewModel.class);

        intialize();
    }
    private void intialize() {
        binding.setNearviewModel(viewModel);
    }
}
