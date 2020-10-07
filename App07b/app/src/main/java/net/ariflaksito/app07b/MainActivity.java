package net.ariflaksito.app07b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText bil1;
    EditText bil2;
    TextView txResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bil1 = findViewById(R.id.inBil1);
        bil2 = findViewById(R.id.inBil2);
        txResult = findViewById(R.id.txResult);

        bil1.setText("0");
        bil2.setText("0");

        Button btnAdd = findViewById(R.id.btn_jml);
        btnAdd.setOnClickListener(this);
        Button btnSub = findViewById(R.id.btn_kurang);
        btnSub.setOnClickListener(this);
        Button btnMul = findViewById(R.id.btn_kali);
        btnMul.setOnClickListener(this);
        Button btnDiv = findViewById(R.id.btn_bagi);
        btnDiv.setOnClickListener(this);
        Button btnClear = findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Double val1 = Double.parseDouble(bil1.getText().toString());
        Double val2 = Double.parseDouble(bil2.getText().toString());

        if(v.getId() == R.id.btn_jml){
            Double res = val1 + val2;
            txResult.setText(res+"");
        }else if(v.getId() == R.id.btn_kurang){
            Double res = val1 - val2;
            txResult.setText(res+"");
        }else if(v.getId() == R.id.btn_kali){
            Double res = val1 * val2;
            txResult.setText(res+"");
        }else if(v.getId() == R.id.btn_bagi){
            Double res = val1 / val2;
            txResult.setText(res+"");
        }else if(v.getId() == R.id.btn_clear){
            bil1.setText("0");
            bil2.setText("0");
            txResult.setText("0");
        }
    }
}