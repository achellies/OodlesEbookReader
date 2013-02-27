package org.geometerplus.android.fbreader;

import org.geometerplus.fbreader.fbreader.FBReaderApp;
import org.geometerplus.zlibrary.core.filesystem.ZLFile;

public class OpenBook extends FBAndroidAction{
	
	

	public OpenBook(FBReader baseActivity, FBReaderApp fbreader) {
		super(baseActivity, fbreader);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run(Object... params) {
		// TODO Auto-generated method stub
		FBReaderApp fbreader=(FBReaderApp)FBReaderApp.Instance();
		fbreader.openFile(ZLFile.createFileByPath("/storage/emulated/0/docs/Android Programming Collection/Beginning Android Web Apps Development/Beginning Android Web Apps Development.epub"), null);
		
	}

}
