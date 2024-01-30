package water.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bestone.water.R;
import com.bestone.water.databinding.ProductDetailsBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import water.utils.CustomDialogBuilder;
import water.utils.GlobalUtils;
import water.viewmodel.HomeViewModel;
import water.viewmodel.ProductDetailsViewModel;
import water.viewmodelfactory.ViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductDetailsBottomSheet#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductDetailsBottomSheet extends BottomSheetDialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ProductDetailsBottomSheetBinding binding;
    ProductDetailsViewModel model;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductDetailsBottomSheet() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductDetailsBottomSheet.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductDetailsBottomSheet newInstance(String param1, String param2) {
        ProductDetailsBottomSheet fragment = new ProductDetailsBottomSheet();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.product_details_bottom_sheet, container, false);

        model = ViewModelProviders.of(getActivity(), new ViewModelFactory(new ProductDetailsViewModel(getActivity(),binding)).createFor()).get(ProductDetailsViewModel.class);
        initialize();
        initObserve();

        model.getProductDetails();
        return binding.getRoot();
    }


    private void initialize() {
        binding.setViewmodel(model);
    }

    private void initObserve() {
        CustomDialogBuilder customDialogBuilder = new CustomDialogBuilder(getActivity());
        model.isloading.observe(this, isLoading -> {
            if (isLoading) {
                customDialogBuilder.showLoadingDialog();
            } else {
                customDialogBuilder.hideLoadingDialog();
            }
        });
        model.toast.observe(this, s -> {
            if (s != null && !s.isEmpty()) {
                GlobalUtils.showToast(getActivity(), s);
            }
        });

    }
}