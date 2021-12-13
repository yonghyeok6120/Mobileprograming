package com.example.reviewmaster;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button ex_ch, ex_pt, ex_ba, ex_sh, ex_st, ex_wr, ex_kn, ex_bl;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ex_ch = findViewById(R.id.ex_ch);
        ex_pt = findViewById(R.id.ex_pt);
        ex_ba = findViewById(R.id.ex_ba);
        ex_sh = findViewById(R.id.ex_sh);
        ex_st = findViewById(R.id.ex_st);
        ex_wr = findViewById(R.id.ex_wr);
        ex_kn = findViewById(R.id.ex_kn);
        ex_bl = findViewById(R.id.ex_bl);

        ex_ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, chicken.class);
                startActivity(intent);
            }
        });

        ex_pt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, protin.class);
                startActivity(intent);
            }
        });

        ex_ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, bag.class);
                startActivity(intent);
            }
        });

        ex_sh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, shoes.class);
                startActivity(intent);
            }
        });

        ex_st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, shoes.class);
                startActivity(intent);
            }
        });

        ex_wr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, shoes.class);
                startActivity(intent);
            }
        });

        ex_bl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, shoes.class);
                startActivity(intent);
            }
        });

        ex_kn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, shoes.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_option, menu);
        return true;
    }
    @Override

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()) {

            case R.id.menu1:
                Intent intent = new Intent(MainActivity.this, borad.class);
                startActivity(intent);
                break;
            case R.id.menu2:
                Toast.makeText(this, "★jumg6120@naver.com로 메일주세요★", Toast.LENGTH_SHORT).show();
                break;

        }

        return super.onOptionsItemSelected(item);

    }
}