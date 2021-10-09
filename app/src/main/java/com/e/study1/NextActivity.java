package com.e.study1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class NextActivity extends AppCompatActivity {

    private int i = 0;
    private EditText et_1;
    private TextView tv_1, tv_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        et_1 = findViewById(R.id.et_1);
        tv_1 = findViewById(R.id.tv_1);




        //String url ="https://skipancho.github.io/test2/";
        // Request a string response from the provided URL.




        findViewById(R.id.btn_1).setOnClickListener(view -> {
            String text = et_1.getText().toString();
           /* String url = "https://skipancho.cafe24.com/test1/index.php/"+id;

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            tv_1.setText(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    tv_1.setText("That didn't work!");
                }
            });
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(stringRequest);*/
           Response.Listener listener = new Response.Listener() {
               @Override
               public void onResponse(Object response) {
                   String res = (String) response;
                   tv_1.setText(res);
               }
           };
           ExampleRequest request = new ExampleRequest(text,listener);
           RequestQueue queue = Volley.newRequestQueue(this);
           queue.add(request);
        });


    }






    public void putExample(String key, Example example){
        SharedPreferences sharedPref = getSharedPreferences("example", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(example,Example.class);
        editor.putString(key,json);
        editor.commit();
    }

    public Example getExample(String key){
        SharedPreferences sharedPref = getSharedPreferences("example", Context.MODE_PRIVATE);
        Gson gson = new GsonBuilder().create();
        String json = sharedPref.getString(key,null);
        if(json == null){
            return null;
        }
        Example example = gson.fromJson(json,Example.class);
        return example;
    }



    public  void setList(String key, List<Example> productList){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();

        JSONArray jsonArray = new JSONArray();
        Gson gson = new GsonBuilder().create();
        for(int i = 0; i < productList.size();i++){
            String string = gson.toJson(productList.get(i),Example.class);
            jsonArray.put(string);
        }
        if(!productList.isEmpty()){
            editor.putString(key,jsonArray.toString()).commit();
            //System.out.println(jsonArray.toString());
        }else {
            editor.putString(key,null).commit();
            //System.out.println(jsonArray.toString());
        }
    }
    public  List<Example> getList(String key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String json = preferences.getString(key,null);
        List<Example> list = new ArrayList<>();
        Gson gson = new GsonBuilder().create();
        if(json != null){
            try {
                JSONArray jsonArray = new JSONArray(json);
                for(int i = 0; i<jsonArray.length();i++){
                    Example p = gson.fromJson(jsonArray.get(i).toString(), Example.class);
                    list.add(p);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return list;
    }
}