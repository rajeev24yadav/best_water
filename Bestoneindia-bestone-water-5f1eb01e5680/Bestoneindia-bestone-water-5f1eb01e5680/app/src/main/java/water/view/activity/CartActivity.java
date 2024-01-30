package water.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;

import com.bestone.water.R;
import com.bestone.water.databinding.ActivityCartBinding;
import com.google.gson.Gson;


import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.HttpException;
import water.adapter.AddCartAdapter;
import water.model.ErrorResponse;
import water.retorfit.ApiInterfaceService;
import water.utils.ConnectivityUtils;
import water.utils.Constants;
import water.viewmodel.CartViewModel;
import water.viewmodelfactory.ViewModelFactory;

public class CartActivity extends AppCompatActivity {
    ActivityCartBinding binding;
    CartViewModel viewModel;
    private Context context;
    private Activity activity;
    private CompositeDisposable disposable = new CompositeDisposable();
    public MutableLiveData<Boolean> isloading = new MutableLiveData<>(false);
    public MutableLiveData<String> toast = new MutableLiveData<>();
    public AddCartAdapter addCartAdapter = new AddCartAdapter(context);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart);
        viewModel = ViewModelProviders.of(this, new ViewModelFactory(new CartViewModel(this,CartActivity.this)).createFor()).get(CartViewModel.class);
        intialize();

        viewModel.cart_ItemList();
    }

    private void intialize() {
        binding.setCartviewModel(viewModel);
        binding.processToPay.setOnClickListener(v -> {
            Intent intent = new Intent(binding.getRoot().getContext(), WalletActivity.class);
            binding.getRoot().getContext().startActivity(intent);
        });
    }

}
