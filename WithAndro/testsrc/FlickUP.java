package com.example;

import android.os.Bundle;

public class FlickUP extends FlickCenter {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.sqlist);
		
		textView.setText("UP");
	}

}
