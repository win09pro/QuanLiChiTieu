package com.example.ql.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ql.R;


/*
	Hien cac icon cho menu chinh
*/
public class ImageAdapter extends BaseAdapter {
	private Context context;
	private final String[] mobileValues;

	public ImageAdapter(Context context, String[] mobileValues) {
		this.context = context;
		this.mobileValues = mobileValues;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View gridView;

		if (convertView == null) {

			gridView = new View(context);

			// get layout from mobile.xml
			gridView = inflater.inflate(R.layout.mobile, null);

			// set value into textview
			TextView textView = (TextView) gridView
					.findViewById(R.id.grid_item_label);
			textView.setText(mobileValues[position]);

			// set image based on selected text
			ImageView imageView = (ImageView) gridView
					.findViewById(R.id.grid_item_image);

			String mobile = mobileValues[position];

			if (mobile.equals("Thể Loại Thu/Chi")) {
				imageView.setImageResource(R.drawable.themtheloai);
				
			} else if (mobile.equals("Khoản Thu")) {
				imageView.setImageResource(R.drawable.khoanthu);
			} else if (mobile.equals("Khoản Chi")) {
				imageView.setImageResource(R.drawable.khoanchi);
			} else if (mobile.equals("Thống Kê")) {
				imageView.setImageResource(R.drawable.thongke);
			} else {
				imageView.setImageResource(R.drawable.luong);
			}

		} else {
			gridView = (View) convertView;
		}

		return gridView;
	}

	@Override
	public int getCount() {
		return mobileValues.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

}
