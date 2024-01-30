package water.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bestone.water.databinding.ActivityOtpVerificationBinding;
import com.bestone.water.databinding.ActivityWelcomeBinding;
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
import water.utils.BWaterPreferences;
import water.view.activity.Mainactivity;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.HttpException;

public class OtpViewModel extends ViewModel {
    private static final String TAG = "OtpViewModel";
    public MutableLiveData<String> toast = new MutableLiveData<>();
    private Context context;
    public ActivityOtpVerificationBinding binding;
    public ActivityWelcomeBinding welcomeBinding;
    private CompositeDisposable disposable = new CompositeDisposable();
    public MutableLiveData<Boolean> isloading = new MutableLiveData<>(false);
    public String mobile;
    public String otp;
    public String type;

    public OtpViewModel(Context context, ActivityOtpVerificationBinding binding) {
        this.context = context;
        this.binding = binding;
    }

    public void onOtpVerfication() {
        if (ConnectivityUtils.isConnected(context)) {



            if (validation()) {


                disposable.add(ApiInterfaceService.initRetrofit().verify(Constants.TOKEN,mobile,binding.pinView.getText().toString(),type)
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
                                    BWaterPreferences.setUserLoggedIn(context, true);
                                    Intent intent = new Intent(context, Mainactivity.class);
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
        if (!GlobalUtils.validatename(binding.pinView.getText().toString())) {
            binding.pinView.setError("Enter Otp");
            return false;
        } else if (binding.pinView.length() != 4) {
            binding.pinView.setError("Enter valid Otp");
            return false;
        }
        return true;
    }


}
