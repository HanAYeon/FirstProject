package com.pyo.simple.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class NewsSimpleLaunchActivity extends Activity {
  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //루트 레이아웃  생성
        LinearLayout    rootLayout = new LinearLayout(this);
        LinearLayout.LayoutParams fillParams = new 
                         LinearLayout.LayoutParams(
                        		  ViewGroup.LayoutParams.MATCH_PARENT,
            		              ViewGroup.LayoutParams.MATCH_PARENT);
        
        LinearLayout.LayoutParams wrapParams = new 
        LinearLayout.LayoutParams(
       		  ViewGroup.LayoutParams.WRAP_CONTENT,
	              ViewGroup.LayoutParams.WRAP_CONTENT);
        rootLayout.setLayoutParams(fillParams);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setGravity(Gravity.CENTER);
       
        Button startService = new Button(this);
        startService.setLayoutParams(wrapParams);
        startService.setText("서비스시작");
        
        Button stopService = new Button(this);
        stopService.setLayoutParams(wrapParams);
        stopService.setText("서비스정지");
 
        rootLayout.addView(startService);
        rootLayout.addView(stopService);
 
        setContentView(rootLayout);
        startService.setOnClickListener(serviceControlListener);
        stopService.setOnClickListener(serviceControlListener);
    }
    private   View.OnClickListener serviceControlListener = new View.OnClickListener() {	
		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			String  btnLabel = ((Button)view).getText().toString();
			Intent  serviceIntent = new 
					Intent(NewsSimpleLaunchActivity.this, 
							NewsSimpleService.class);
			if(btnLabel.equals("서비스시작")){
				serviceIntent.putExtra("newsSubject", 3);
				startService(serviceIntent);
			}else{
				stopService(serviceIntent);
			}
		}
	};
	public void onPause(){
		super.onPause();
		Intent  serviceIntent = new
				Intent(NewsSimpleLaunchActivity.this,
				NewsSimpleService.class);
		stopService(serviceIntent);
	}
}