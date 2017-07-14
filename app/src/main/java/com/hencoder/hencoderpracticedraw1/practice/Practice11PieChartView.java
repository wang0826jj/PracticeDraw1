package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice11PieChartView extends View {

    private String[] textArr = {"Froyo", "GB", "ICS", "JB", "KitKat", "L", "M"};
    private Integer[] percentArr = {2, 8, 10, 50, 80, 160, 50};
    private Integer[] colorArr = {Color.BLACK, Color.BLUE, Color.GRAY, Color.GREEN, Color.RED, Color.LTGRAY, Color.YELLOW};

    Paint paintPie;//画饼

    Paint paintText;//画字体

    Paint paintLine;//画线

    Path path;


    RectF rectfCommon;//不移动的部分

    RectF rectfMove;//移动的部分

    int offset = 10; // 最大的扇形 偏移量

    public Practice11PieChartView(Context context) {
        this(context, null);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图

        int width = getWidth();
        int height = getHeight();
        canvas.translate(width/2, height/2);
        float startAngle = 0;
        for (int i = 0; i < textArr.length; i++) {

            // 画扇形
            paintPie.setColor(colorArr[i]);
            float lineAngle = startAngle + percentArr[i]/2; // 每个弧度中点的角度

            if(i == 5){ // 第6个扇形 偏移一部分
                canvas.drawArc(rectfMove, startAngle + 1, percentArr[i] - 1, true, paintPie);
            }else {
                canvas.drawArc(rectfCommon, startAngle + 1, percentArr[i] - 1, true, paintPie);
            }


            path = new Path();
            // 路径添加斜线
            float lineStartX = (float) (300 * Math.cos(lineAngle * Math.PI / 180));
            float lineStartY = (float) (300 * Math.sin(lineAngle * Math.PI / 180));
            float lineMediumX = (float) (350 * Math.cos(lineAngle * Math.PI / 180));
            float lineMediumY = (float) (350 * Math.sin(lineAngle * Math.PI / 180));

            if(i == 5){
                canvas.translate(-offset, -offset);
                path.moveTo(lineStartX, lineStartY);
                path.lineTo(lineMediumX, lineMediumY);
                canvas.translate(offset, offset);
            }else{
                path.moveTo(lineStartX, lineStartY);
                path.lineTo(lineMediumX, lineMediumY);
            }

            // 路径添加直线
            if(lineStartX < 0){
                if(i == 5){
                    canvas.translate(-offset, -offset);
                    path.lineTo(-400, lineMediumY);
                    canvas.drawText(textArr[i], -450, lineMediumY, paintText);
                    canvas.translate(offset, offset);

                }else {
                    path.lineTo(-350, lineMediumY);
                    canvas.drawText(textArr[i], -450, lineMediumY, paintText);
                }
            }else {
                path.lineTo(400, lineMediumY);
                canvas.drawText(textArr[i], 400, lineMediumY, paintText);
            }

            // 画路径
            canvas.drawPath(path, paintLine);

            startAngle += percentArr[i];
        }

    }

    private void init() {
        paintPie = new Paint(Paint.ANTI_ALIAS_FLAG);

        paintLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintLine.setStrokeWidth(5);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setColor(Color.LTGRAY);

        paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setTextSize(30);
        paintText.setColor(Color.LTGRAY);

        rectfCommon = new RectF(-300, -300, 300, 300);
        rectfMove = new RectF(-(300 + offset), -(300 + offset), 300 - offset, 300 - offset);

    }
}
