package com.example.prabhakarananbazhag.grapie;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    //static final String NODE_CANVAS="canvas";
//static final String NODE_TEAM="team";
//int X,Y,Width,Height,Trans;
   /* ArrayList<String> teamname = new ArrayList<>();
    ArrayList<Float> matches = new ArrayList<>();
    ArrayList<String> colour = new ArrayList<>();*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyCanvas mc = (MyCanvas) findViewById(R.id.grap);

 /*       XMLDOMParser parser = new XMLDOMParser();
        AssetManager manager = getAssets();
        InputStream stream;
        try {
            stream = manager.open("data.xml");
            Document doc = parser.getDocument(stream);


            NodeList nodeList = doc.getElementsByTagName(NODE_CANVAS);
                Element e = (Element) nodeList.item(0);
                X=Integer.parseInt(parser.getValue(e,"value_x"));
                Y=Integer.parseInt(parser.getValue(e,"value_y"));
                Width=Integer.parseInt(parser.getValue(e,"width"));
                Height=Integer.parseInt(parser.getValue(e,"height"));


            NodeList nodeList1=doc.getElementsByTagName(NODE_TEAM);

                for(int i=0;i<nodeList1.getLength();i++){
                    Element team=(Element)nodeList1.item(i);
                    teamname.add(parser.getValue(team,"name"));
                    matches.add(Float.valueOf(parser.getValue(team,"matches")));
                    colour.add(parser.getValue(team,"colour"));
                }

          NodeList nodeList2=doc.getElementsByTagName("Trans");
        } catch (IOException e1) {
            e1.printStackTrace();
        }*/


       // PieChartData obj = new PieChartData(teamname, matches, colour);
       /*RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.grap);
        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.rel);
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(Width,Height);
        params.leftMargin=X;
        params.topMargin=Y;*/

       /* mc.setLayoutParams(new ViewGroup.LayoutParams(Width,Height));
        mc.setBackgroundColor(Color.GRAY);
        relativeLayout.addView(mc,params);*/

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
           // Log.d("Details-->", jo_inside.getString("name"));
            String name_value = jo_inside.getString("name");
            String matches_value = jo_inside.getString("matches");
            String colour_value=jo_inside.getString("colour");
            //Add your values in your `ArrayList` as below:
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