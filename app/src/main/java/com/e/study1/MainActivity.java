package com.e.study1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity{
    private MyListAdapter adapter;
    private List<List_Item> list;

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

        list = new ArrayList<>();
        adapter = new MyListAdapter(this,list);
        ListView lv_1 = findViewById(R.id.lv_1);
        lv_1.setAdapter(adapter);

        new BackgroundTask().execute();
    }

    class BackgroundTask extends AsyncTask<Void,Void,String> {

        String target;

        @Override
        protected void onPreExecute(){
            try {
                target="https://skipancho.cafe24.com/test1/Time_line.php";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(target);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                InputStream inputStream = httpsURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp=bufferedReader.readLine()) != null){
                    stringBuilder.append(temp+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpsURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        public void onProgressUpdate(Void... values){
            super.onProgressUpdate();
        }
        @Override
        public void onPostExecute(String result){
            try{
                int id;
                String l_time, s_time, info;
                int count = 0;
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                while(count<jsonArray.length()){
                    JSONObject object = jsonArray.getJSONObject(count);
                    id = object.getInt("id");
                    s_time = object.getString("server_time");
                    l_time = object.getString("local_time");
                    info = object.getString("info");
                    list.add(new List_Item(id,s_time,l_time,info));
                    count++;
                }
                adapter.notifyDataSetChanged();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }




}