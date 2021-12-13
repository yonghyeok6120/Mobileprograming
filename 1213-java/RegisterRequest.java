package com.example.reviewmaster;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.invoke.MethodType;
import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    //서버 URL설정 (PHP파일 연동)
    final static private String URL = "http://jumg6120.dothome.co.kr/register.php";
    private Map<String, String> map;


    public RegisterRequest(String userID, String userPassword, String userName, String userTell, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPassword", userPassword);
        map.put("userName", userName);
        map.put("userTell", userTell);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
