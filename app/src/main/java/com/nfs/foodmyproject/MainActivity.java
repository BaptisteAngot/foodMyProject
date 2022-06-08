package com.nfs.foodmyproject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.nfs.foodmyproject.DAO.DaoFactory;
import com.nfs.foodmyproject.beans.Projet;
import com.nfs.foodmyproject.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Projet> projets;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        projets = new ArrayList<Projet>();
        initDb();

        if(internetCheck()){
            //do something
        }


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    private void initDb(){
        projets = DaoFactory.getProjetDao(this).getAll();
        if(projets == null || projets.size() == 0){
            DaoFactory.getProjetDao(this).addProjet(new Projet(0, "poutre", "je poutre vos garonnes", (float) 15000 , (float) 1780, "21/08/2023"));
            DaoFactory.getProjetDao(this).addProjet(new Projet(1, "papillotte", "dingo sur le pédalo", (float) 3000 , (float) 2130, "12/06/2025"));
            DaoFactory.getProjetDao(this).addProjet(new Projet(2, "donne l'argent", "je viens chez toi sinon", (float) 10000 , (float) 6735, "04/04/2024"));
            projets = DaoFactory.getProjetDao(this).getAll();
        }
    }

    private boolean internetCheck(){

        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        return nInfo != null && nInfo.isAvailable() && nInfo.isConnected();

    }

}