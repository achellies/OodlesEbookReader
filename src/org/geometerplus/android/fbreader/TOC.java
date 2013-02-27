package org.geometerplus.android.fbreader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.geometerplus.fbreader.bookmodel.TOCTree;
import org.geometerplus.fbreader.fbreader.FBReaderApp;
import org.geometerplus.zlibrary.core.application.ZLApplication;

import android.util.Log;

public class TOC {
	private Map<String, TOCTree> TOCmap;
	private List<String> TOCList;
	private String bookName;

	public TOC() {
		TOCmap = new HashMap<String, TOCTree>();
		TOCList = new ArrayList<String>();

		final FBReaderApp fbreader = (FBReaderApp) ZLApplication.Instance();
		final TOCTree root = fbreader.Model.TOCTree;
		bookName = fbreader.Model.Book.getTitle();

		if (root != null) {
			for (TOCTree tocTree : root.subTrees()) {
				TOCmap.put(tocTree.getText(), tocTree);
				TOCList.add(tocTree.getText());
			}
		} else {
			TOCmap = null;
			TOCList = null;
		}
	}

	public List<String> getTOC() {
		if (TOCmap != null)
			return TOCList;
		else
			return null;
	}

	public String getBookName() {
		if (bookName != null)
			return bookName;
		else
			return null;

	}

	public void openBookText(String item) {
		TOCTree tree = TOCmap.get(item);
		final TOCTree.Reference reference = tree.getReference();
		if (reference != null) {
			final FBReaderApp fbreader = (FBReaderApp) ZLApplication.Instance();
			fbreader.addInvisibleBookmark();
			fbreader.BookTextView.gotoPosition(reference.ParagraphIndex, 0, 0);
			fbreader.showBookTextView();
		}
	}

}
