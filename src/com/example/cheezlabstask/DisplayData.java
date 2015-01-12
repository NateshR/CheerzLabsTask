package com.example.cheezlabstask;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayData extends Activity {

	private static String filename = "MySharedString";
	private SharedPreferences someData;
	private String basket;
	private String TAG = "DATE";
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display);
		tv = (TextView) findViewById(R.id.tvDisplay);
		someData = getSharedPreferences(filename, 0);
		basket = someData.getString(TAG, "");
		tv.setText(basket);

	}

}
