package water.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bestone.water.R;
import com.bestone.water.databinding.ActivityOtpVerificationBinding;
import com.stfalcon.smsverifycatcher.SmsVerifyCatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import water.utils.CustomDialogBuilder;
import water.utils.GlobalUtils;
import water.viewmodel.OtpViewModel;
import water.viewmodelfactory.ViewModelFactory;

import static water.viewmodel.LoginViewModel.MOBILE;
import static water.viewmodel.LoginViewModel.OTP;
import static water.viewmodel.LoginViewModel.TYPE;

public class ActivityOtpVerfication extends AppCompatActivity {
    ActivityOtpVerificationBinding binding;
    OtpViewModel model;
    private SmsVerifyCatcher smsVerifyCatcher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(ActivityOtpVerfication.this, R.layout.activity_otp_verification);
        model = ViewModelProviders.of(this, new ViewModelFactory(new OtpViewModel(this, binding)).createFor()).get(OtpViewModel.class);
        intialize();
        initObserve();
    }

    private void intialize() {
        Bundle bundle = getIntent().getExtras();
        model.mobile  = bundle.getString(MOBILE);
        model.type  = bundle.getString(TYPE);
        binding.setViewModel(model);

    }

    private String parseCode(String message) {
        Pattern p = Pattern.compile("\\b\\d{4}\\b");
        Matcher m = p.matcher(message);
        String code = "";
        while (m.find()) {
            code = m.group(0);
        }
        return code;
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
}
