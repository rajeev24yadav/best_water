package water.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bestone.water.R;
import com.bestone.water.databinding.ActivityMyBulkBinding;

import water.viewmodel.BulkViewModel;
import water.viewmodelfactory.ViewModelFactory;

public class BulkOrders extends AppCompatActivity {
    ActivityMyBulkBinding binding;
    BulkViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_bulk);
        viewModel = ViewModelProviders.of(this, new ViewModelFactory(new BulkViewModel(this,BulkOrders.this)).createFor()).get(BulkViewModel.class);
        intialize();
    }

    private void intialize() {
        binding.setBulkviewModel(viewModel);
    }
}
