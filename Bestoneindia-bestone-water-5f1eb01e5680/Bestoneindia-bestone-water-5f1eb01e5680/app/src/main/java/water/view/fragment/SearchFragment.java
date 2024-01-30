package water.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bestone.water.R;
import com.bestone.water.databinding.SearchFragBinding;

import water.base.BaseFragment;
import water.viewmodel.SearchViewModel;
import water.viewmodelfactory.ViewModelFactory;

public class SearchFragment extends BaseFragment {
    private SearchViewModel searchViewModel;
    private SearchFragBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_frag, container, false);
        searchViewModel = ViewModelProviders.of(getActivity(), new ViewModelFactory(new SearchViewModel(getActivity(),getActivity())).createFor()).get(SearchViewModel.class);
        intialize();
        return binding.getRoot();
    }

    private void intialize() {
        binding.setSearchviewmoodel(searchViewModel);
    }

}
