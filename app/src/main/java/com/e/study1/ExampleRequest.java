package com.e.study1;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ExampleRequest extends StringRequest {
    final static private String URL="https://skipancho.cafe24.com/test1/comment";
    private Map<String,String> parameters;

    public ExampleRequest(String text, Response.Listener<String> listener){
        super(Method.POST,URL,listener,null);
        parameters = new HashMap<>();
        parameters.put("text",text);
    }

    @Override
    public Map<String,String> getParams(){
        return parameters;
    }
}
