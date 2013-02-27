package org.geometerplus.android.fbreader;

import org.geometerplus.fbreader.fbreader.ActionCode;
import org.geometerplus.fbreader.fbreader.FBReaderApp;

import android.util.Log;

public class BookmarkAddDelete extends FBAndroidAction{

	ManageBookmarks bookmarkManager;
	BookmarkAddDelete(FBReader baseActivity, FBReaderApp fbreader) {
		super(baseActivity, fbreader);
		// TODO Auto-generated constructor stub
	
	}
	
	@Override
	protected void run(Object... params) {
		// TODO Auto-generated method stub
		bookmarkManager=new ManageBookmarks();
		
		if(!bookmarkManager.checkBookmarkForPage())
		{
			bookmarkManager.addBookmark();
			Log.i("add bookmark", "method to add called");
		}					
		else
		{
			bookmarkManager.deleteBookmarkForPage();
			Log.i("add bookmark", "method to add NOT called");
//			Reader.runAction(ActionCode.TOGGLE_BOOKMARK);
		}
		
		
	}

}
