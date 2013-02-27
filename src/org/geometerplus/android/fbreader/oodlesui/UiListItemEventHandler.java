package org.geometerplus.android.fbreader.oodlesui;

import org.geometerplus.fbreader.fbreader.ActionCode;
import org.geometerplus.fbreader.fbreader.FBReaderApp;
import org.geometerplus.zlibrary.ui.android.R;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.TextView;

public class UiListItemEventHandler implements OnItemClickListener {
	View previous = null;
	FBReaderApp fbReaderApp;

	/**
	 * 
	 */
	public UiListItemEventHandler() {
		super();
		fbReaderApp = (FBReaderApp) FBReaderApp.Instance();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		// Log.d("check", "onItemClick Method executed");
		CheckBox checkBox = (CheckBox) view.findViewById(R.id.fb_reader_checkBox);
		if (previous != null) {
			CheckBox cb1 = (CheckBox) previous.findViewById(R.id.fb_reader_checkBox);
			cb1.setVisibility(View.INVISIBLE);
		}
		checkBox.setButtonDrawable(R.drawable.fb_reader_check_box);
		checkBox.setVisibility(View.VISIBLE);
		previous = view;
		TextView textView = (TextView) view.findViewById(R.id.fb_reader_rowTextView);
		if (textView.getText().equals("Landscape")) {
			fbReaderApp.runAction(ActionCode.SET_SCREEN_ORIENTATION_LANDSCAPE);
		} else if (textView.getText().equals("Portrait")) {
			fbReaderApp.runAction(ActionCode.SET_SCREEN_ORIENTATION_PORTRAIT);
		} else if (textView.getText().equals("Auto")) {
			fbReaderApp.runAction(ActionCode.SET_SCREEN_ORIENTATION_SENSOR);
		} else if (textView.getText().equals("Sepia")) {
			 fbReaderApp.runAction(ActionCode.SWITCH_TO_SEPIA_PROFILE);
		} else if (textView.getText().equals("Night")) {
			fbReaderApp.runAction(ActionCode.SWITCH_TO_NIGHT_PROFILE);
		} else if (textView.getText().equals("Classic")) {
			fbReaderApp.runAction(ActionCode.SWITCH_TO_DAY_PROFILE);
		}
	}

}
