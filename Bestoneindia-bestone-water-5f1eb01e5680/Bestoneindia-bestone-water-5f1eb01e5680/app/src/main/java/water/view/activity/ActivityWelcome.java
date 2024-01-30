package water.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bestone.water.R;
import com.bestone.water.databinding.ActivityWelcomeBinding;

import water.utils.BWaterPreferences;
import water.viewmodel.ActivityWelcomeViewModel;
import water.viewmodelfactory.ViewModelFactory;

public class ActivityWelcome extends AppCompatActivity {
    ActivityWelcomeBinding activityWelcomeBinding;
    ActivityWelcomeViewModel  model;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityWelcomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);
        model = ViewModelProviders.of(this, new ViewModelFactory(new ActivityWelcomeViewModel(this)).createFor()).get(ActivityWelcomeViewModel.class);

        activityWelcomeBinding.setWelcomeviewmodel(model);
        initialize();
    }


    private void initialize() {
        if (!BWaterPreferences.getUserLoggedIn(this)) {
            startActivity(new Intent(ActivityWelcome.this, LoginActivity.class));
        } else {
            Intent intent = new Intent(this, Mainactivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

}
