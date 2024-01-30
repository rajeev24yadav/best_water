package water.view.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.bestone.water.R;
import com.bestone.water.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;

import water.view.fragment.GridFragment;
import water.view.fragment.HomeFragment;
import water.view.fragment.ProfileFragment;

import water.view.fragment.SearchFragment;
import water.viewmodel.MainActivityViewModel;
import water.viewmodelfactory.ViewModelFactory;

import static androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class Mainactivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MainActivityViewModel mainActivityViewModel;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private Toolbar mToolbar;
    String FRAGMENT_HOME = "home";
    String FRAGMENT_OTHER = "other";
    ActionBarDrawerToggle toggle;
    private String currentFragName;
    BottomNavigationView bottomNavigationView;
    Menu menu;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(Mainactivity.this, R.layout.activity_main);
        mainActivityViewModel = ViewModelProviders.of(this, new ViewModelFactory(new MainActivityViewModel(this, binding)).createFor()).get(MainActivityViewModel.class);
        intialize();
    }

    private void intialize() {
        setUpToolBar();
        binding.setMainActivityViewModel(mainActivityViewModel);
        bottomNavigationView = binding.include.navigation;
        binding.include.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = bottomNavigationView.getMenu();

    }


    private void setUpToolBar() {
        navigationView = binding.navView;
        mDrawerLayout = binding.drawerLayout;
        View headerView = navigationView.getHeaderView(0);
        mToolbar = binding.include.toolbar;
        MaterialShapeDrawable navViewBackground = (MaterialShapeDrawable) navigationView.getBackground();
        navViewBackground.setShapeAppearanceModel(
                navViewBackground.getShapeAppearanceModel()
                        .toBuilder()
                        .setBottomRightCorner(CornerFamily.ROUNDED, 100)
                        .setTopRightCorner(CornerFamily.ROUNDED, 100)
                        .build());
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_menu));
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            mDrawerLayout.addDrawerListener(toggle);
            toggle.syncState();

        }
        viewFragment(new HomeFragment(), FRAGMENT_HOME);
    }




    @Override
    protected void onResume() {
        super.onResume();
        if (mDrawerLayout != null) {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }

    private void lockDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }

    private void unlockDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void loadFragment(@NonNull Fragment fragment, boolean clearTillHome) {
        String fragmentName = fragment.getClass().getSimpleName();
        currentFragName = fragmentName;
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (isFragmentInBackStack(fragmentManager, fragment.getClass().getSimpleName())) {
            // Fragment exists, go back to that fragment
            fragmentManager.popBackStack(fragment.getClass().getSimpleName(), 0);
        } else {
            if (clearTillHome)
                fragmentManager.popBackStack(HomeFragment.class.getSimpleName(), 0);
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment, fragmentName)
                    .addToBackStack(fragmentName)
                    .commit();
        }
    }


    public static boolean isFragmentInBackStack(final FragmentManager fragmentManager, final String fragmentTagName) {
        for (int entry = 0; entry < fragmentManager.getBackStackEntryCount(); entry++) {
            if (fragmentTagName.equals(fragmentManager.getBackStackEntryAt(entry).getName())) {
                return true;
            }
        }
        return false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.action_one:
                    menu.findItem(R.id.action_two).setIcon(R.drawable.ic_category_icon);
                    menu.findItem(R.id.action_three).setIcon(R.drawable.ic_search_unfilled);
                    menu.findItem(R.id.action_four).setIcon(R.drawable.ic_profile_icon);
                    item.setIcon(R.drawable.ic_home);
                    Fragment homeFrag = new HomeFragment();
                    viewFragment(homeFrag, FRAGMENT_HOME);
                    break;
                case R.id.action_two:
                    menu.findItem(R.id.action_one).setIcon(R.drawable.ic_home_unfilled);
                    menu.findItem(R.id.action_three).setIcon(R.drawable.ic_search_unfilled);
                    menu.findItem(R.id.action_four).setIcon(R.drawable.ic_profile_icon);
                    item.setIcon(R.drawable.ic_category_filled);
                    Fragment gridFrag = new GridFragment();
                    viewFragment(gridFrag, FRAGMENT_OTHER);
                    break;
                case R.id.action_three:
                    menu.findItem(R.id.action_one).setIcon(R.drawable.ic_home_unfilled);
                    menu.findItem(R.id.action_two).setIcon(R.drawable.ic_category_icon);
                    menu.findItem(R.id.action_four).setIcon(R.drawable.ic_profile_icon);
                    item.setIcon(R.drawable.ic_search_filled);
                    Fragment searchFrag = new SearchFragment();
                    viewFragment(searchFrag, FRAGMENT_OTHER);
                    break;
                case R.id.action_four:
                    menu.findItem(R.id.action_one).setIcon(R.drawable.ic_home_unfilled);
                    menu.findItem(R.id.action_two).setIcon(R.drawable.ic_category_icon);
                    menu.findItem(R.id.action_three).setIcon(R.drawable.ic_search_unfilled);
                    item.setIcon(R.drawable.ic_profile_filled);
                    Fragment profileFrag = new ProfileFragment();
                    viewFragment(profileFrag, FRAGMENT_OTHER);
//                    Intent myaccount = new Intent(Mainactivity.this, MyAccountActivity.class);
//                    startActivity(myaccount);
            }
            return false;
        }

    };

    private void viewFragment(Fragment fragment, String name) {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        final int count = fragmentManager.getBackStackEntryCount();
        if (name.equals(FRAGMENT_OTHER)) {
            fragmentTransaction.addToBackStack(name);
        }
        fragmentTransaction.commit();
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (fragmentManager.getBackStackEntryCount() <= count) {
                    menu.findItem(R.id.action_two).setIcon(R.drawable.ic_category_icon);
                    menu.findItem(R.id.action_three).setIcon(R.drawable.ic_search_unfilled);
                    menu.findItem(R.id.action_four).setIcon(R.drawable.ic_profile_icon);
                    menu.findItem(R.id.action_one).setIcon(R.drawable.ic_home);
                    Log.e("Fragment", "home");
                    fragmentManager.popBackStack(FRAGMENT_OTHER, POP_BACK_STACK_INCLUSIVE);
                    fragmentManager.removeOnBackStackChangedListener(this);
                    bottomNavigationView.getMenu().getItem(0).setChecked(true);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app_bar, menu);
        MenuItem menuItem = menu.findItem(R.id.action_cart);
        if (menuItem != null) {
            menuItem.setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_cart) {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        int backStackSize = getSupportFragmentManager().getBackStackEntryCount();
        if (backStackSize == 1) {
           confirmExit();
        }else if(backStackSize==0){
            confirmExit();

        }
     //   super.onBackPressed();
    }

    public void confirmExit() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_exit_app);
        dialog.setCancelable(false);
        Button yesButton = dialog.findViewById(R.id.btn_yes);
        Button noButton = dialog.findViewById(R.id.btn_no);
        yesButton.setOnClickListener(v -> {
           finishAffinity();
            dialog.dismiss();
        });
        noButton.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }



}
