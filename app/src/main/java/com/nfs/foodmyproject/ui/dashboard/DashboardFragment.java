package com.nfs.foodmyproject.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.nfs.foodmyproject.R;
import com.nfs.foodmyproject.beans.Palier;
import com.nfs.foodmyproject.beans.adapter.PalierListAdapter;
import com.nfs.foodmyproject.databinding.FragmentDashboardBinding;
import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    private PalierListAdapter pla;
    private ArrayList<Palier> palierList;
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

        int percentage = getArguments().getInt("percentage",0);
        binding.progressBar2.setProgress(percentage);
        binding.percentageTextView2.setText(percentage + " %");
        binding.descriptionTextView.setText(getArguments().getString("description",""));
        binding.nbContributeurs.setText(getArguments().getInt("nbDonator",0) + " contributeur(s)");
        Picasso.get().load(getArguments().getString("image")).into(binding.imagetest);
        binding.argentCollect.setText(getArguments().getDouble("collect",0) + " € collecté");

        palierList = getPalier();
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

    public ArrayList<Palier> getPalier() {
        ArrayList<Palier> paliers = new ArrayList<>();
        paliers.add(new Palier("Palier 1","ouais", 5, LocalDate.now()));
        paliers.add(new Palier("Palier 2","ouais", 10, LocalDate.now()));
        paliers.add(new Palier("Palier 3","ouais", 7, LocalDate.now()));
        paliers.add(new Palier("Palier 3","ouais", 7, LocalDate.now()));
        paliers.add(new Palier("Palier 3","ouais", 7, LocalDate.now()));
        paliers.add(new Palier("Palier 3","ouais", 7, LocalDate.now()));
        return paliers;
    }
}