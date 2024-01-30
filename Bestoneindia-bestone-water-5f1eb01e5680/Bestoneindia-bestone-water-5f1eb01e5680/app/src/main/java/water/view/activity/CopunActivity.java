package water.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bestone.water.R;
import com.bestone.water.databinding.ActivityCouponBinding;

import water.viewmodel.CoupunViewModel;
import water.viewmodelfactory.ViewModelFactory;

public class CopunActivity extends AppCompatActivity {
    ActivityCouponBinding binding;
    CoupunViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_coupon);
        viewModel = ViewModelProviders.of(this, new ViewModelFactory(new CoupunViewModel(this,CopunActivity.this)).createFor()).get(CoupunViewModel.class);
        intialize();
    }
    private void intialize() {
        binding.setCouponviewModel(viewModel);
    }
}
