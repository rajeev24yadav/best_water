package water.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bestone.water.R;
import com.bestone.water.databinding.ActivityNewAddressBinding;

import water.viewmodel.AddNewAddressViewModel;
import water.viewmodelfactory.ViewModelFactory;

public class AddNewAddressActivity extends AppCompatActivity {
    ActivityNewAddressBinding binding;
    AddNewAddressViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_address);
        viewModel = ViewModelProviders.of(this, new ViewModelFactory(new AddNewAddressViewModel(this,AddNewAddressActivity.this)).createFor()).get(AddNewAddressViewModel.class);
        intialize();
    }
    private void intialize() {
        binding.setAddressviewModel(viewModel);
    }
}
