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

import org.geometerplus.android.fbreader.library.SQLiteBooksDatabase;
import org.geometerplus.fbreader.fbreader.FBReaderApp;
import org.geometerplus.fbreader.fbreader.ScrollingPreferences;
import org.geometerplus.zlibrary.core.options.ZLBooleanOption;
import org.geometerplus.zlibrary.core.options.ZLEnumOption;
import org.geometerplus.zlibrary.core.options.ZLIntegerRangeOption;
import org.geometerplus.zlibrary.core.view.ZLView;
import org.geometerplus.zlibrary.ui.android.library.ZLAndroidApplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.util.Log;

//import com.oodles.model.database.tables.DaoMaster;
//import com.oodles.model.database.tables.DaoMaster.DevOpenHelper;
//import com.oodles.model.database.tables.DaoSession;

public class FBReaderApplication extends ZLAndroidApplication {
	public static final String XMLNS = "http://github.com/ignition/schema";
	private static SQLiteDatabase db;
//	private static DaoMaster daoMaster;
//	private static DaoSession daoSession;

	@Override
	public void onCreate() {
		super.onCreate();
//		DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "Book-db", null);
//		db = helper.getWritableDatabase();
//		daoMaster = new DaoMaster(db);
//		createTables();
//		daoSession = daoMaster.newSession();
//		getSortBy_prefValue();
//		setdefaultScrollPreference();
		// bindService(new Intent(this, LibraryService.class), null,
		// LibraryService.BIND_AUTO_CREATE);
	}

//	public static DaoSession getDaoSession() {
//		return daoSession;
//	}

//	public static void resetData() {
//		try {
//			DaoMaster.dropAllTables(db, true);
//		} catch (Exception e) {
//			Log.e("not able to drop tables---", "" + e);
//		}
//	}

//	public static void createTables() {
//		try {
//			DaoMaster.createAllTables(db, true);
//			com.oodles.download.free.ebooks.reader.Log.i("tsblr cretsate..........................");
//		} catch (Exception e) {
//			Log.e("not able to create tables---", "" + e);
//		}
//
//	}

//	public String getSortBy_prefValue() {
//		SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
//		String sort_by = mPrefs.getString("sort_by", "Last download");
//		com.oodles.download.free.ebooks.reader.Log.i("sort by pref value" + sort_by);
//		return sort_by;
//	}

	public boolean isInternetConnected() {
		final ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
		if (activeNetwork != null && activeNetwork.isConnected())
			return true;
		else
			return false;
	}

	private void setdefaultScrollPreference() {
		SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		String scroll_mode = mPrefs.getString("scroll_mode", "Horizontal scroll");
		setScrollMode(scroll_mode);
		FBReaderApp fbReader = (FBReaderApp) FBReaderApp.Instance();
	}

	/**
	 * 
	 * @param scrollMode
	 */
	private void setScrollMode(String scrollMode) {
		ScrollingPreferences scrollingPreferences = ScrollingPreferences.Instance();
		if (scrollMode.equals("Horizontal scroll")) {
			scrollingPreferences.AnimationOption = new ZLEnumOption<ZLView.Animation>("Scrolling", "Animation", ZLView.Animation.shift);
			scrollingPreferences.HorizontalOption = new ZLBooleanOption("Scrolling", "Horizontal", true);
			scrollingPreferences.AnimationSpeedOption = new ZLIntegerRangeOption("Scrolling", "AnimationSpeed", 1, 10, 4);

		} else if (scrollMode.equals("Vertical scroll")) {
			scrollingPreferences.AnimationOption = new ZLEnumOption<ZLView.Animation>("Scrolling", "Animation", ZLView.Animation.shift);
			scrollingPreferences.HorizontalOption = new ZLBooleanOption("Scrolling", "Horizontal", false);
			scrollingPreferences.AnimationSpeedOption = new ZLIntegerRangeOption("Scrolling", "AnimationSpeed", 1, 10, 4);
		} else if (scrollMode.equals("Curl animation")) {
			scrollingPreferences.AnimationOption = new ZLEnumOption<ZLView.Animation>("Scrolling", "Animation", ZLView.Animation.curl);
			scrollingPreferences.HorizontalOption = new ZLBooleanOption("Scrolling", "Horizontal", true);
			scrollingPreferences.AnimationSpeedOption = new ZLIntegerRangeOption("Scrolling", "AnimationSpeed", 1, 10, 4);
		}

	}

}
