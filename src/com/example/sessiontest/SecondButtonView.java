package com.example.sessiontest;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout.LayoutParams;

public class SecondButtonView extends Activity {
	WebView mWV;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mWV = new WebView(this);
		mWV.setWebViewClient(mWebViewClient);
		this.setContentView(mWV,new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT));
		
		mWV.loadUrl("http://ec2-54-199-191-137.ap-northeast-1.compute.amazonaws.com/test/content.php");
	}
	
	 WebViewClient mWebViewClient = new WebViewClient() {
		  @Override
		  public boolean shouldOverrideUrlLoading(WebView view, String url) {
		   view.loadUrl(url);
		   return true;
		  }
		 };
}
