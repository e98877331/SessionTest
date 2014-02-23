package com.example.sessiontest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button mBtn1,mBtn2, mBtn3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		mBtn1 = (Button)findViewById(R.id.button1);
		mBtn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
                Intent i = new Intent();
                i.setClass(MainActivity.this, FirstButtonView.class);
                startActivity(i);
			}
		});
		
		mBtn2 = (Button)findViewById(R.id.button2);
		mBtn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
                Intent i = new Intent();
                i.setClass(MainActivity.this, SecondButtonView.class);
                startActivity(i);
			}
		});
		
		
		mBtn3 = (Button)findViewById(R.id.button3);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
