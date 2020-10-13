package net.ariflaksito.app09a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView lvFile;
    static final int WRITE_CODE_STORAGE = 100;
    static final int READ_CODE_STORAGE = 200;

    static final String LOG_TAG = "Log Activity";
    static final String KEY_MAPNAME = "name";
    static final String INTENT_MAPNAME = "key_name";
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvFile = findViewById(R.id.lv_files);
        lvFile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> item = (Map<String, Object>) parent.getAdapter().getItem(position);

                Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
                intent.putExtra(INTENT_MAPNAME, item.get(KEY_MAPNAME).toString());
                startActivity(intent);

            }
        });

        fab = findViewById(R.id.buttonAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkWritePermission() && checkReadPermission())
                getListFiles();
        } else
            getListFiles();
    }

    private boolean checkWritePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                return true;
            else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_CODE_STORAGE);
                return false;
            }
        } else {
            return true;
        }
    }

    private boolean checkReadPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                return true;
            else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_CODE_STORAGE);
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_CODE_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getListFiles();
            }
        }else if(requestCode == READ_CODE_STORAGE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) { }
        }
    }


    private void getListFiles() {
        String appName = getResources().getString(R.string.app_name);
        String path = Environment.getExternalStorageDirectory().toString() + "/" + appName;
        File dir = new File(path);

        if (!dir.exists()) {

            dir.mkdirs();
            final String FILENAME = "Default";
            File dataFile = new File(path, FILENAME);

            if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String text = getResources().getString(R.string.media_unavailable);
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                FileOutputStream mOutput = new FileOutputStream(dataFile, false);
                String data = "Data text";
                mOutput.write(data.getBytes());
                mOutput.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File[] files = dir.listFiles();
        String[] fname = new String[files.length];
        String[] datec = new String[files.length];
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM YYYY HH:mm:ss");
        ArrayList<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < files.length; i++) {
            fname[i] = files[i].getName();
            Date tempDate = new Date(files[i].lastModified());
            datec[i] = dateFormat.format(tempDate);

            Map<String, Object> itemMap = new HashMap<String, Object>();
            itemMap.put("name", fname[i]);
            itemMap.put("date", datec[i]);
            dataList.add(itemMap);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, dataList, android.R.layout.simple_list_item_2,
                new String[]{"name", "date"}, new int[]{android.R.id.text1, android.R.id.text2});
        lvFile.setAdapter(adapter);
    }
}