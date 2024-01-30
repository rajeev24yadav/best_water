package water.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bestone.water.R;
import com.bestone.water.databinding.ActivityScheduleBinding;

import water.viewmodel.ScheduleViewModel;
import water.viewmodelfactory.ViewModelFactory;

public class SchedulerActivity extends AppCompatActivity {
    ActivityScheduleBinding binding;
    ScheduleViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_schedule);
        viewModel = ViewModelProviders.of(this, new ViewModelFactory(new ScheduleViewModel(this, SchedulerActivity.this)).createFor()).get(ScheduleViewModel.class);
        intialize();
    }

    private void intialize() {
        binding.setSheduleviewModel(viewModel);
    }
}
