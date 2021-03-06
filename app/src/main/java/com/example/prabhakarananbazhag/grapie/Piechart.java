package com.example.prabhakarananbazhag.grapie;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Piechart extends View {
    Paint paint,paint1;
    ArrayList<HashMap<String, String>> pieChartData;
    float start=0f;
    public Piechart(Context context){
    super(context);
    }
    public Piechart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        paint1=new Paint();
        paint.setColor(Color.BLACK);
    }

    public Piechart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
@Override
    protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    if(pieChartData!=null){
     float[] sweep=scale();
        int z=canvas.getWidth()/20;
        Log.i("val", String.valueOf(getWidth()));
        int w=canvas.getWidth()/30;
        int x=canvas.getWidth()-z;
        int y= canvas.getHeight()-z;
        RectF rectF = new RectF(z,z,x,y);
        RectF rectF1=new RectF(w,w,x+w,y+w);
        for(int i=0;i<pieChartData.size();++i){
            paint.setColor(Color.parseColor(pieChartData.get(i).get("colour")));
            Path p2=new Path();
            canvas.drawArc(rectF, start, sweep[i], true, paint);
            p2.addArc(rectF,start,sweep[i]);
            Path p = new Path();
            p.addArc(rectF1, start, sweep[i]);
            PathMeasure pathMeasure = new PathMeasure(p2, false);
            float pathLength = pathMeasure.getLength();
            paint.setColor(Color.BLACK);
            paint.setTextSize(getWidth() / 25);
            paint.setTextAlign(Paint.Align.CENTER);
            String name = pieChartData.get(i).get("name");
            if (paint.measureText(name) > pathLength) {
                while (paint.measureText(name) < pathLength) {
                    name = name.substring(0, name.length() - 1);
                }
                canvas.drawTextOnPath(name.substring(0, name.length() - 3).concat("..."), p, 0, 0, paint);
            } else {
                canvas.drawTextOnPath(pieChartData.get(i).get("name"), p, 0, 0, paint);
            }
            start += sweep[i];
//            paint1.setShadowLayer(10.0f, 6.0f, 6.0f, Color.BLACK);
//            setLayerType(LAYER_TYPE_SOFTWARE,paint);

        }

    }else{
        return;
    }
}
    private float[] scale() {
float[] sweep=new float[pieChartData.size()];
        float total = getTotal();
for(int i=0;i<pieChartData.size();i++){
      sweep[i] = (Float.parseFloat(pieChartData.get(i).get("matches")) /total) * 360;
     }
        return sweep;
    }
   private float getTotal(){
       float total = 0;
      for(int i=0;i<pieChartData.size();i++) {
          float val= Float.parseFloat(pieChartData.get(i).get("matches"));
          total += val;
      }
      return total;
   }
    public void setdata(ArrayList<HashMap<String, String>> chartdata){
        pieChartData=chartdata;
        postInvalidate();
    }
}
