package water.view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bestone.water.R;
import com.bestone.water.databinding.ActivitySignUpBinding;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.api.GoogleApiClient;

import water.utils.CustomDialogBuilder;
import water.utils.DatePicker.date.DatePicker;
import water.utils.GlobalUtils;
import water.viewmodel.SignUpViewModel;
import water.viewmodelfactory.ViewModelFactory;
import timber.log.Timber;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignUpActivity";
    private ActivitySignUpBinding binding;
    private SignUpViewModel signUpViewModel;
    DatePicker datePicker;
    String selected_date;
    private GoogleApiClient googleApiClient;
    private static int RESOLVE_HINT = 2;
    private String phoneNumber = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        signUpViewModel = ViewModelProviders.of(this, new ViewModelFactory(new SignUpViewModel(this, binding)).createFor()).get(SignUpViewModel.class);
        intialize();
        initObserve();
        initListener();
    }

    private void initListener() {
        binding.etAge.setOnClickListener(v -> {
            showDataPicker();
        });
    }

    private void showDataPicker() {
        final Dialog setDate = new Dialog(SignUpActivity.this);
        setDate.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setDate.setContentView(R.layout.dialog_dateset);
        setDate.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setDate.show();
        datePicker = setDate.findViewById(R.id.datePicker);

        datePicker.setOnDateSelectedListener((year, month, day) -> {
            selected_date = datePicker.getDate();
            int age = signUpViewModel.checkAgeValidatin(selected_date);

           // if (18 < age) {
                binding.etAge.setText(getCurrentDate());
                signUpViewModel.age = age;
                Log.e("selected date", "date: " + datePicker.getDate());

//            } else {
//                GlobalUtils.showToast(this, getString(R.string.Age_should_be_greater_than_18));
//            }
        });


    }

    public String getCurrentDate() {
        StringBuilder builder = new StringBuilder();

        builder.append(datePicker.getYear()).append("-");
        if (datePicker.getMonth() < 10) {
            builder.append("0").append(datePicker.getMonth()).append("-");
        } else {
            builder.append(datePicker.getMonth()).append("-");
        }
        if (datePicker.getDay() < 10) {
            builder.append("0").append(datePicker.getDay()).append("-");
        } else {
            builder.append(datePicker.getDay());
        }


        return builder.toString();
    }

    public void getHintPhoneNumber() {
        try {
            HintRequest hintRequest = new HintRequest.Builder()
                    .setPhoneNumberIdentifierSupported(true)
                    .build();
            PendingIntent intent = Auth.CredentialsApi.getHintPickerIntent(googleApiClient, hintRequest);
            startIntentSenderForResult(intent.getIntentSender(), RESOLVE_HINT, null, 0, 0, 0);
        } catch (IntentSender.SendIntentException e) {
            Timber.e(e);
        }
    }

    private void initObserve() {
        CustomDialogBuilder customDialogBuilder = new CustomDialogBuilder(this);
        signUpViewModel.isloading.observe(this, isLoading -> {
            if (isLoading) {
                customDialogBuilder.showLoadingDialog();
            } else {
                customDialogBuilder.hideLoadingDialog();
            }
        });
        signUpViewModel.toast.observe(this, s -> {
            if (s != null && !s.isEmpty()) {
                GlobalUtils.showToast(this, s);
            }
        });
    }


    private void intialize() {
        phoneNumber = binding.etMobile.getText().toString();
        //   getHintPhoneNumber();
        binding.setSignupviewModel(signUpViewModel);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESOLVE_HINT) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
                    if (credential != null) {
                        phoneNumber = credential.getId();
                        correctPhoneNumber(phoneNumber);
                    }
                }
            }
        }
    }

    public void correctPhoneNumber(String str) {
        str = str.replace("+91", "");
        str = str.replaceAll(" ", "");
        phoneNumber = str;
        binding.etMobile.setText(phoneNumber);
        binding.etMobile.setSelection(binding.etMobile.getText().length());
    }
}
