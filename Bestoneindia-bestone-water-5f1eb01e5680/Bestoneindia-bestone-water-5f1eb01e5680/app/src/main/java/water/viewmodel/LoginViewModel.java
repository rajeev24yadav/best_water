package water.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bestone.water.R;
import com.bestone.water.databinding.ActivityLoginBinding;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import water.model.ErrorResponse;
import water.retorfit.ApiInterfaceService;
import water.utils.ConnectivityUtils;
import water.utils.Constants;
import water.utils.GlobalUtils;
import water.view.activity.ActivityOtpVerfication;
import water.view.activity.SignUpActivity;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.HttpException;

public class LoginViewModel extends ViewModel {
    public static String MOBILE = "mobile";
    public static String OTP = "otp";
    public static String TYPE = "type";
    private static final String TAG = "LoginViewModel";
    public MutableLiveData<String> toast = new MutableLiveData<>();
    public ObservableBoolean isMobileLoading = new ObservableBoolean(false);
    public ObservableBoolean isMobileExist = new ObservableBoolean(true);
    public ObservableInt length = new ObservableInt();
    public MutableLiveData<Boolean> isloading = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> isSuccess = new MutableLiveData<>();
    public Context context;
    private CompositeDisposable disposable = new CompositeDisposable();
    public ActivityLoginBinding binding;
    private String number;

    public LoginViewModel(Context context, ActivityLoginBinding binding) {
        this.context = context;
        this.binding = binding;

    }

    //disposable.add(ApiInterfaceService.initRetrofit().login("application/json", hashMap)
    public void onNextScreen() {
        if (ConnectivityUtils.isConnected(context)) {
            if (validation()) {

                disposable.add(ApiInterfaceService.initRetrofit().login(Constants.TOKEN, binding.etMobile.getText().toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .unsubscribeOn(Schedulers.io())
                        .doOnSubscribe(disposable1 -> isloading.setValue(true))
                        .doOnTerminate(() -> isloading.setValue(false))
                        .subscribe((singup, throwable) -> {
                            if (singup != null) {
                                toast.setValue(singup.getMessage());

                                if (singup.isStatus()) {
                                    Log.e(TAG, "singup: "+singup.getOtp() );
                                    Intent intent = new Intent(context, ActivityOtpVerfication.class);
                                    intent.putExtra(MOBILE, binding.etMobile.getText().toString());
                                    intent.putExtra(TYPE, "login");
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(intent);
                                }

                            } else {
                                try {
                                    HttpException httpException = (HttpException) throwable;
                                    ResponseBody errorBody = httpException.response().errorBody();

                                    ErrorResponse errorResponse = new Gson().fromJson(errorBody.string(), ErrorResponse.class);
                                    toast.setValue(errorResponse.getMessage());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }));
            }
        }
    }

    private boolean validation() {
        number = binding.etMobile.getText().toString();
        if (!GlobalUtils.validatename(number)) {
            binding.etMobile.setError("Enter mobile No.");
            binding.etMobile.requestFocus();
            return false;
        } else if (!checkMobileValidation(number)) {
            binding.etMobile.setError("Enter valid mobile No.");
            binding.etMobile.requestFocus();
            return false;
        }
        return true;
    }

    public void onSignUpScreen() {
        Intent intent = new Intent(context, SignUpActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

    public boolean checkMobileValidation(String user_mobile_no) {
        String checkString = user_mobile_no;
        String pattern = "[0-9]+";
        if (checkString.length() > 10) {
            toast.setValue(context.getString(R.string.invalid_mobile_number));
            return false;
        } else if (checkString.length() < 10) {
            toast.setValue(context.getString(R.string.Mobile_digit_less_than_10_digit));
            return false;
        }
        return true;
    }

}
