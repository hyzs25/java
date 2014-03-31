package com.hy.drawview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DView extends View{
	
	public float currentX = 40;
	public float currentY = 50;

	public DView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		//创建画笔
		Paint p = new Paint();
		//设置画笔颜色
		p.setColor(Color.RED);
		//绘制一个圆
		canvas.drawCircle(currentX, currentY, 15, p);
	}
	
}
