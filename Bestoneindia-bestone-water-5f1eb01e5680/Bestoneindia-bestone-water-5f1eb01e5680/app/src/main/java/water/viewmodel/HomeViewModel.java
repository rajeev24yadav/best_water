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

import com.bestone.water.databinding.HomeFragBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import water.adapter.ChakhnaAdapter;
import water.adapter.CouponAdapter;
import water.adapter.ImageAdapter;
import water.adapter.NearByAdapter;
import water.adapter.PickupcategoriesAdapter;
import water.adapter.PopularBrandAdapter;
import water.adapter.TopItemAdapter;
import water.model.ErrorResponse;
import water.retorfit.ApiInterfaceService;
import water.utils.ConnectivityUtils;
import water.utils.Constants;
import water.utils.GlobalUtils;
import water.utils.BWaterPreferences;
import water.view.activity.TopItemForYoyActivity;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.HttpException;
import water.view.fragment.HomeFragment;
import water.view.fragment.ProductDetailsBottomSheet;

public class HomeViewModel extends ViewModel {

    private static final String TAG = "HomeViewModel";

    public Context context;
    public FragmentActivity activity;

    public PopularBrandAdapter popularBrandAdapter ;

    public static String product_id;
    public int numberOfPager= 0;

    public FusedLocationProviderClient fusedLocationClient;
    public String getLat, getlongs;
    int PERMISSION_ALL = 1;
    public HomeFragBinding binding;

    public MutableLiveData<Boolean> isloading = new MutableLiveData<>(false);
    public MutableLiveData<String> toast = new MutableLiveData<>();
    private final CompositeDisposable disposable = new CompositeDisposable();


    public HomeViewModel(Context context, FragmentActivity activity, HomeFragBinding binding) {
        this.context = context;
        this.activity = activity;
        this.binding = binding;
        this.fusedLocationClient = new FusedLocationProviderClient(activity);
        this.popularBrandAdapter = new PopularBrandAdapter(context);

        popularBrandAdapter.setOnItemClickListener(product_id -> {
            HomeViewModel.product_id = product_id;
            ProductDetailsBottomSheet productDetailsBottomSheet = new ProductDetailsBottomSheet();
            productDetailsBottomSheet.show(activity.getSupportFragmentManager(), productDetailsBottomSheet.getTag());
        });

    }


    public void getCheckLocation() {
        if (checkLocation()) {
            if (!GlobalUtils.GpsEnable(activity)) {
                GlobalUtils.showGPSDisabledAlertToUser(activity);
            } else {
                lastLocation();
            }
        } else GlobalUtils.showSettingsDialog(activity);
    }

    private boolean checkLocation() {
        int courcelocation = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);
        int fineLocation = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (courcelocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }

        if (fineLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), PERMISSION_ALL);
            return false;
        }
        return true;
    }

    @SuppressLint("MissingPermission")
    private void lastLocation() {
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(activity, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            try {
                                getAddress(location.getLatitude(), location.getLongitude());

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    public void getAddress(double lat, Double longs) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(context, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(lat, longs, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            getLat = String.valueOf(lat);
            getlongs = String.valueOf(longs);
            binding.tvLocation.setText(address);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getCategoryList() {
        if (ConnectivityUtils.isConnected(context)) {
            disposable.add(ApiInterfaceService.initRetrofit().category(Constants.TOKEN,"4545")
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
                                popularBrandAdapter.update(response.getList());
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


    public void getBannersList() {
        if (ConnectivityUtils.isConnected(context)) {
            disposable.add(ApiInterfaceService.initRetrofit().banner(Constants.TOKEN,"4545")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
                    .doOnSubscribe(disposable1 -> isloading.setValue(true))
                    .doOnTerminate(() -> isloading.setValue(false))
                    .subscribe((response, throwable) -> {
                        if (response != null && response.getList() != null) {
                            if (response.isStatus())
                            {
                                HomeFragment homeFragment = new HomeFragment();
                                if (homeFragment.ImagesArray!=null)
                                {
                                    homeFragment.ImagesArray.clear();
                                }
                                for (int i = 0; i <response.getList().size() ; i++) {
                                    homeFragment.ImagesArray.add(response.getList().get(i).getImage_url());
                                }
                                numberOfPager = homeFragment.ImagesArray.size();
                                homeFragment.bannerAdapter = new ImageAdapter(context, homeFragment.ImagesArray);
                                binding.viewPage.setAdapter(homeFragment.bannerAdapter);
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


    public void viewAll(){
        Intent intent = new Intent(binding.getRoot().getContext(), TopItemForYoyActivity.class);
        binding.getRoot().getContext().startActivity(intent);
    }


}
