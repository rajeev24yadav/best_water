package water.viewmodel;

import android.app.Activity;
import android.content.Intent;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;

import com.bestone.water.R;


import water.view.activity.BulkOrders;
import water.view.activity.FavouriteActivity;
import water.view.activity.HelpActivity;
import water.view.activity.MyAccountActivity;
import water.view.activity.MyordersActivity;
import water.view.activity.ShopNearByActivity;
import water.view.activity.SchedulerActivity;
import water.view.activity.WalletActivity;
import water.view.activity.OfferActivity;

public class MainActivityViewModel extends ViewModel {

    private FragmentActivity context;

    com.bestone.water.databinding.ActivityMainBinding binding;



    public MainActivityViewModel(Activity context, com.bestone.water.databinding.ActivityMainBinding binding) {
        this.context = (FragmentActivity) context;
        this.binding = binding;
    }


    public void onNavigationSelected(int selected) {
        switch (selected) {
            case 1:
                Intent myAccount = new Intent(context, MyAccountActivity.class);
                context.startActivity(myAccount);
                binding.drawerLayout.closeDrawers();
                break;
            case 2:
                Intent myorders = new Intent(context, MyordersActivity.class);
                context.startActivity(myorders);
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case 3:
                Intent favorite = new Intent(context, FavouriteActivity.class);
                context.startActivity(favorite);
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case 4:
                Intent offer = new Intent(context, OfferActivity.class);
                context.startActivity(offer);
                binding.drawerLayout.closeDrawers();
                break;
            case 5:
                Intent nearMe = new Intent(context, ShopNearByActivity.class);
                context.startActivity(nearMe);
                binding.drawerLayout.closeDrawers();
                break;
            case 6:
                Intent bulkorders = new Intent(context, BulkOrders.class);
                context.startActivity(bulkorders);
                binding.drawerLayout.closeDrawers();
                break;
            case 7:
                Intent help = new Intent(context, HelpActivity.class);
                context.startActivity(help);
                binding.drawerLayout.closeDrawers();
                break;
            case 8:
                Intent wallet = new Intent(context, WalletActivity.class);
                context.startActivity(wallet);
                binding.drawerLayout.closeDrawers();
                break;
            case 9:
                Intent secudler = new Intent(context, SchedulerActivity.class);
                context.startActivity(secudler);
                binding.drawerLayout.closeDrawers();
                break;
            case 10:
                logOut();
                break;
        }
    }

    private void logOut() {
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
