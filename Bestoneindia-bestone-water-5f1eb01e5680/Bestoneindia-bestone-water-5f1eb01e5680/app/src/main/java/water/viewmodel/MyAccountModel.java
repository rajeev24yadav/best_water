package water.viewmodel;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.bestone.water.R;
import com.bestone.water.databinding.MyAccountActivityBinding;
import com.google.gson.Gson;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.HttpException;
import water.model.ErrorResponse;
import water.model.MyAccountResponse;
import water.retorfit.ApiInterfaceService;
import water.utils.ConnectivityUtils;
import water.utils.Constants;
import water.utils.GlobalUtils;

public class MyAccountModel extends ViewModel {
    private CompositeDisposable disposable = new CompositeDisposable();
    public ObservableBoolean isUsernameLoading = new ObservableBoolean(false);
    public MutableLiveData<Boolean> isloading = new MutableLiveData<>(false);
    public MutableLiveData<String> toast = new MutableLiveData<>();
    private MyAccountActivityBinding binding;
    private Context context;
    public String name = "", mobile = "";/*, latitude = "",longitude=""*/;
    public String date_of_birth;
    public MyAccountResponse myaccountResponse;
    public MyAccountModel(Context context, MyAccountActivityBinding binding) {
        this.binding = binding;
        this.context = context;
    }

    public void fetchMyaccount() {
        disposable.add(ApiInterfaceService.initRetrofit().user_details(Constants.TOKEN, "1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .doOnSubscribe(disposable1 -> isUsernameLoading.set(true))
                .doOnTerminate(() -> isUsernameLoading.set(false))
                .subscribe((MyAccount, throwable) -> {
                    if (MyAccount != null && MyAccount.getData() != null) {
                        if (MyAccount.getStatus()==true){
                            this.myaccountResponse = MyAccount;
                            binding.etName.setText(MyAccount.getData().getName());
                            binding.etMobileno.setText("+91 "+MyAccount.getData().getPhoneNumber());
                            binding.etDob.setText(MyAccount.getData().getDateOfBirth());
                            binding.etEmail.setText(MyAccount.getData().getEmail());
                        }else {
                            Toast.makeText(context, MyAccount.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(context, "Please Check Network Connecton..", Toast.LENGTH_SHORT).show();

                    }
                }));
    }
    public void updateMyAccount(){
        if (validation()) {
            if (ConnectivityUtils.isConnected(context)) {

                disposable.add(ApiInterfaceService.initRetrofit().user_update(Constants.TOKEN, name, date_of_birth, "1")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .unsubscribeOn(Schedulers.io())
                        .doOnSubscribe(disposable1 -> isloading.setValue(true))
                        .doOnTerminate(() -> isloading.setValue(false))
                        .subscribe((UserUpdate, throwable) -> {

                            Log.e("11111", "");
                            if (UserUpdate != null) {
                                binding.etName.setText(UserUpdate.getData().getName());
                                binding.etMobileno.setText("+91 " + UserUpdate.getData().getPhoneNumber());
                                binding.etDob.setText(UserUpdate.getData().getDateOfBirth());
                                binding.etEmail.setText(UserUpdate.getData().getEmail());
                                toast.setValue(UserUpdate.getMessage());
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
        name =  binding.etName.getText().toString();
        date_of_birth = binding.etDob.getText().toString();
        if (!GlobalUtils.validatename(name)) {
            binding.etName.setError("enter name");
            return false;
        } else if (!GlobalUtils.validateDOB(String.valueOf(date_of_birth))) {
            binding.etDob.setError("enter valid age ");
            return false;
        }
        return true;
    }

    public void afterTextChanged(CharSequence charSequence, int type) {
        switch (type) {
            case 1:
                name = charSequence.toString();
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
