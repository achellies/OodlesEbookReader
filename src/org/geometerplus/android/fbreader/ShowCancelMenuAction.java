/*
 * Copyright (C) 2007-2012 Geometer Plus <contact@geometerplus.com>
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

import java.util.List;

import android.content.Intent;
import android.widget.Toast;

import org.geometerplus.android.fbreader.oodlesui.UiButtonsEventHandler;
import org.geometerplus.fbreader.fbreader.ActionCode;
import org.geometerplus.fbreader.fbreader.FBReaderApp;
import org.geometerplus.fbreader.fbreader.FBView;

class ShowCancelMenuAction extends FBAndroidAction {
	UiButtonsEventHandler eventHandler;

	ShowCancelMenuAction(FBReader baseActivity, FBReaderApp fbreader) {
		super(baseActivity, fbreader);
		eventHandler = new UiButtonsEventHandler(baseActivity);
	}

	@Override
	protected void run(Object... params) {

		final FBView fbview = Reader.getTextView();
		if (fbview.getCountOfSelectedWords() > 0) {
			Reader.runAction(ActionCode.SELECTION_CLEAR);
			Reader.runAction(ActionCode.SELECTION_HIDE_PANEL);
		} else {

			if (eventHandler.isFooterVisible || eventHandler.isHeaderVisible) {
				Reader.runAction(ActionCode.TOUCH_ACTIONS);
			} else {
				BaseActivity.finish();
//				Toast.makeText(BaseActivity, "TODO go back to library activity!", Toast.LENGTH_SHORT).show();
			}
		}
	}
}
