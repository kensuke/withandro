package com.example;

import android.os.Bundle;

public class FlickUP extends FlickCenter {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.sqlist);
		
		textView.setText("UP");
	}

}
