package com.marlenepaper.meh.fr.aficiones;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.marlenepaper.meh.Aficiones;
import com.marlenepaper.meh.DetalleFragmentActivity;
import com.marlenepaper.meh.R;
import com.marlenepaper.meh.databinding.ActivitySobreMiBinding;

public class SobreMi extends AppCompatActivity {

    private ActivitySobreMiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySobreMiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_desplegable, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.favStarButton) {
            Intent intent = new Intent(SobreMi.this, DetalleFragmentActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.homeButton) {
            Intent intent = new Intent(SobreMi.this, Aficiones.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.aboutMeButton) {
            return false;
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