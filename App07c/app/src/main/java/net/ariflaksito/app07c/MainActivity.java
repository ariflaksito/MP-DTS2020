package net.ariflaksito.app07c;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView lv_country;
    private String[] countries = new String[]{
      "Indonesia","Malaysia","Thailand","Singapore",
      "Vietnam","Cambodia","Japan","China"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String title = getResources().getString(R.string.label_country);
        getSupportActionBar().setTitle(title);

        lv_country = findViewById(R.id.lv_country);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, android.R.id.text1, countries);

        lv_country.setAdapter(adapter);
        lv_country.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Anda memilih: "+countries[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}