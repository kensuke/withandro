package com.example;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FlickCenter extends FlickableActivity {
	

	
	TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);

		activities.add(null);
		activities.add(FlickUP.class);
		activities.add(FlickDOWN.class);
		activities.add(FlickLEFT.class);
		activities.add(FlickRIGHT.class);

		
		LinearLayout ll = new LinearLayout(this);
		
		textView = new TextView(this);
		textView.setText("CENTER");
		ll.addView(textView);
		
		setContentView(ll);
	}

}
