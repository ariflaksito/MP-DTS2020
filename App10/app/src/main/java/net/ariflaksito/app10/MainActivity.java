package net.ariflaksito.app10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBHelper db;
    EditText inName;
    Button btnAdd, btnShow;
    ArrayList<String> listData;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);
        inName = findViewById(R.id.in_name);
        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addStudent(inName.getText().toString());
                inName.setText("");

                String info = getResources().getString(R.string.info_success_add_student);
                Toast.makeText(MainActivity.this, info, Toast.LENGTH_SHORT).show();
            }
        });

        btnShow = findViewById(R.id.btn_show);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listData = db.getStudentList();
                inName.setText("");
                showData();
            }
        });

        listView = findViewById(R.id.listview);

    }

    void showData(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listData);

        listView.setAdapter(adapter);
    }
}