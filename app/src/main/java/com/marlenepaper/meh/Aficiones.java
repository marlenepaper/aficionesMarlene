package com.marlenepaper.meh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.view.MenuItem;
import com.marlenepaper.meh.databinding.ActivityAficionesBinding;
import com.marlenepaper.meh.fr.aficiones.SobreMi;
import com.marlenepaper.meh.ui.fr.manager.Paginador;

import android.view.MenuInflater;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;


public class Aficiones extends AppCompatActivity {

    private ActivityAficionesBinding binding;
    public static final String FAVORITES_KEY = "favorites"; // Clave para los favoritos en SharedPreferences

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAficionesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Paginador paginador = new Paginador(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(paginador);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("");


        binding.favHeartButton.setOnClickListener(view -> {
            int currentFragmentPosition = binding.viewPager.getCurrentItem();

            SharedPreferences preferences = getSharedPreferences("AficionesPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            Set<String> favorites = preferences.getStringSet(FAVORITES_KEY, new HashSet<>());

            if (favorites.contains(String.valueOf(currentFragmentPosition))) {

                favorites.remove(String.valueOf(currentFragmentPosition));
                Toast.makeText(this, "Eliminado de favoritos", Toast.LENGTH_SHORT).show();

                binding.favHeartButton.setImageResource(R.drawable.baseline_favorite_border_24);
            } else {

                favorites.add(String.valueOf(currentFragmentPosition));
                Toast.makeText(this, "¡Agregado a favoritos!", Toast.LENGTH_SHORT).show();

                binding.favHeartButton.setImageResource(R.drawable.baseline_favorite_24);
            }

            editor.putStringSet(FAVORITES_KEY, favorites);
            editor.apply(); // Aplicar cambios
        });

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
            Intent intent = new Intent(Aficiones.this, DetalleFragmentActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.homeButton) {
            return false;
        } else if (id == R.id.aboutMeButton) {
            Intent intent = new Intent(Aficiones.this, SobreMi.class);
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
