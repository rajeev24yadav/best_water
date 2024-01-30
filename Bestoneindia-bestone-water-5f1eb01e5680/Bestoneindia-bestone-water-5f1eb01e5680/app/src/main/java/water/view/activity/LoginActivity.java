package water.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bestone.water.R;
import com.bestone.water.databinding.ActivityLoginBinding;

import water.utils.CustomDialogBuilder;
import water.utils.GlobalUtils;
import water.viewmodel.LoginViewModel;
import water.viewmodelfactory.ViewModelFactory;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    LoginViewModel model;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);
        model = ViewModelProviders.of(this, new ViewModelFactory(new LoginViewModel(this,binding)).createFor()).get(LoginViewModel.class);
        intialize();
        initObserve();


    }
    private void initObserve() {
        CustomDialogBuilder customDialogBuilder = new CustomDialogBuilder(this);
        model.isloading.observe(this, isLoading -> {
            if (isLoading) {
                customDialogBuilder.showLoadingDialog();
            } else {
                customDialogBuilder.hideLoadingDialog();
            }
        });
        model.toast.observe(this, s -> {
            if (s != null && !s.isEmpty()) {
                GlobalUtils.showToast(this, s);
            }
        });

    }

    private void intialize() {
        binding.setViewmodel(model);
    }


}
