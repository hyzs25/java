package com.qihoo.notify_send_tool;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener{
	
	private EditText title,content;
	private Button submitButton;
	private String titleString,contentString;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		title = (EditText) findViewById(R.id.title);
		content = (EditText) findViewById(R.id.content);
		submitButton = (Button) findViewById(R.id.submit);
		
		submitButton.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		send();
	}

	@SuppressWarnings("deprecation")
	private void send() {
		// TODO Auto-generated method stub
		titleString = title.getText().toString();
		contentString = content.getText().toString();
		
		NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		Notification n = new Notification(R.drawable.ic_launcher,"通知",System.currentTimeMillis()); //图标，概要，时间
		
		Intent intent = new Intent(this, MainActivity.class);
		PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
		n.setLatestEventInfo(this, titleString, contentString, pi);
		n.defaults = Notification.DEFAULT_SOUND;
		
		nm.notify(1, n);
	}

}
