package com.nfs.foodmyproject.ui.home;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
import com.nfs.foodmyproject.DAO.DaoFactory;
import com.nfs.foodmyproject.R;
import com.nfs.foodmyproject.beans.Box;
import com.nfs.foodmyproject.beans.Contrepartie;
import com.nfs.foodmyproject.beans.Don;
import com.nfs.foodmyproject.beans.Projet;
import com.nfs.foodmyproject.beans.adapter.ProjetToBoxAdapter;
import com.nfs.foodmyproject.beans.adapter.BoxListAdapter;
import com.nfs.foodmyproject.config.ApiEndpoint;
import com.nfs.foodmyproject.databinding.FragmentHomeBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private BoxListAdapter bla;
    ArrayList<Box> boxList = new ArrayList<Box>();
    List<Projet> projets = new ArrayList<Projet>();
    ProjetToBoxAdapter projetToBoxAdapter = new ProjetToBoxAdapter();
    private ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        projets = DaoFactory.getProjetDao(getContext()).getAll();

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        listView = binding.listView;
        homeViewModel.getListView().observe(getViewLifecycleOwner(), listView::addView);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            //get Item at position
            Box box = (Box) listView.getItemAtPosition(position);
            Bundle bundle = new Bundle();
            bundle.putInt("id", box.getId());
            bundle.putString("titre", box.getTitle());
            bundle.putString("description", box.getDescription());
            bundle.putString("image", box.getImage());
            bundle.putInt("percentage", box.getPercentage());
            bundle.putDouble("collect", box.getPriceCollect());
            bundle.putInt("nbDonator", box.getNbContributeur());
            Navigation.findNavController(view).navigate(R.id.navigation_dashboard, bundle);
        });
        boxList = (ArrayList<Box>) projetToBoxAdapter.ConvertProjetToBox(projets);
        refreshList();
        getApiBox();
        bla = new BoxListAdapter(getContext(), boxList);
        listView.setAdapter(bla);
        return root;
    }

    private void refreshList(){
        bla = new BoxListAdapter(getContext(), boxList);
        listView.setAdapter(bla);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getApiBox() {
        RequestQueue rq = Volley.newRequestQueue(getContext());
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                ApiEndpoint.BASE+ApiEndpoint.GET_PROJECTS,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length() > 0) {
                            ArrayList<Box> boxArrayList = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject obj = response.getJSONObject(i);
                                    Log.d("API EX", obj.get("pledge").toString());
                                    double percentage = Double.parseDouble(obj.get("pledge").toString()) / Double.parseDouble(obj.get("goal").toString()) * 100;
                                    LocalDate date = LocalDate.parse(obj.get("limit_date").toString().split("T")[0], DateTimeFormatter.ISO_DATE);
                                    String title = obj.get("title").toString();
                                    String description = obj.get("description").toString();
                                    ArrayList<String> images = new ArrayList<>();
                                    for (int y = 0; y < obj.getJSONArray("media").length(); y++) {
                                        images.add(obj.getJSONArray("media").getJSONObject(y).get("source").toString());
                                    }
                                    Integer id = obj.getInt("id");
                                    Integer contributors = obj.getInt("contributors");
                                    if (i > 3 && projets.size() == 0) {
                                        Projet projet = new Projet(
                                                id,
                                                title,
                                                description,
                                                Float.parseFloat(obj.get("goal").toString()) ,
                                                Float.parseFloat(obj.get("pledge").toString()),
                                                images.get(0),
                                                images,
                                                null,
                                                contributors,
                                                null,
                                                date.toString());
                                        DaoFactory.getProjetDao(getContext()).addProjet(projet);
                                    }
                                    boxArrayList.add(new Box(id,title,description,images.get(0), (int) Math.round(percentage), date, Double.parseDouble(obj.get("pledge").toString()),contributors));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            boxList = boxArrayList;
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


    private boolean internetCheck(){
        ConnectivityManager cm = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        return nInfo != null && nInfo.isAvailable() && nInfo.isConnected();

    }
}