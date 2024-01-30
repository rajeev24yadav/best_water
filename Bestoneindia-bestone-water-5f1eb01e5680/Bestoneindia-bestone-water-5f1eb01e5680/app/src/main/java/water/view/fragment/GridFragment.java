package water.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bestone.water.R;
import com.bestone.water.databinding.GridFragBinding;

import water.viewmodel.GridViewModel;
import water.viewmodelfactory.ViewModelFactory;

public class GridFragment extends Fragment {

    private GridViewModel gridViewModel;
    GridFragBinding gridFragBinding;
    public static final String BUNDLE_KEY="bundle";


    public static GridFragment newInstance() {
        return new GridFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        gridFragBinding = DataBindingUtil.inflate(inflater, R.layout.grid_frag, container, false);
        gridViewModel = ViewModelProviders.of(getActivity(), new ViewModelFactory(new GridViewModel(getActivity())).createFor()).get(GridViewModel.class);
        intialize();
        return gridFragBinding.getRoot();
    }

    private void intialize() {
        gridFragBinding.setGridviewModel(gridViewModel);
    }
}
