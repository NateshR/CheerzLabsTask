package com.example.cheezlabstask;

import java.text.DateFormatSymbols;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private Button BDate, BSave, BDisplay;
	private int year, month, date;
	private Calendar calendar;
	private static String filename = "MySharedString";
	private SharedPreferences someData;
	private SharedPreferences.Editor editor;
	private StringBuilder sb;
	private String TAG = "DATE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		BDate = (Button) findViewById(R.id.BDate);
		BSave = (Button) findViewById(R.id.BSave);
		BDisplay = (Button) findViewById(R.id.BDisplay);

		calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		date = calendar.get(Calendar.DAY_OF_MONTH);

		BDate.setOnClickListener(this);
		BSave.setOnClickListener(this);
		BDisplay.setOnClickListener(this);

		someData = getSharedPreferences(filename, 0);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.BDate:
			new DatePickerDialog(MainActivity.this, mDatePickListener, year,
					month + 1, date).show();
			editor = someData.edit();
			editor.putString(TAG, "Please Save Date !");
			editor.commit();
			break;
		case R.id.BSave:
			if (sb != null) {
				String save = sb.toString();
				editor = someData.edit();
				editor.putString(TAG, save);
				editor.commit();
				Toast.makeText(MainActivity.this, save + " saved",
						Toast.LENGTH_SHORT).show();
				sb = null;
			} else {
				Toast.makeText(MainActivity.this, "Please Set Date First !",
						Toast.LENGTH_SHORT).show();
				editor = someData.edit();
				editor.putString(TAG, "Please Set Date First !");
				editor.commit();
			}
			break;
		case R.id.BDisplay:
			Intent i = new Intent(MainActivity.this, DisplayData.class);
			startActivity(i);
		default:
			break;
		}
	}

	private DatePickerDialog.OnDateSetListener mDatePickListener = new OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			sb = new StringBuilder();
			sb.append(dayOfMonth).append(" ").append(getMonth(monthOfYear))
					.append(" ").append(year);
			Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_SHORT)
					.show();
		}

	};

	private String getMonth(int month) {
		return new DateFormatSymbols().getMonths()[month];
	}

}
