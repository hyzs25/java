package com.hy.drawview;

import android.os.Bundle;
import android.app.Activity;
import android.sax.RootElement;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements OnTouchListener{
	
	private DView draw = new DView(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		LinearLayout ll = (LinearLayout) findViewById(R.id.root);
		
		draw.setMinimumWidth(300);
		draw.setMinimumHeight(500);
		draw.setOnTouchListener(this);
		
		ll.addView(draw);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		draw.currentX = event.getX();
		draw.currentY = event.getY();
		draw.invalidate();
		return true;
	}

}
