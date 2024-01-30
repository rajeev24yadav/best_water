package water.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.HttpException;
import water.adapter.AddCartAdapter;
import water.model.ErrorResponse;
import water.model.MyAccountResponse;
import water.retorfit.ApiInterfaceService;
import water.utils.ConnectivityUtils;
import water.utils.Constants;

public class CartViewModel extends ViewModel {
    private Context context;
    private Activity activity;
    private CompositeDisposable disposable = new CompositeDisposable();
    public MutableLiveData<Boolean> isloading = new MutableLiveData<>(false);
    public MutableLiveData<String> toast = new MutableLiveData<>();
    public AddCartAdapter addCartAdapter = new AddCartAdapter(context);

    public CartViewModel(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void onBackPressed() {
        activity.onBackPressed();
    }
    public void cart_ItemList(){
        if (ConnectivityUtils.isConnected(context)) {
            disposable.add(ApiInterfaceService.initRetrofit().cart_item_list(Constants.TOKEN, "1")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
                    .doOnSubscribe(disposable1 -> isloading.setValue(true))
                    .doOnTerminate(() -> isloading.setValue(false))
                    .subscribe((CartItem, throwable) -> {
                        if (CartItem != null) {

                            Log.e("ITEM ", "=========");

                        } else {

                            Log.e("NO ITEM", "=========");
                        }
                    }));
        }

    }

}
