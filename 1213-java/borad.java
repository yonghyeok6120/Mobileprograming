package com.example.reviewmaster;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class borad extends AppCompatActivity {

    myDBHelper myHelper;
    EditText edttitle, edtview, edttitleResult, edtviewResult;
    Button btnInsert, btnSelect;
    SQLiteDatabase sqlDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borad);
        setTitle("자유게시판");

        edttitle = (EditText) findViewById(R.id.edttitle);
        edtview = (EditText) findViewById(R.id.edtview);
        edttitleResult = (EditText) findViewById(R.id.edttitleResult);
        edtviewResult = (EditText) findViewById(R.id.edtviewrResult);

        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnSelect = (Button) findViewById(R.id.btnSelect);

        myHelper = new myDBHelper(this);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO groupTBL VALUES ( '"
                        + edttitle.getText().toString() + "' , '"
                        + edtview.getText().toString() + "');");
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "입력됨",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM groupTBL;", null);

                String strTitle = "제목" + "\r\n" + "----------------" + "\r\n";
                String strView = "내용" + "\r\n" + "----------------------------------" + "\r\n";

                while (cursor.moveToNext()) {
                    strTitle += cursor.getString(0) + "\r\n";
                    strView += cursor.getString(1) + "\r\n";
                }

                edttitleResult.setText(strTitle);
                edtviewResult.setText(strView);

                cursor.close();
                sqlDB.close();
            }
        });

    }

    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "groupDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE  groupTBL ( gTitle VACHAR(15) PRIMARY KEY, gView VACHAR(30));");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);
        }
    }
}
