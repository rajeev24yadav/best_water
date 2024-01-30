package water.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.bestone.water.R;
import com.bestone.water.databinding.HomeFragBinding;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import water.adapter.BannerAdapter;

import water.adapter.ImageAdapter;
import water.base.BaseFragment;
import water.model.AllBanner;
import water.model.Banner;
import water.viewmodel.HomeViewModel;
import water.viewmodelfactory.ViewModelFactory;

public class HomeFragment extends BaseFragment {
    private static final String TAG = "HomeFragment";
    HomeViewModel homeViewModel;
    HomeFragBinding binding;
    public ImageAdapter bannerAdapter;


    public ArrayList<String> ImagesArray = new ArrayList<String>();



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_frag, container, false);

        homeViewModel = ViewModelProviders.of(getActivity(), new ViewModelFactory(new HomeViewModel(getActivity(), getActivity(), binding)).createFor()).get(HomeViewModel.class);
        intialize();
        homeViewModel.getCategoryList();
        homeViewModel.getBannersList();
        return binding.getRoot();
    }

    private void intialize() {


        bannerAdapter = new ImageAdapter(getActivity(), ImagesArray);

        binding.viewPage.setAdapter(bannerAdapter);
        binding.viewPage.setCurrentItem(0);
        binding.indicator.setViewPager(binding.viewPage);
        float density = getResources().getDisplayMetrics().density;
        binding.indicator.setRadius(3 * density);
        // Auto start of viewpager

        // Pager listener over indicator
        binding.indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {
              //  currentPage = pos;

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });


        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        binding.setHomemodel(homeViewModel);



    }



    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.


    final Handler handler = new Handler();
    final Runnable Update = new Runnable() {
        public void run() {
            if (currentPage == homeViewModel.numberOfPager) {
                currentPage = 0;
            }
            binding.viewPage.setCurrentItem(currentPage++, true);
        }
    };





    @Override
    public void onPause() {
       // bannerChangeHandler.removeCallbacks(bannerChangeRunnable);
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        //homeViewModel.getCheckLocation();
    }
}
