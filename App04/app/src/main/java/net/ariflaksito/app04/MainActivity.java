package net.ariflaksito.app04;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView label1 = findViewById(R.id.label1);
        final Button btn1 = findViewById(R.id.btn1);
        final Button btn2 = findViewById(R.id.btn2);

        btn1.setBackgroundColor(Color.parseColor("#9c4dcc"));
        btn2.setBackgroundColor(Color.parseColor("#38006b"));

        label1.setText(number +"");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = number +1;
                label1.setText(number +"");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number >0){
                    number = number -1;
                }
                label1.setText(number +"");
            }
        });

    }
}