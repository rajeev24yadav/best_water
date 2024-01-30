package water.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bestone.water.R;
import com.bestone.water.databinding.ActivityFavouriteBinding;

import water.viewmodel.FavouriteViewModel;
import water.viewmodelfactory.ViewModelFactory;

public class FavouriteActivity extends AppCompatActivity {
    ActivityFavouriteBinding binding;
    FavouriteViewModel viewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_favourite);
        viewModel = ViewModelProviders.of(this, new ViewModelFactory(new FavouriteViewModel(this,FavouriteActivity.this)).createFor()).get(FavouriteViewModel.class);

        intialize();
    }
    private void intialize() {
        binding.setFavviewModel(viewModel);
    }
}
