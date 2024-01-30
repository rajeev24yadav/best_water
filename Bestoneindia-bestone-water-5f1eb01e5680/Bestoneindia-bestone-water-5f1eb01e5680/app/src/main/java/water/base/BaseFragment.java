package water.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import timber.log.Timber;

public class BaseFragment  extends Fragment {
    private boolean isAlive;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        isAlive = true;
        Timber.d("Fragment name is: ${BaseFragment.class.getSimpleName()}");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        isAlive = false;
        super.onDestroy();
    }

    public boolean isAlive() {
        return isAlive;
    }



    public static boolean isFragmentInBackStack(final FragmentManager fragmentManager, final String fragmentTagName) {
        for (int entry = 0; entry < fragmentManager.getBackStackEntryCount(); entry++) {
            if (fragmentTagName.equals(fragmentManager.getBackStackEntryAt(entry).getName())) {
                return true;
            }
        }
        return false;
    }
}
