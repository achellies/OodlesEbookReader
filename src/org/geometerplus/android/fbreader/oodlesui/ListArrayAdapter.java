package org.geometerplus.android.fbreader.oodlesui;

import java.util.List;

import org.geometerplus.zlibrary.ui.android.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class ListArrayAdapter extends ArrayAdapter<String> {
	private LayoutInflater inflater;
	List<String> arrayList;
	Context context;
	private ListView listView;

	public ListArrayAdapter(Context context, List<String> arrayList, int id) {
		super(context, R.layout.item_fb_reader_effect_listview, R.id.fb_reader_rowTextView, arrayList);
		inflater = LayoutInflater.from(context);
		this.arrayList = arrayList;
		if (id == 0) {
			listView = (ListView) ((Activity) context).findViewById(R.id.reader_page_orientaion_mode_ListView);

		} else {
			listView = (ListView) ((Activity) context).findViewById(R.id.reader_page_effect_mode_listview);

		}
		LayoutParams lp = (LayoutParams) listView.getLayoutParams();
		BitmapDrawable bd = (BitmapDrawable) context.getResources().getDrawable(R.drawable.fb_reader_listview_bg);
		lp.width = bd.getBitmap().getWidth();
		listView.setLayoutParams(lp);
	}

	@Override
	public int getCount() {
		// Log.d("check", "getCount Method executed");
		return arrayList.size();
	}

	@Override
	public long getItemId(int position) {
		// Log.i("check", "getItemId Method executed");
		return position;
	}

	@Override
	public String getItem(int position) {
		// Log.d("check", "getItem Method executed");
		return arrayList.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Log.d("check", "get View Executed");
		TextView textView;
		// Create a new row view
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_fb_reader_effect_listview, null);
			textView = (TextView) convertView.findViewById(R.id.fb_reader_rowTextView);
			textView.setText((CharSequence) arrayList.get(position));
		}
		return convertView;
	}
}
