package com.example.reviewmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class bag extends AppCompatActivity {

    private RecyclerView mrv_todo;
    private FloatingActionButton mbtn_right;
    private ArrayList<chickenItem> mchickenItems;
    private DBHelper mDBHelper;
    private CutomeAdpter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicken);
        setTitle("헬스가방의 모든것");

        setInit();
    }

    private void setInit() {
        mDBHelper = new DBHelper(this);
        mrv_todo = findViewById(R.id.rv_todo);
        mbtn_right = findViewById(R.id.btn_right);
        mchickenItems = new ArrayList<>();


        loadRecentDB();


        mbtn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(bag.this, android.R.style.Theme_Material_Light_Dialog);
                dialog.setContentView(R.layout.dialog_edit);
                EditText et_desc_id = dialog.findViewById(R.id.et_desc_id);
                EditText et_desc_st = dialog.findViewById(R.id.et_desc_st);
                EditText et_desc_ki = dialog.findViewById(R.id.et_desc_ki);
                EditText et_desc_re = dialog.findViewById(R.id.et_desc_re);
                Button button = dialog.findViewById((R.id.button));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //insert database
                        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                        mDBHelper.InsertTodo(et_desc_id.getText().toString(), et_desc_st.getText().toString(), et_desc_ki.getText().toString(), et_desc_re.getText().toString(), currentTime);

                        //insert ui
                        chickenItem item = new chickenItem();
                        item.setId(et_desc_id.getText().toString());
                        item.setStore(et_desc_st.getText().toString());
                        item.setKind(et_desc_ki.getText().toString());
                        item.setRe_view(et_desc_re.getText().toString());
                        item.setWriteDate(currentTime);

                        mAdapter.addItem(item);
                        mrv_todo.smoothScrollToPosition(0);
                        dialog.dismiss();
                        Toast.makeText(bag.this, "리뷰가 추가되었습니다.", Toast.LENGTH_SHORT).show();

                    }
                });

                dialog.show();
            }
        });

    }

    private void loadRecentDB() {
        //저자오디어있던 DB를 가져옴
        mchickenItems = mDBHelper.getchicken();
        if(mAdapter == null){
            mAdapter = new CutomeAdpter(mchickenItems, this);
            mrv_todo.setHasFixedSize(true);
            mrv_todo.setAdapter(mAdapter);
        }
    }
}