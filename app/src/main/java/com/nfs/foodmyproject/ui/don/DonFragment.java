package com.nfs.foodmyproject.ui.don;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.nfs.foodmyproject.databinding.FragmentDonBinding;

public class DonFragment extends Fragment {

    private FragmentDonBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DonViewModel donViewModel = new ViewModelProvider(this).get(DonViewModel.class);

        binding = FragmentDonBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView ammount = binding.ammountDonTextView;
        ammount.setText(String.valueOf(getArguments().getDouble("ammount",0.0)));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
