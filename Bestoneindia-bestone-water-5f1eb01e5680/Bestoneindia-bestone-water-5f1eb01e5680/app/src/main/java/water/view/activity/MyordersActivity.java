package water.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bestone.water.R;
import com.bestone.water.databinding.ActivityMyOrderBinding;


import water.viewmodel.MyOrderViewModel;
import water.viewmodelfactory.ViewModelFactory;

public class MyordersActivity extends AppCompatActivity {
    ActivityMyOrderBinding binding;
    MyOrderViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_order);
        viewModel = ViewModelProviders.of(this, new ViewModelFactory(new MyOrderViewModel(this,MyordersActivity.this)).createFor()).get(MyOrderViewModel.class);
        intialize();
    }
    private void intialize() {
        binding.setOrderviewModel(viewModel);
    }

}
