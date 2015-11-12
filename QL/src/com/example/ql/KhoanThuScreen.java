package com.example.ql;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class KhoanThuScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_khoan_thu_screen);
		loadTabs();
	}

	
	public void loadTabs() {
		// L?y Tabhost id ra tru?c (cái này c?a built - in android
		final TabHost tab = (TabHost) findViewById(android.R.id.tabhost);
		// g?i l?nh setup
		tab.setup();
		TabHost.TabSpec spec;
		// T?o tab1
		spec = tab.newTabSpec("t1");
		spec.setContent(R.id.tab1kt);
		spec.setIndicator("1-Thêm Khoản Thu");
		tab.addTab(spec);
		// T?o tab2
		spec = tab.newTabSpec("t2");
		spec.setContent(R.id.tab2kt);
		spec.setIndicator("2-Danh Sách Khoản Thu");
		tab.addTab(spec);
		// Thi?t l?p tab m?c d?nh du?c ch?n ban d?u là tab 0
		tab.setCurrentTab(0);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.khoan_thu_screen, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
