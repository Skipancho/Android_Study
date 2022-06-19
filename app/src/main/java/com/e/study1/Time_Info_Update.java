package com.e.study1;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Time_Info_Update extends StringRequest {
    final static private String URL="https://skipancho.cafe24.com/test1/Time_Info_Update.php";
    private Map<String,String> parameters;

    public Time_Info_Update(int id, String info, Response.Listener<String> listener){
        super(Request.Method.POST,URL,listener,null);
        parameters = new HashMap<>();
        parameters.put("id",""+id);
        parameters.put("info",info);
    }

    @Override
    public Map<String,String> getParams(){
        return parameters;
    }
}
