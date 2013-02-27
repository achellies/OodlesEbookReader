package org.geometerplus.android.fbreader.oodlesui;

import org.geometerplus.fbreader.fbreader.ActionCode;
import org.geometerplus.fbreader.fbreader.FBReaderApp;
import org.geometerplus.zlibrary.core.library.ZLibrary;
import org.geometerplus.zlibrary.ui.android.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;



public class UiButtonsEventHandler implements OnClickListener {

	private Context context;
	private ImageView brightness_button;
	private ImageView text_button;
	private ImageView effect_button;
	private ImageView orientation_button;
	private ImageView page_scroll_button;
	private RelativeLayout brightness_relative_view;
	private RelativeLayout effect_mode_relative_view;
	private RelativeLayout orientation_mode_relative_view;
	 private RelativeLayout text_bar_relative_view;
	private FBReaderApp fbReaderApp;
	private ImageView page_bookmark_ribbon;
	private ImageView header_bookmark_ribbon;
	private SeekBar brightness_seekbar;
	private static LinearLayout bottom_linear_view;
	private static RelativeLayout top_relative_view;

	private int myStartBrightness;
	public static boolean isFooterVisible;
	public static boolean isHeaderVisible;
	public static boolean isBookmarked;

	Animation slide_out_to_bottom;
	Animation slide_in_from_bottom;
	Animation slide_out_to_top;
	Animation slide_in_from_top;

	/**
	 * @param context
	 */
	public UiButtonsEventHandler(Context context) {
		super();
		this.context = context;
		myStartBrightness = ZLibrary.Instance().getScreenBrightness();

		brightness_button = (ImageView) ((Activity) context).findViewById(R.id.fb_reader_brightness_button);
		text_button = (ImageView) ((Activity) context).findViewById(R.id.fb_reader_text_button);
		effect_button = (ImageView) ((Activity) context).findViewById(R.id.fb_reader_effect_button);
		orientation_button = (ImageView) ((Activity) context).findViewById(R.id.fb_reader_orientation_button);
		page_scroll_button = (ImageView) ((Activity) context).findViewById(R.id.fb_reader_page_scroll_button);

		page_bookmark_ribbon = (ImageView) ((Activity) context).findViewById(R.id.fb_reader_bookmark_ribbon);
		header_bookmark_ribbon = (ImageView) ((Activity) context).findViewById(R.id.fb_reader_header_bookmark_ribbon);

		brightness_seekbar = (SeekBar) ((Activity) context).findViewById(R.id.fb_reader_brightness_seek_bar);
		brightness_seekbar.setMax(100);
		brightness_seekbar.setProgress(myStartBrightness);
		brightness_seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				ZLibrary.Instance().setScreenBrightness(progress);
			}
		});

		brightness_relative_view = (RelativeLayout) ((Activity) context).findViewById(R.id.reader_brightness_relative_view);
		effect_mode_relative_view = (RelativeLayout) ((Activity) context).findViewById(R.id.reader_page_effect_mode_relative_view);
		orientation_mode_relative_view = (RelativeLayout) ((Activity) context).findViewById(R.id.reader_page_orientation_mode_relative_view);
		text_bar_relative_view = (RelativeLayout) ((Activity) context).findViewById(R.id.reader_page_textbar_raltiveview);
		bottom_linear_view = (LinearLayout) ((Activity) context).findViewById(R.id.reader_bottom_linear_view);
		fbReaderApp = (FBReaderApp) FBReaderApp.Instance();

		isFooterVisible = true;
		isHeaderVisible = true;
		isBookmarked = false;

		slide_out_to_bottom = AnimationUtils.loadAnimation(context, R.anim.slide_out_to_bottom);
		slide_in_from_bottom = AnimationUtils.loadAnimation(context, R.anim.slide_in_from_bottom);
		slide_out_to_top = AnimationUtils.loadAnimation(context, R.anim.slide_out_to_top);
		slide_in_from_top = AnimationUtils.loadAnimation(context, R.anim.slide_in_from_top);

	}

	@Override
	public void onClick(View v) {
		// Log.d(TAG,"onClick Method executed");
		switch (v.getId()) {
		case R.id.fb_reader_brightness_button:
			hidePopups();

			brightness_relative_view.setVisibility(View.VISIBLE);

			break;
		case R.id.fb_reader_text_button:
			hidePopups();
			 text_bar_relative_view.setVisibility(View.VISIBLE);

			break;
		case R.id.fb_reader_effect_button:
			hidePopups();

			effect_mode_relative_view.setVisibility(View.VISIBLE);

			break;
		case R.id.fb_reader_orientation_button:
			hidePopups();

			orientation_mode_relative_view.setVisibility(View.VISIBLE);

			break;
		case R.id.fb_reader_page_scroll_button:
			hidePopups();
			hideFooter();
			fbReaderApp.runAction(ActionCode.SHOW_NAVIGATION);

			break;
		case R.id.fb_reader_action_search_btn:
			fbReaderApp.runAction(ActionCode.SEARCH);
			break;
		case R.id.fb_reader_decrease_font_button:
			fbReaderApp.runAction(ActionCode.DECREASE_FONT);
			break;
		case R.id.fb_reader_increase_font_button:
			fbReaderApp.runAction(ActionCode.INCREASE_FONT);
			break;
		case R.id.fb_reader_bookmark_ribbon:
			fbReaderApp.runAction(ActionCode.TOGGLE_BOOKMARK);
			break;
		case R.id.fb_reader_header_bookmark_ribbon:
			fbReaderApp.runAction(ActionCode.TOGGLE_BOOKMARK);
			break;

		case R.id.fb_reader_action_toc_btn:
			Log.i("toc button clicked","");
			fbReaderApp.runAction(ActionCode.SHOW_TOC);
			break;
		case R.id.fb_reader_action_setting_btn:
			fbReaderApp.runAction(ActionCode.SHOW_PREFERENCES);
			break;
		default:
			break;
		}
	}

	public void showFullScreenBookmark() {

		// bookmark_ribbon.setBackgroundResource(R.drawable.fb_reader_bookmark_ribbon);
		page_bookmark_ribbon.setAnimation(slide_in_from_top);
		page_bookmark_ribbon.setVisibility(View.VISIBLE);
	}

	public void hideFullscreenBookmark() {
		// fb_reader_topbar_imageview.setVisibility(View.VISIBLE);
		page_bookmark_ribbon.startAnimation(slide_out_to_top);
		page_bookmark_ribbon.setVisibility(View.INVISIBLE);
	}

	public void headerRibbonEnable() {
		header_bookmark_ribbon.setBackgroundResource(R.drawable.fb_reader_bookmark_ribbon);
	}

	public void headerRibbonDisable() {
		header_bookmark_ribbon.setBackgroundResource(R.drawable.fb_reader_without_bookmark_ribbon);
	}

	public void hidePopups() {
		brightness_relative_view.setVisibility(View.GONE);
		effect_mode_relative_view.setVisibility(View.GONE);
		 text_bar_relative_view.setVisibility(View.GONE);
		orientation_mode_relative_view.setVisibility(View.GONE);

	}

	public void hideFooter() {
		// TODO Auto-generated method stub

		bottom_linear_view.setVisibility(View.INVISIBLE);
		isFooterVisible = false;
	}
}
