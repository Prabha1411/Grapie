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

import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
static final String NODE_CANVAS="canvas";
static final String NODE_TEAM="team";
int X,Y,Width,Height,Trans;
    ArrayList<String > teamname=new ArrayList<>();
    ArrayList<Float> matches=new ArrayList<>();
    ArrayList<String > colour=new ArrayList<>();
    //PieChartData pieChartData;,Right,Bottom

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        XMLDOMParser parser = new XMLDOMParser();
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

          //  NodeList cirlcelist=doc.getElementsByTagName("circle_radius");
            // Element e1=(Element)cirlcelist.item(0);
            //Radius=Integer.parseInt(parser.getValue(e1,"circle_radius"));
            // Radius=Integer.parseInt(doc.getElementsByTagName("circle_radius").item(0).getTextContent());
               //Log.i("helo","Radius");
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
        }


       PieChartData obj=new PieChartData(X,Y,Height,Width,Trans,teamname,matches,colour);


//for (Float i:matches)
//    Log.i("x", String.valueOf(i));

        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.rel);
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(Width,Height);
        params.leftMargin=X;
        params.topMargin=Y;
        MyCanvas mc=new MyCanvas(this);

        mc.setLayoutParams(new ViewGroup.LayoutParams(Width,Height));

        mc.setBackgroundColor(Color.GRAY);

        /*obj.getTrans();
        float conversion;
        conversion=255-((Trans*100)/255);
      mc.getBackground().setAlpha((int) conversion);
      Log.i("haka", String.valueOf(conversion));
*/
        relativeLayout.addView(mc,params);
        mc.setdata(obj);
    }






        //View view =new RelativeLayout.LayoutParams(R.id.rel);

//       Bundle b=getIntent().getExtras();
//       PieChartData r=(PieChartData)b.getSerializable("rect");
//        setContentView(new MyCanvas(this,r));


    }


