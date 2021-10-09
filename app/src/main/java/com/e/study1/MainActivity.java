package com.e.study1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.btn_1:
                        startActivity(new Intent(MainActivity.this,NextActivity.class));
                        break;
                    case R.id.btn_2:
                        startActivity(new Intent(MainActivity.this,ChartActivity.class));
                        break;
                    case R.id.btn_3:
                        startActivity(new Intent(MainActivity.this,AsyncTaskActivity.class));
                        break;
                    case R.id.btn_4:
                        startActivity(new Intent(MainActivity.this,FragmentActivity.class));
                        break;
                }
            }
        };

        TextView tv_1 = (TextView) findViewById(R.id.tv_1);
        Button btn_1 = (Button) findViewById(R.id.btn_1);
        Button btn_2 = (Button) findViewById(R.id.btn_2);
        Button btn_3 = (Button) findViewById(R.id.btn_3);
        Button btn_4 = (Button) findViewById(R.id.btn_4);

        btn_1.setOnClickListener(listener);
        btn_2.setOnClickListener(listener);
        btn_3.setOnClickListener(listener);
        btn_4.setOnClickListener(listener);

        List<List_Item> list = new ArrayList<>();
        list.add(new List_Item("Volley","click Button 1",1));
        list.add(new List_Item("Chart","click Button 2",2));
        list.add(new List_Item("AsyncTask","click Button 3",3));
        list.add(new List_Item("Fragment","click Button 4",4));
        MyListAdapter adapter = new MyListAdapter(this,list);
        ListView lv_1 = findViewById(R.id.lv_1);
        lv_1.setAdapter(adapter);
    }


}