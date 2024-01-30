package water.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bestone.water.R;
import com.bestone.water.databinding.ActivityOfferBinding;

import water.viewmodel.OfferViewModel;
import water.viewmodelfactory.ViewModelFactory;

public class OfferActivity extends AppCompatActivity {
    ActivityOfferBinding binding;
    OfferViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_offer);
        viewModel = ViewModelProviders.of(this, new ViewModelFactory(new OfferViewModel(this,OfferActivity.this)).createFor()).get(OfferViewModel.class);

        intialize();

    }
    private void intialize() {
        binding.setOfferviewModel(viewModel);
    }
}
