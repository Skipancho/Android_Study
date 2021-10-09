package com.e.study1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FragmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();
                switch (id) {
                    case R.id.btn_1:
                        setFragment(1);
                        break;
                    case R.id.btn_2:
                        setFragment(2);
                        break;
                    case R.id.btn_3:
                        setFragment(3);
                        break;
                }
            }
        };

        Button btn_1 = findViewById(R.id.btn_1);
        Button btn_2 = findViewById(R.id.btn_2);
        Button btn_3 = findViewById(R.id.btn_3);

        btn_1.setOnClickListener(clickListener);
        btn_2.setOnClickListener(clickListener);
        btn_3.setOnClickListener(clickListener);

        setFragment(1);
    }
    private void setFragment(int num){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        switch (num){
            case 1:
                ft.replace(R.id.fragment,new Fragment1());
                break;
            case 2:
                ft.replace(R.id.fragment,new Fragment2());
                break;
            case 3:
                ft.replace(R.id.fragment,new Fragment3());
                break;
        }
        ft.commit();
    }
}