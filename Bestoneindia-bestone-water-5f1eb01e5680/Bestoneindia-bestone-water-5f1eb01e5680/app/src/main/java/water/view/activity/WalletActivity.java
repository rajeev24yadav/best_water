package water.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bestone.water.R;
import com.bestone.water.databinding.ActivityWalletBinding;


import water.viewmodel.WalletViewModel;
import water.viewmodelfactory.ViewModelFactory;

public class WalletActivity extends AppCompatActivity {
    ActivityWalletBinding binding;

    WalletViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wallet);
        viewModel = ViewModelProviders.of(this, new ViewModelFactory(new WalletViewModel(this,WalletActivity.this)).createFor()).get(WalletViewModel.class);

        intialize();
    }

    private void intialize() {
        binding.setWalletviewModel(viewModel);

    }
}
