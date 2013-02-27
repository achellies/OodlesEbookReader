package org.geometerplus.android.fbreader;

import org.geometerplus.fbreader.fbreader.FBReaderApp;
import org.geometerplus.zlibrary.core.view.ZLPaintContext;
import org.geometerplus.zlibrary.text.view.ZLTextSelection;

public class drawCursor extends FBAndroidAction{

	drawCursor(FBReader baseActivity, FBReaderApp fbreader) {
		super(baseActivity, fbreader);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run(Object... params) {
		// TODO Auto-generated method stub
		ZLPaintContext context=(ZLPaintContext)params[0];
	
		Integer x= (Integer)params[1];
		Integer y= (Integer)params[2];
		Integer direction= (Integer)params[3];
		
		context.drawImage(x,y,direction,BaseActivity);
		
	}

}

