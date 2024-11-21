package com.marlenepaper.meh.fr.aficiones;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.marlenepaper.meh.Aficiones;
import com.marlenepaper.meh.DetalleFragmentActivity;
import com.marlenepaper.meh.R;
import com.marlenepaper.meh.databinding.ActivityAficionesBinding;
import com.marlenepaper.meh.databinding.ActivitySobreMiBinding;

public class SobreMi extends AppCompatActivity {

    private ActivitySobreMiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Vincula con el layout correcto
        binding = ActivitySobreMiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Recuperar la posición del fragmento (si es necesario)
        int fragmentPosition = getIntent().getIntExtra("fragment_position", -1);
        // Aquí puedes usar fragmentPosition para personalizar la lógica
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
        // Obtén el ID del elemento seleccionado
        int id = item.getItemId();

        if (id == R.id.favStarButton) {
            // Lógica para guardar la posición del fragmento en SharedPreferences
            int currentFragmentPosition = getIntent().getIntExtra("fragment_position", -1);

            SharedPreferences preferences = getSharedPreferences("AficionesPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("favorite_fragment_position", currentFragmentPosition);
            editor.apply(); // Guardar la preferencia

            // Abrir la actividad DetalleFragmentActivity
            Intent intent = new Intent(SobreMi.this, DetalleFragmentActivity.class);
            intent.putExtra("fragment_position", currentFragmentPosition); // Pasar la posición
            startActivity(intent);

            return true;

        } else if (id == R.id.aboutMeButton) {
            // Abrir la actividad SobreMi
            Intent intent = new Intent(SobreMi.this, SobreMi.class);
            intent.putExtra("fragment_position", getIntent().getIntExtra("fragment_position", -1));
            startActivity(intent);
            return true;

        } else if (id == R.id.myWebpage) {
            // Abrir la página web en un navegador
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://marlenepaper.com/"));
            startActivity(intent);
            return true;

        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}