package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Arrays;
import java.util.List;

public class Practice10HistogramView extends View {

    private String[] textArr = {"Froyo", "GB", "ICS", "JB", "KitKat", "L", "M"};
    private int[] heightArr = {10, 40, 40, 120, 200, 250, 110};
    private Paint paint;
    int space = 20;//柱状图的间距
    int width = 100;//柱状图的间距
    int textY = 640;//画text的纵坐标
    int stopy = 600;//画text的纵坐标

    List<String> deviceNames;

    public Practice10HistogramView(Context context) {
        this(context, null);

    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }


    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        deviceNames = Arrays.asList(textArr);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图

        // 画坐标系
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3);
        canvas.drawLine(100, 100, 100, 600, paint);
        canvas.drawLine(100, 600, 1000, 600, paint);


        for (int i = 0; i < deviceNames.size(); i++) {
            // 画柱状图
            paint.setColor(Color.parseColor("#8800FF00"));
            canvas.drawRect(100+ space*(i+1)+ width*i , stopy - heightArr[i], 100+ space*(i+1)+ width*(i+1), stopy, paint);

            // 画字体
            paint.setTextSize(30);
            paint.setColor(Color.WHITE);
            String text = deviceNames.get(i);
            Rect rect = new Rect();
            paint.getTextBounds(text, 0, text.length(), rect);
            canvas.drawText(text, (float) (100+ space*(i+1)+ width*(i+0.5)-rect.width()/2), textY, paint);
        }

    }
}
