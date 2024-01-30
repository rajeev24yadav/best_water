package water.view.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bestone.water.R;
import com.bestone.water.databinding.MyAccountActivityBinding;

import water.utils.DatePicker.date.DatePicker;
import water.viewmodel.MyAccountModel;
import water.viewmodelfactory.ViewModelFactory;

public class MyAccountActivity extends AppCompatActivity {
    MyAccountActivityBinding binding;
    MyAccountModel viewModel;
    DatePicker datePicker;
    String selected_date;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MyAccountActivity.this, R.layout.my_account_activity);
        viewModel = ViewModelProviders.of(this, new ViewModelFactory(new MyAccountModel(this, binding)).createFor()).get(MyAccountModel.class);
        intialize();
        viewModel.fetchMyaccount();
    }

    private void intialize() {
        binding.setViewmodel(viewModel);
        binding.imgBack.setOnClickListener(v -> {
            onBackPressed();
        });
        binding.etDob.setOnClickListener(v -> {
            showDataPicker();
        });

    }
    private void showDataPicker() {
        final Dialog setDate = new Dialog(MyAccountActivity.this);
        setDate.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setDate.setContentView(R.layout.dialog_dateset);
        setDate.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setDate.show();
        datePicker = setDate.findViewById(R.id.datePicker);

        datePicker.setOnDateSelectedListener((year, month, day) -> {
            selected_date = datePicker.getDate();
            int age = viewModel.checkAgeValidatin(selected_date);

            // if (18 < age) {
            binding.etDob.setText(getCurrentDate());
            viewModel.date_of_birth = String.valueOf(age);
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
}
