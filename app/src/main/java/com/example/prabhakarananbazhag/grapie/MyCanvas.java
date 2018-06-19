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

public class MyCanvas extends View {
Paint paint,paint1;
PieChartData pieChartData;
    float start=0f;
   


    public MyCanvas(Context context){
    super(context);
    paint=new Paint();
        paint1=new Paint();
    paint.setColor(Color.BLACK);
  //      paint1.setColor(Color.RED);
   // paint.setShadowLayer(3f,9f,9f,Color.BLACK);

}

    public MyCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


    }

    public MyCanvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

@Override
    protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);


    if (pieChartData != null) {
            float[] sweep=scale();
            int z=pieChartData.getWidth()/20;
            int w=pieChartData.getWidth()/30;

            int x=pieChartData.getWidth()-z;
            int y= pieChartData.getHeight()-z;
            RectF rectF = new RectF(z,z,x,y);
            RectF rectF1=new RectF(w,w,x+w,y+w);

        for(int i=0;i<pieChartData.getMatches().size();i++) {


            paint.setColor(Color.parseColor(pieChartData.getColor().get(i)));
            //System.out.println("sasasd"+start+"sweep"+sweep[i]);
            canvas.drawArc(rectF, start, sweep[i], true, paint);
            // paint.setColor(Color.BLACK);

            //paint.setShadowLayer(10.0f, 6.0f, 6.0f, Color.BLACK);
            // setLayerType(LAYER_TYPE_SOFTWARE,paint);


            Path p = new Path();
            p.addArc(rectF1, start, sweep[i]);
            PathMeasure pathMeasure = new PathMeasure(p, false);
            float pathLength = pathMeasure.getLength();

            //paint1.setColor(Color.BLACK);
            //paint.setShadowLayer(10.0f, 6.0f, 6.0f, Color.RED);
            //setLayerType(LAYER_TYPE_SOFTWARE,paint);
            //Log.i("x", String.valueOf(pathLength));

            // float a[]=new float[2];
            // pathMeasure.getPosTan(pathLength/2,a,null);
            // float rad=x/2;

            // Log.i("x", String.valueOf(rad));
            // Path p2=new Path();
            // p2.moveTo(rad+50,rad);
            // p2.lineTo(rad,pathLength/2);
            paint.setColor(Color.BLACK);
            paint.setTextSize(getWidth() / 25);
            paint.setTextAlign(Paint.Align.CENTER);
            //Log.i("dsd", String.valueOf(paint.measureText(pieChartData.getName().get(i))));
            String name = pieChartData.getName().get(i);
            if (paint.measureText(name) > pathLength) {
                while (paint.measureText(name) < pathLength) {
                    name = name.substring(0, name.length() - 1);
                }
                canvas.drawTextOnPath(name.substring(0, name.length() - 3).concat("..."), p, 0, 0, paint);
            } else {
                canvas.drawTextOnPath(pieChartData.getName().get(i), p, 0, 0, paint);
            }
            start += sweep[i];


            /*final float[] position = new float[2];
            for ( i = 0; i < pieChartData.getName().size(); ++i) {
                final float distance = (i * pathLength) / 2;
                pathMeasure.getPosTan(distance, position, null);



            }*/

        }


    } else {
        return;
    }


}

    private float[] scale() {

float[] sweep=new float[pieChartData.getMatches().size()];
        float total = getTotal();
for(int i=0;i<pieChartData.getMatches().size();i++){
    sweep[i] = (pieChartData.getMatches().get(i) / total) * 360;
}
        return sweep;
    }
   private float getTotal(){
       float total = 0;
       for (float val : pieChartData.getMatches())
           total += val;
 return total;
   }
    public void setdata(PieChartData chartdata){
        pieChartData=chartdata;
      postInvalidate();
    }


}
