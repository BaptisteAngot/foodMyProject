package com.nfs.foodmyproject.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.nfs.foodmyproject.R;
import com.nfs.foodmyproject.beans.Palier;
import com.nfs.foodmyproject.beans.adapter.PalierListAdapter;
import com.nfs.foodmyproject.config.ApiEndpoint;
import com.nfs.foodmyproject.databinding.FragmentDashboardBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    private PalierListAdapter pla;
    private ArrayList<Palier> palierList = new ArrayList<>();
    private ListView listView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        final TextView description = binding.descriptionTextView;
        //logd argument titre
        Log.d("titre", "onCreateView: " + getArguments().getString("titre"));
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), s -> textView.setText(getArguments().getString("titre")));
        dashboardViewModel.getDescription().observe(getViewLifecycleOwner(), s -> description.setText(getArguments().getString("titre")));
        getPalierByApi();

        int percentage = getArguments().getInt("percentage",0);
        binding.progressBar2.setProgress(percentage);
        binding.percentageTextView2.setText(percentage + " %");
        binding.descriptionTextView.setText(getArguments().getString("description",""));
        binding.nbContributeurs.setText(getArguments().getInt("nbDonator",0) + " contributeur(s)");
        Picasso.get().load(getArguments().getString("image")).into(binding.imagetest);
        binding.argentCollect.setText(getArguments().getDouble("collect",0) + " € collecté");

        listView = binding.listView;
        dashboardViewModel.getListView().observe(getViewLifecycleOwner(),listView::addView);
        pla = new PalierListAdapter(getContext(), palierList);
        listView.setAdapter(pla);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Palier palier = (Palier) listView.getItemAtPosition(position);
            Bundle bundle = new Bundle();
            bundle.putDouble("ammount", palier.getPrice());
            bundle.putInt("idProject", getArguments().getInt("idProject",0));
            Navigation.findNavController(view).navigate(R.id.navigation_don,bundle);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void refreshList() {
        pla = new PalierListAdapter(getContext(), palierList);
        listView.setAdapter(pla);
    }

    private void getPalierByApi() {
        RequestQueue rq = Volley.newRequestQueue(getContext());
        Log.d("API_EX", "idProject: " +  getArguments().getInt("id",0));
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                ApiEndpoint.BASE + ApiEndpoint.GET_PALIERS + getArguments().getInt("id",0),
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length() > 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject obj = response.getJSONObject(i);
                                    palierList.add(new Palier(obj.getString("name"), obj.getString("description"), obj.getDouble("price") ));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            refreshList();
                        } else {
                            Log.d("API_EX", "empty ressources");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("API_EX", "onErrorResponse: " + error.toString());
                    }
                }
        );
        rq.add(request);
    }
}