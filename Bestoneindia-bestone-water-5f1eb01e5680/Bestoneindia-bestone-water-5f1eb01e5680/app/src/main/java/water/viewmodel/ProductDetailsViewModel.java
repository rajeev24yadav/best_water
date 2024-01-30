package water.viewmodel;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bestone.water.R;
import com.bestone.water.databinding.HomeFragBinding;
import com.bestone.water.databinding.ProductDetailsBottomSheetBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.HttpException;
import water.adapter.ChakhnaAdapter;
import water.adapter.CouponAdapter;
import water.adapter.NearByAdapter;
import water.adapter.PickupcategoriesAdapter;
import water.adapter.PopularBrandAdapter;
import water.adapter.TopItemAdapter;
import water.model.ErrorResponse;
import water.retorfit.ApiInterfaceService;
import water.utils.ConnectivityUtils;
import water.utils.Constants;
import water.utils.GlobalUtils;
import water.view.activity.TopItemForYoyActivity;

public class ProductDetailsViewModel extends ViewModel {

    private static final String TAG = "HomeViewModel";

    public Context context;



    public ProductDetailsBottomSheetBinding binding;

    public MutableLiveData<Boolean> isloading = new MutableLiveData<>(false);
    public MutableLiveData<String> toast = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();


    public ProductDetailsViewModel(Context context, ProductDetailsBottomSheetBinding binding) {
        this.context = context;
        this.binding = binding;

    }



    @SuppressLint("SetTextI18n")
    public void getProductDetails() {
        if (ConnectivityUtils.isConnected(context)) {
            disposable.add(ApiInterfaceService.initRetrofit().product(Constants.TOKEN,HomeViewModel.product_id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
                    .doOnSubscribe(disposable1 -> isloading.setValue(true))
                    .doOnTerminate(() -> isloading.setValue(false))
                    .subscribe((response, throwable) -> {

                        if (response != null && response.getList() != null) {
                            Log.e(TAG, "status: "+response.isStatus());
                            if (response.isStatus())
                            {
                                if (response.getList()!=null)
                                {
                                    if (response.getList().size()>0)
                                    {
                                        /*Glide.with(context)
                                                .load(response.getList().get(0).getImage_url())
                                                .apply(new RequestOptions().placeholder(R.drawable.ic_cart_icon).error(R.drawable.ic_cart_icon))
                                                .into(binding.productImage);*/

                                        binding.productName.setText(""+response.getList().get(0).getProduct_name());
                                        binding.productPrice.setText(""+response.getList().get(0).getPrice());

                                        Log.e(TAG, "getProduct_name: "+response.getList().get(0).getProduct_name());
                                        Log.e(TAG, "getPrice: "+response.getList().get(0).getPrice());
                                        Log.e(TAG, "getImage_url: "+response.getList().get(0).getImage_url());


                                    }
                                }

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
