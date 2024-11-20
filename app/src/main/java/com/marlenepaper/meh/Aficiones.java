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
import com.marlenepaper.meh.ui.fr.manager.Paginador;

import android.view.MenuInflater;
import android.widget.Toast;


public class Aficiones extends AppCompatActivity {

    private ActivityAficionesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAficionesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Paginador paginador= new Paginador(this, getSupportFragmentManager());
        ViewPager viewPager= binding.viewPager;
        viewPager.setAdapter(paginador);

        setSupportActionBar(binding.toolbar);

        // Configura el OnClickListener para el FloatingActionButton
        binding.favHeartButton.setOnClickListener(view -> {
            try {
                // Asegúrate de que el tag esté bien configurado antes de usarlo
                Object tag = binding.favHeartButton.getTag();
                int currentIcon = tag != null && tag instanceof Integer ? (Integer) tag : R.drawable.baseline_favorite_border_24;

                if (currentIcon == R.drawable.baseline_favorite_border_24) {
                    // Si el ícono es el corazón vacío, cambiamos a corazón lleno
                    binding.favHeartButton.setImageResource(R.drawable.baseline_favorite_24);
                    binding.favHeartButton.setTag(R.drawable.baseline_favorite_24);  // Actualizamos el tag con el nuevo ícono
                } else {
                    // Si el ícono es el corazón lleno, cambiamos a corazón vacío
                    binding.favHeartButton.setImageResource(R.drawable.baseline_favorite_border_24);
                    binding.favHeartButton.setTag(R.drawable.baseline_favorite_border_24);  // Actualizamos el tag con el nuevo ícono
                }

                // Mostrar mensaje (opcional)
                Toast.makeText(this, "¡Agregado a tus favoritos!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();  // Imprime el error en el log para depuración
                Toast.makeText(this, "Ha ocurrido un error.", Toast.LENGTH_SHORT).show();
            }
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
            int currentFragmentPosition = binding.viewPager.getCurrentItem();

            // Guardar la posición del fragmento en SharedPreferences
            SharedPreferences preferences = getSharedPreferences("AficionesPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("favorite_fragment_position", currentFragmentPosition);
            editor.apply(); // Guardar la preferencia

            Intent intent = new Intent(Aficiones.this, DetalleFragmentActivity.class);
            intent.putExtra("fragment_position", currentFragmentPosition); // Pasar la posición
            startActivity(intent);

            return true;
        } else if (id == R.id.aboutMeButton) {
            int currentFragmentPosition = binding.viewPager.getCurrentItem();
            Intent intent = new Intent(Aficiones.this, SobreMi.class);
            intent.putExtra("fragment_position", currentFragmentPosition); // Pasar la posición
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
