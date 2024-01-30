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
import com.bestone.water.databinding.LiquarTypesFragmentBinding;

import water.viewmodel.LiquarTypesViewModel;
import water.viewmodelfactory.ViewModelFactory;

public class LiquarTypesFragment extends Fragment {

    private LiquarTypesViewModel mViewModel;
    LiquarTypesFragmentBinding binding;

    public static LiquarTypesFragment newInstance() {
        return new LiquarTypesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.liquar_types_fragment, container, false);
        mViewModel = ViewModelProviders.of(getActivity(), new ViewModelFactory(new LiquarTypesViewModel()).createFor()).get(LiquarTypesViewModel.class);

        return binding.getRoot();
    }



}