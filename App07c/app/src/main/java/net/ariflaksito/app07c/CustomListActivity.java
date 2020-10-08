package net.ariflaksito.app07c;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class CustomListActivity extends AppCompatActivity {

    private ListView lvCustom;
    private String[] countries = new String[]{
            "Indonesia","Malaysia","Thailand","Singapore",
            "Vietnam","Cambodia","Japan","China"
    };

    private String[] capitals = new String[]{
            "Jakarta", "Kuala lumpur", "Bangkok",
            "Singapore", "Hanoi", "Phnom Penh", "Tokyo",
            "China"
    };

    private int[] pop = {267700000,31530000,69430000,5639000,95540000,16250000,126000000,1400000000};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);

        String title = getResources().getString(R.string.label_country);
        getSupportActionBar().setTitle(title);

        lvCustom = findViewById(R.id.lv_custom_countries);
        CountryAdapter adapter = new CountryAdapter(this,countries, capitals, pop);
        lvCustom.setAdapter(adapter);
    }
}