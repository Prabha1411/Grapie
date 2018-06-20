package com.example.prabhakarananbazhag.grapie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Piechart mc = (Piechart) findViewById(R.id.grap);
        mc.setdata(getjson());
    }

public  ArrayList<HashMap<String, String>> getjson(){
    ArrayList<HashMap<String, String>> formList=null;
    try {
        JSONObject obj = new JSONObject(loadJSONFromAsset());
        JSONArray m_jArry = obj.getJSONArray("team");
        formList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> m_li;

        for (int i = 0; i < m_jArry.length(); i++) {
            JSONObject jo_inside = m_jArry.getJSONObject(i);
            String name_value = jo_inside.getString("name");
            String matches_value = jo_inside.getString("matches");
            String colour_value=jo_inside.getString("colour");
            m_li = new HashMap<String, String>();
            m_li.put("name", name_value);
            m_li.put("matches", matches_value);
            m_li.put("colour",colour_value);
            formList.add(m_li);
        }
    } catch (JSONException e) {
        e.printStackTrace();
    }
    return formList;
    }
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("infor.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}