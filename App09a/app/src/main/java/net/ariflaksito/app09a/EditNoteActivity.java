package net.ariflaksito.app09a;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import static net.ariflaksito.app09a.MainActivity.INTENT_MAPNAME;

public class EditNoteActivity extends AppCompatActivity {

    EditText inText;
    TextInputEditText inFile;
    Button btnSave, btnDelete;
    String fname, path;
    File dataFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        inText = findViewById(R.id.in_content);
        inFile = findViewById(R.id.in_filename);
        btnSave = findViewById(R.id.btn_save);
        btnDelete = findViewById(R.id.btn_delete);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            fname = bundle.getString(INTENT_MAPNAME);
        }

        inFile.setText(fname);
        String appName = getResources().getString(R.string.app_name);
        path = Environment.getExternalStorageDirectory().toString() + "/" + appName;
        dataFile = new File(path, fname);

        if (dataFile.exists()) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(dataFile));
                String line = br.readLine();
                while (line != null) {
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            inText.setText(text.toString());
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String FILENAME = inFile.getText().toString();
                File dataFile = new File(path, FILENAME);

                if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    String text = getResources().getString(R.string.media_unavailable);
                    Toast.makeText(EditNoteActivity.this, text, Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    dataFile.createNewFile();
                    OutputStream outputStream = new FileOutputStream(dataFile);
                    OutputStreamWriter osWriter = new OutputStreamWriter(outputStream);
                    osWriter.append(inText.getText().toString());
                    osWriter.flush();
                    osWriter.close();
                    outputStream.flush();
                    outputStream.close();

                    String text = getResources().getString(R.string.info_save_success);
                    Toast.makeText(EditNoteActivity.this, text, Toast.LENGTH_SHORT).show();
                    finish();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditNoteActivity.this);

                builder
                        .setMessage(getResources().getString(R.string.notif_del))
                        .setPositiveButton(getResources().getString(R.string.label_del_yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                if (dataFile.exists()) {
                                    dataFile.delete();
                                    String text = getResources().getString(R.string.info_del_success);
                                    Toast.makeText(EditNoteActivity.this, text, Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.label_del_no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        .show();
            }
        });

    }
}