package com.marlenepaper.meh;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetalleFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_fragment);

        // Obtener la posición del fragmento que fue pasada desde la actividad anterior
        int fragmentPosition = getIntent().getIntExtra("fragment_position", -1);

        if (fragmentPosition != -1) {
            // Mostrar la posición del fragmento como un mensaje
            Toast.makeText(this, "Has seleccionado el fragmento: " + fragmentPosition, Toast.LENGTH_SHORT).show();
        }

        // Recuperar la posición del fragmento guardada en SharedPreferences
        SharedPreferences preferences = getSharedPreferences("AficionesPrefs", MODE_PRIVATE);
        int favoriteFragmentPosition = preferences.getInt("favorite_fragment_position", -1);

        if (favoriteFragmentPosition != -1) {
            // Mostrar el fragmento favorito
            Toast.makeText(this, "Tu fragmento favorito es el número: " + favoriteFragmentPosition, Toast.LENGTH_LONG).show();
        }
    }
}
