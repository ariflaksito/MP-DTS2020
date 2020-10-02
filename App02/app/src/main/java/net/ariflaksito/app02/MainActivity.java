package net.ariflaksito.app02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("DEBUG_MSG","Hello world");

        String usia = hitungUsia(3,3,1997);
        Log.d("DEBUG_MSG","--------------");
        Log.d("DEBUG_MSG", usia);

    }

    private String hitungUsia(int tgl, int bln, int thn){

        int t1 = tgl + 30*bln + 365*thn;
        int t2 = 30 + 30*9 + 365*2020;
        int t = t2 - t1;

        int th = t / 365;
        int s = t % 365;
        int bl = s / 30;
        int tg = s % 30;

        return "Usia anda "+th+" tahun "+bl+" bulan "+tg+" hari";
    }
}