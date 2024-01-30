package water.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bestone.water.R;
import com.bestone.water.databinding.ActivitySignUpBinding;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import water.model.ErrorResponse;
import water.model.SignUpResponse;
import water.retorfit.ApiInterfaceService;
import water.utils.ConnectivityUtils;
import water.utils.Constants;
import water.utils.GlobalUtils;
import water.view.activity.ActivityOtpVerfication;
import water.view.activity.LoginActivity;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.HttpException;

import static water.viewmodel.LoginViewModel.MOBILE;
import static water.viewmodel.LoginViewModel.OTP;
import static water.viewmodel.LoginViewModel.TYPE;

public class SignUpViewModel extends ViewModel {
    private static final String TAG = "SignUpViewModel";
    public MutableLiveData<Boolean> isloading = new MutableLiveData<>(false);
    public MutableLiveData<String> toast = new MutableLiveData<>();
    public MutableLiveData<Boolean> signUpSuccess = new MutableLiveData<>();
    private Context context;
    public String name = "", email = "", mobile = "";
    public int age;
    private ActivitySignUpBinding binding;
    private CompositeDisposable disposable = new CompositeDisposable();

    public SignUpViewModel(Context context, ActivitySignUpBinding binding) {
        this.context = context;
        this.binding = binding;
    }

    public void onLoginScreen() {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

    public void onHomeScreen() {
        if (validation()) {
            if (ConnectivityUtils.isConnected(context)) {

                disposable.add(ApiInterfaceService.initRetrofit().signUp(Constants.TOKEN, age,mobile,name,email)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .unsubscribeOn(Schedulers.io())
                        .doOnSubscribe(disposable1 -> isloading.setValue(true))
                        .doOnTerminate(() -> isloading.setValue(false))
                        .subscribe((singup, throwable) -> {
                            if (singup != null) {

                                toast.setValue(singup.getMessage());
                                if (singup.isStatus())
                                {
                                    Intent intent = new Intent(context, ActivityOtpVerfication.class);
                                    intent.putExtra(MOBILE, binding.etMobile.getText().toString());
                                    intent.putExtra(TYPE, "registration");
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(intent);
                                }

                            } else {
                                HttpException httpException = (HttpException) throwable;
                                ResponseBody errorBody = httpException.response().errorBody();
                                try {
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
        if (!GlobalUtils.validatename(name)) {
            binding.etName.setError("enter name");
            return false;
        } else if (!GlobalUtils.validatename(email)) {
            binding.etEmail.setError("enter email");
            return false;
        } else if (!GlobalUtils.ValidEmail(email)) {
            binding.etEmail.setError("enter Valid email");
            return false;
        } else if (!GlobalUtils.validatename(mobile)) {
            binding.etMobile.setError("enter mobile No.");
        } else if (!checkMobileValidation(mobile)) {
            binding.etMobile.setError("enter valid mobile No.");
            return false;
        }  else if (!GlobalUtils.validateDOB(String.valueOf(age))) {
            binding.etAge.setError("enter valid age ");
            return false;
        }
        return true;
    }

    public void afterTextChanged(CharSequence charSequence, int type) {
        switch (type) {
            case 1:
                name = charSequence.toString();
                break;
            case 2:
                email = charSequence.toString();
                break;
            case 3:
                mobile = charSequence.toString();
                break;
        }

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

    public static int checkAgeValidatin(String date) {
        int age = 0;
        boolean chkAge = false;
        DateFormat format = SimpleDateFormat.getDateInstance();
        try {
            Date date1 = format.parse(date);
            Calendar now = Calendar.getInstance();
            Calendar dob = Calendar.getInstance();
            assert date1 != null;

            dob.setTime(date1);
            if (dob.after(now)) {
                throw new IllegalArgumentException("Can't be born in the future");
            }
            int year1 = now.get(Calendar.YEAR);
            int year2 = dob.get(Calendar.YEAR);
            age = year1 - year2;
            if (age > 18) {
                chkAge = false;
            }
            if (age == 18) {
                int month1 = now.get(Calendar.MONTH);
                int month2 = dob.get(Calendar.MONTH);
                if (month1 > month2) {
                    chkAge = false;
                } else if (month1 == month2) {
                    int day1 = now.get(Calendar.DAY_OF_MONTH);
                    int day2 = dob.get(Calendar.DAY_OF_MONTH);
                    if (day1 >= day2) {
                        chkAge = false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return age;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
