package net.ariflaksito.app10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    static String DBNAME = "studentdb";
    static final int DBVER = 1;
    static String TB_STUDENT = "students";
    static String KEY_ID = "id";
    static String KEY_NAME = "name";

    public DBHelper(Context context) {
        super(context, DBNAME, null, DBVER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+ TB_STUDENT +" ("+
                KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_NAME+" TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP TABLE IF EXISTS '"+ TB_STUDENT +"'";
        db.execSQL(dropTable);
        onCreate(db);
    }

    public long addStudent(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);

        long insert = db.insert(TB_STUDENT, null, cv);
        return insert;
    }

    public ArrayList<String> getStudentList(){
        ArrayList<String> listStudent = new ArrayList<String>();
        String querySelect = "SELECT id, name FROM "+TB_STUDENT;

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(querySelect, null);
        if(c.moveToFirst()){
            do{
                String name = c.getString(c.getColumnIndex(KEY_NAME));
                listStudent.add(name);
            }while (c.moveToNext());
        }

        return listStudent;
    }
}
