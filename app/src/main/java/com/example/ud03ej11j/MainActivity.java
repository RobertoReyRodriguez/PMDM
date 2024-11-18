package com.example.ud03ej11j;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar SharedPreferences
        sharedPreferences = getSharedPreferences("ActivityData", MODE_PRIVATE);

        // Obtener referencias de vistas
        TextView tvCurrentScreen = findViewById(R.id.tv_current_screen);
        TextView tvPreviousScreen = findViewById(R.id.tv_previous_screen);
        TextView tvTotalChanges = findViewById(R.id.tv_total_changes);

        // Configurar datos de pantalla
        tvCurrentScreen.setText("Pantalla actual: 1");
        int previousScreen = getIntent().getIntExtra("previous_screen", 0);
        tvPreviousScreen.setText("Pantalla anterior: " + (previousScreen == 0 ? "N/A" : previousScreen));

        // Actualizar y mostrar contador de cambios
        int totalChanges = sharedPreferences.getInt("total_changes", 0);
        totalChanges++;
        sharedPreferences.edit().putInt("total_changes", totalChanges).apply();
        tvTotalChanges.setText("Número de cambios: " + totalChanges);

        // Configurar navegación
        configureNavigationButtons();
    }

    private void configureNavigationButtons() {
        Button btnToScreen1 = findViewById(R.id.btn_to_screen_1);
        Button btnToScreen2 = findViewById(R.id.btn_to_screen_2);
        Button btnToScreen3 = findViewById(R.id.btn_to_screen_3);

        // Navegar a las pantallas correspondientes
        btnToScreen1.setOnClickListener(view -> navigateToScreen(MainActivity.class, 1));
        btnToScreen2.setOnClickListener(view -> navigateToScreen(SecondActivity.class, 1));
        btnToScreen3.setOnClickListener(view -> navigateToScreen(ThirdActivity.class, 1));
    }

    private void navigateToScreen(Class<?> targetActivity, int currentScreen) {
        Intent intent = new Intent(MainActivity.this, targetActivity);
        intent.putExtra("previous_screen", currentScreen);
        startActivity(intent);
    }
}
