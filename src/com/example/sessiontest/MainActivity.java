package com.example.sessiontest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends Activity {

	Button mBtn1,mBtn2, mBtn3,mBtn4;
	TextView mApiResultView;
	CheckBox mShareCookieCB;
	
	 static WebViewClient mWebViewClient = new WebViewClient() {
		  @Override
		  public boolean shouldOverrideUrlLoading(WebView view, String url) {
		   view.loadUrl(url);
		   return true;
		  }
		 };
	
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
		mBtn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
                Intent i = new Intent();
                i.setClass(MainActivity.this, FBWebActivity.class);
                startActivity(i);
			}
		});
		
		
		mBtn4 = (Button)findViewById(R.id.button4);
		mBtn4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					postTask();
				}
			}).start();
			}
		});
	
		mApiResultView = (TextView)findViewById(R.id.textView3);
		
		
		mShareCookieCB = (CheckBox)findViewById(R.id.checkBox1);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	//for sharing cookie
	public static String getCookieFromAppCookieManager(String url) throws MalformedURLException {
	    CookieManager cookieManager = CookieManager.getInstance();
	    if (cookieManager == null)
	        return null;
	    String rawCookieHeader = null;
	    URL parsedURL = new URL(url);

	    // Extract Set-Cookie header value from Android app CookieManager for this URL
	    rawCookieHeader = cookieManager.getCookie(parsedURL.getHost());
	    if (rawCookieHeader == null)
	        return null;
	    return rawCookieHeader;
	}
	

	private void postTask()
	{
		
		boolean isSharingCookie = mShareCookieCB.isChecked();
		
		String url = "http://ec2-54-199-191-137.ap-northeast-1.compute.amazonaws.com/test/api.php";
		HttpClient httpclient = new DefaultHttpClient();
		
		
		httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

		//�إ�post request
		HttpPost httppost = new HttpPost(url);
		
		//setting webclient cookie
		try {
			if(isSharingCookie)
			httppost.addHeader("Cookie", getCookieFromAppCookieManager(url));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//�n�ǰe���ɮ�
		List nameValuePairs = new ArrayList(2);
		nameValuePairs.add(new BasicNameValuePair("id", "12345"));
		nameValuePairs.add(new BasicNameValuePair("stringdata", "AndDev is Cool!"));
		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			
			BasicResponseHandler handler = new BasicResponseHandler();
			final String responseString = handler.handleResponse(response);
			
			Log.e("result",responseString);
			
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					mApiResultView.setText(responseString);
				}
			});
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//����
	}
}
