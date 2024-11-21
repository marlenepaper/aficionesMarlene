package com.marlenepaper.meh;

import static com.marlenepaper.meh.Aficiones.FAVORITES_KEY;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DetalleFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_fragment); // Crea un layout para la lista

        SharedPreferences preferences = getSharedPreferences("AficionesPrefs", MODE_PRIVATE);
        Set<String> favorites = preferences.getStringSet(FAVORITES_KEY, new HashSet<>());

        // Convertir el set a una lista
        List<String> favoriteList = new ArrayList<>(favorites);

        // Mostrar la lista en un RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FavoritesAdapter adapter = new FavoritesAdapter(favoriteList);
        recyclerView.setAdapter(adapter);
    }
}
