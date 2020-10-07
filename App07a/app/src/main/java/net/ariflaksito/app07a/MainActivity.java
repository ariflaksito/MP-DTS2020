package net.ariflaksito.app07a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText inNama;
    TextView labelNama;
    Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inNama = (EditText)findViewById(R.id.inNama);
        labelNama = (TextView)findViewById(R.id.labelNama);
        btnShow = (Button)findViewById(R.id.btnShow);

        btnShow.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnShow){
            labelNama.setText("Hello "+inNama.getText().toString());
        }
    }
}