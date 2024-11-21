package com.marlenepaper.meh;

import static com.marlenepaper.meh.Aficiones.FAVORITES_KEY;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.marlenepaper.meh.databinding.ActivityDetalleFragmentBinding;
import com.marlenepaper.meh.databinding.ActivitySobreMiBinding;
import com.marlenepaper.meh.fr.aficiones.SobreMi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DetalleFragmentActivity extends AppCompatActivity {

    private ActivityDetalleFragmentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_fragment);

        binding = ActivityDetalleFragmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("");

        Map<String, String> itemNames = new HashMap<>();
        itemNames.put("0", "Sobrevivir");
        itemNames.put("1", "Olfatear");

        SharedPreferences preferences = getSharedPreferences("AficionesPrefs", MODE_PRIVATE);
        Set<String> favorites = preferences.getStringSet(FAVORITES_KEY, new HashSet<>());

        List<String> favoriteList = new ArrayList<>(favorites);

        for (int i = 0; i < favoriteList.size(); i++) {
            String item = favoriteList.get(i);
            if (itemNames.containsKey(item)) {
                favoriteList.set(i, itemNames.get(item));
            }
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FavoritesAdapter adapter = new FavoritesAdapter(favoriteList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla el archivo de menú (menu_desplegable.xml)
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_desplegable, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.favStarButton) {
            return false;
        } else if (id == R.id.homeButton) {
            Intent intent = new Intent(DetalleFragmentActivity.this, Aficiones.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.aboutMeButton) {
            Intent intent = new Intent(DetalleFragmentActivity.this, SobreMi.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.myWebpage) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://marlenepaper.com/"));
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
