/*
 * Copyright (C) 2009-2012 Geometer Plus <contact@geometerplus.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */

package org.geometerplus.android.fbreader;

import org.geometerplus.fbreader.bookmodel.TOCTree;
import org.geometerplus.fbreader.fbreader.FBReaderApp;
import org.geometerplus.zlibrary.text.view.ZLTextView;
import org.geometerplus.zlibrary.ui.android.R;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

final class NavigationPopup extends PopupPanel {
	final static String ID = "NavigationPopup";

	private volatile boolean myIsInProgress;

	private LinearLayout bottom_linear_view;

	private ImageView page_scroll_button;

	NavigationPopup(FBReaderApp fbReader) {
		super(fbReader);
	}

	public void runNavigation() {
		if (myWindow == null || myWindow.getVisibility() == View.GONE) {
			myIsInProgress = false;
			initPosition();
			Application.showPopup(ID);
		}
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	protected void show_() {
		super.show_();
		if (myWindow != null) {
			setupNavigation(myWindow);
		}
	}

	@Override
	protected void update() {
		if (!myIsInProgress && myWindow != null) {
			setupNavigation(myWindow);
		}
	}

	@Override
	public void createControlPanel(FBReader activity, RelativeLayout root) {
		if (myWindow != null && activity == myWindow.getActivity()) {
			return;
		}

		myWindow = new PopupWindow(activity, root, PopupWindow.Location.Bottom, true);

		bottom_linear_view = (LinearLayout) activity.findViewById(R.id.reader_bottom_linear_view);// added
		final View layout = activity.getLayoutInflater().inflate(R.layout.navigate, myWindow, false);
		page_scroll_button = (ImageView) activity.findViewById(R.id.fb_reader_page_scroll_button);// added
		final SeekBar slider = (SeekBar) layout.findViewById(R.id.book_position_slider);
		final TextView text = (TextView) layout.findViewById(R.id.book_position_text);
		

		slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			private void gotoPage(int page) {
				final ZLTextView view = getReader().getTextView();
				if (page == 1) {
					view.gotoHome();
				} else {
					view.gotoPage(page);
				}
				getReader().getViewWidget().reset();
				getReader().getViewWidget().repaint();
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
				myIsInProgress = false;
				storePosition();
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				myIsInProgress = true;
			}

			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				if (fromUser) {
					final int page = progress + 1;
					final int pagesNumber = seekBar.getMax() + 1;
					text.setText(makeProgressText(page, pagesNumber));
					gotoPage(page);
					
				}
			}
		});

	/*	final Button btnOk = (Button) layout.findViewById(android.R.id.button1);
		final Button btnCancel = (Button) layout.findViewById(android.R.id.button3);
		View.OnClickListener listener = new View.OnClickListener() {
			public void onClick(View v) {
				final ZLTextWordCursor position = StartPosition;
				if (v == btnCancel && position != null) {
					getReader().getTextView().gotoPosition(position);
					bottom_linear_view.setVisibility(View.VISIBLE);// added
					page_scroll_button.setBackgroundResource(R.drawable.fb_reader_strechbutton_normal);// added
				} else if (v == btnOk) {
					storePosition();
					bottom_linear_view.setVisibility(View.VISIBLE);// added
					page_scroll_button.setBackgroundResource(R.drawable.fb_reader_strechbutton_normal);// added
				}
				StartPosition = null;
				Application.hideActivePopup();
				getReader().getViewWidget().reset();
				getReader().getViewWidget().repaint();
			}
		};
		btnOk.setOnClickListener(listener);
		btnCancel.setOnClickListener(listener);*/
		/*final ZLResource buttonResource = ZLResource.resource("dialog").getResource("button");
		btnOk.setText(buttonResource.getResource("ok").getValue());
		btnCancel.setText(buttonResource.getResource("cancel").getValue());*/

		myWindow.addView(layout);
	}

	private void setupNavigation(PopupWindow panel) {
		final SeekBar slider = (SeekBar) panel.findViewById(R.id.book_position_slider);
		final TextView text = (TextView) panel.findViewById(R.id.book_position_text);

		final ZLTextView textView = getReader().getTextView();
		final ZLTextView.PagePosition pagePosition = textView.pagePosition();

		if (slider.getMax() != pagePosition.Total - 1 || slider.getProgress() != pagePosition.Current - 1) {
			slider.setMax(pagePosition.Total - 1);
			slider.setProgress(pagePosition.Current - 1);
			text.setText(makeProgressText(pagePosition.Current, pagePosition.Total));
		}
	}

	private String makeProgressText(int page, int pagesNumber) {
		final StringBuilder builder = new StringBuilder();
		builder.append(page);
		builder.append("/");
		builder.append(pagesNumber);
		final TOCTree tocElement = getReader().getCurrentTOCElement();
		if (tocElement != null) {
			builder.append("  ");
			builder.append(tocElement.getText());
		}
		return builder.toString();
	}
}
