package com.e.study1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class TimeUpdateActivity extends AppCompatActivity {
    private int item_id;
    private String item_info;
    private EditText info_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_update);
        item_id = getIntent().getIntExtra("id",-1);
        if(item_id == -1){
            Toast.makeText(this,"에러",Toast.LENGTH_SHORT).show();
            finish();
        }
        item_info = getIntent().getStringExtra("info");
        TextView info_tv = findViewById(R.id.info_tv);
        info_tv.setText(item_info);

        info_et = findViewById(R.id.info_et);

        findViewById(R.id.update_btn).setOnClickListener(view -> {
            String info = info_et.getText().toString();
            UpdateInfo(item_id,info);
        });
    }

    private void UpdateInfo(int id, String info){
        Response.Listener<String> listener = response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                boolean success = jsonObject.getBoolean("success");
                if(success){
                    Toast.makeText(TimeUpdateActivity.this,"수정 완료",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(TimeUpdateActivity.this,"에러",Toast.LENGTH_SHORT).show();
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        };
        Time_Info_Update request = new Time_Info_Update(id,info,listener);
        RequestQueue queue = Volley.newRequestQueue(TimeUpdateActivity.this);
        queue.add(request);
    }
}