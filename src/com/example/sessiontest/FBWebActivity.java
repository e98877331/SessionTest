package com.example.sessiontest;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout.LayoutParams;

public class FBWebActivity extends Activity{
	
	WebView mWV;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mWV = new WebView(this);
		mWV.setWebViewClient(MainActivity.mWebViewClient);
		this.setContentView(mWV,new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT));
		
		mWV.loadUrl("http://www.facebook.com");
	}
	

	

}
