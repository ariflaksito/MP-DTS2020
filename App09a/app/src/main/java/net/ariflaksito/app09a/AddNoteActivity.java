package net.ariflaksito.app09a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddNoteActivity extends AppCompatActivity {

    EditText inText;
    TextInputEditText inFile;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        inText = findViewById(R.id.in_content);
        inFile = findViewById(R.id.in_filename);
        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appName = getResources().getString(R.string.app_name);
                String path = Environment.getExternalStorageDirectory().toString() + "/" + appName;
                File dataFile = new File(path, inFile.getText().toString());

                if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    String text = getResources().getString(R.string.media_unavailable);
                    Toast.makeText(AddNoteActivity.this, text, Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    FileOutputStream mOutput = new FileOutputStream(dataFile, false);
                    String data = inText.getText().toString();
                    mOutput.write(data.getBytes());
                    mOutput.close();

                    String text = getResources().getString(R.string.info_save_success);
                    Toast.makeText(AddNoteActivity.this, text, Toast.LENGTH_SHORT).show();
                    finish();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}