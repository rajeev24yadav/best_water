package water.view.fragment;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bestone.water.R;
import com.bestone.water.databinding.ProfileFragmentBinding;

import water.viewmodel.ProfileViewModel;
import water.viewmodelfactory.ViewModelFactory;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    ProfileFragmentBinding binding;
    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false);
        profileViewModel = ViewModelProviders.of(getActivity(), new ViewModelFactory(new ProfileViewModel()).createFor()).get(ProfileViewModel.class);
        intialize();
        return binding.getRoot();
    }

    private void intialize() {
       binding.setProfileviewmodel(profileViewModel);


    }

}