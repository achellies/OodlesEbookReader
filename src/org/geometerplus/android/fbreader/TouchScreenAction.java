package org.geometerplus.android.fbreader;

import org.geometerplus.android.fbreader.oodlesui.UiButtonsEventHandler;
import org.geometerplus.fbreader.fbreader.FBReaderApp;
import org.geometerplus.zlibrary.ui.android.R;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class TouchScreenAction extends FBAndroidAction{
	UiButtonsEventHandler eventHandler;
	
	private Animation slide_in_from_bottom;
	private Animation slide_in_from_top;
	private Animation slide_out_to_bottom;
	private Animation slide_out_to_top;
	private LinearLayout bottom_linear_view;
	private RelativeLayout top_relative_view;
	
	private ImageView fb_reader_topbar_imageview;
	private ImageView fb_reader_action_setting_btn;
	private ImageView fb_reader_action_search_btn;
	private ImageView fb_reader_action_toc_btn;
	private ImageView fb_reader_bookmark_ribbon;
	
	
	TouchScreenAction(FBReader baseActivity, FBReaderApp fbreader, UiButtonsEventHandler eventHandler) {
		super(baseActivity, fbreader);
		this.eventHandler=eventHandler;
		// TODO Auto-generated constructor stub
		loadAnimation();
		setViews();
	}

	@Override
	protected void run(Object... params) {
		// TODO call method to toggle the header and footer and to hide all
		// other UI
		eventHandler.hidePopups();
		Reader.hideActivePopup();

		if (!eventHandler.isHeaderVisible && !eventHandler.isFooterVisible) {
			bottom_linear_view.setVisibility(View.VISIBLE);
			bottom_linear_view.startAnimation(slide_in_from_bottom);

			fb_reader_topbar_imageview.setVisibility(View.VISIBLE);
			fb_reader_topbar_imageview.startAnimation(slide_in_from_top);

			fb_reader_action_setting_btn.setVisibility(View.VISIBLE);
			fb_reader_action_setting_btn.startAnimation(slide_in_from_top);

			fb_reader_action_search_btn.setVisibility(View.VISIBLE);
			fb_reader_action_search_btn.startAnimation(slide_in_from_top);

			fb_reader_action_toc_btn.setVisibility(View.VISIBLE);
			fb_reader_action_toc_btn.startAnimation(slide_in_from_top);

			fb_reader_bookmark_ribbon.setVisibility(View.VISIBLE);
			fb_reader_bookmark_ribbon.startAnimation(slide_in_from_top);			
			
			eventHandler.isFooterVisible=true;
			eventHandler.isHeaderVisible=true;			

		} else {
			
			bottom_linear_view.setVisibility(View.VISIBLE);
			bottom_linear_view.startAnimation(slide_out_to_bottom);

			fb_reader_topbar_imageview.setVisibility(View.VISIBLE);
			fb_reader_topbar_imageview.startAnimation(slide_out_to_top);

			fb_reader_action_setting_btn.setVisibility(View.VISIBLE);
			fb_reader_action_setting_btn.startAnimation(slide_out_to_top);

			fb_reader_action_search_btn.setVisibility(View.VISIBLE);
			fb_reader_action_search_btn.startAnimation(slide_out_to_top);

			fb_reader_action_toc_btn.setVisibility(View.VISIBLE);
			fb_reader_action_toc_btn.startAnimation(slide_out_to_top);

			fb_reader_bookmark_ribbon.setVisibility(View.VISIBLE);
			fb_reader_bookmark_ribbon.startAnimation(slide_out_to_top);

			bottom_linear_view.setVisibility(View.INVISIBLE);
			fb_reader_topbar_imageview.setVisibility(View.GONE);
			fb_reader_action_setting_btn.setVisibility(View.INVISIBLE);
			fb_reader_action_search_btn.setVisibility(View.INVISIBLE);
			fb_reader_action_toc_btn.setVisibility(View.INVISIBLE);
			fb_reader_bookmark_ribbon.setVisibility(View.INVISIBLE);
			
			eventHandler.isFooterVisible=false;
			eventHandler.isHeaderVisible=false;
			
		}

	}

	private void setViews() {
		// Log.d(TAG,"setViews Method executed");
		bottom_linear_view = (LinearLayout) BaseActivity.findViewById(R.id.reader_bottom_linear_view);
		fb_reader_topbar_imageview = (ImageView) BaseActivity.findViewById(R.id.fb_reader_topbar_imageview);
		fb_reader_action_setting_btn = (ImageView) BaseActivity.findViewById(R.id.fb_reader_action_setting_btn);
		fb_reader_action_search_btn = (ImageView) BaseActivity.findViewById(R.id.fb_reader_action_search_btn);
		fb_reader_action_toc_btn = (ImageView) BaseActivity.findViewById(R.id.fb_reader_action_toc_btn);
		fb_reader_bookmark_ribbon = (ImageView) BaseActivity.findViewById(R.id.fb_reader_header_bookmark_ribbon);

	}

	private void loadAnimation() {
		slide_in_from_bottom = AnimationUtils.loadAnimation(BaseActivity, R.anim.slide_in_from_bottom);
		slide_in_from_top = AnimationUtils.loadAnimation(BaseActivity, R.anim.slide_in_from_top);
		slide_out_to_bottom = AnimationUtils.loadAnimation(BaseActivity, R.anim.slide_out_to_bottom);
		slide_out_to_top = AnimationUtils.loadAnimation(BaseActivity, R.anim.slide_out_to_top);
	}

}
