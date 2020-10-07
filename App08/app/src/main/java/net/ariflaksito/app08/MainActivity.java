package net.ariflaksito.app08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnInStorage = findViewById(R.id.btnInStorage);
        btnInStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent internal = new Intent(MainActivity.this, InStorageActivity.class);
                startActivity(internal);
            }
        });

        Button btnExStorage = findViewById(R.id.btnExStorage);
        btnExStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent internal = new Intent(MainActivity.this, ExStorageActivity.class);
                startActivity(internal);
            }
        });
    }
}