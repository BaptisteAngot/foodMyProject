package com.nfs.foodmyproject.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nfs.foodmyproject.R;
import com.nfs.foodmyproject.databinding.FragmentLoginBinding;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    private Button button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        LoginViewModel loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        View root = binding.getRoot();
        button = binding.button2;
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_homeFragment);
            }
        });
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
}
