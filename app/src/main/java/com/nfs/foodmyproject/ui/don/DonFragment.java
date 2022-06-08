package com.nfs.foodmyproject.ui.don;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nfs.foodmyproject.R;
import com.nfs.foodmyproject.config.ApiEndpoint;
import com.nfs.foodmyproject.databinding.FragmentDonBinding;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

public class DonFragment extends Fragment {

    private FragmentDonBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DonViewModel donViewModel = new ViewModelProvider(this).get(DonViewModel.class);

        binding = FragmentDonBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView ammount = binding.ammountDonTextView;
        final int idProject = getArguments().getInt("idProject");
        ammount.setText(String.valueOf(getArguments().getDouble("ammount", 0.0)));
        View view = inflater.inflate(R.layout.fragment_don, container, false);
        //event listener on the button
        binding.sendDon.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   donate(idProject, ammount.getText().toString(), view);
                                               }
                                           }
        );
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void donate(int idProject, String ammount,View view) {
        RequestQueue rq = Volley.newRequestQueue(getContext());
        Map<String, String> elements = new HashMap<>();
        elements.put("project", idProject + "");
        elements.put("amount", ammount);

        JSONObject json = new JSONObject(elements);

        StringRequest post = new StringRequest(
                Request.Method.POST,
                ApiEndpoint.BASE + ApiEndpoint.ADD_DONATIONS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("API_EX", "onResponse: post request" + response);
                        Log.d("API_EX", "Bien envoyé ton don khoya");
                        Toast toast = Toast.makeText(getContext(), "Bien envoyé ton don khoya", Toast.LENGTH_LONG);
                        toast.setMargin(50,50);
                        toast.show();
                        getActivity().onBackPressed();
                        getActivity().onBackPressed();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("API_EX", "onErrorResponse post: " + error.toString());
                    }
                }
        ) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                return json.toString().getBytes(StandardCharsets.UTF_8);
            }
        };
        rq.add(post);
    }
}
