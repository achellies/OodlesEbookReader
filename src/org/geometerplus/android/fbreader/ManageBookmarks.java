package org.geometerplus.android.fbreader;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.geometerplus.fbreader.fbreader.FBReaderApp;
import org.geometerplus.fbreader.library.Book;
import org.geometerplus.fbreader.library.Bookmark;
import org.geometerplus.fbreader.library.Library;

import android.util.Log;

public class ManageBookmarks {

	List<Bookmark> AllBooksBookmarks;	
	private final List<Bookmark> myThisBookBookmarks = new LinkedList<Bookmark>();
	
	public boolean checkBookmarkForPage()
	{final FBReaderApp fbreader = (FBReaderApp) FBReaderApp.Instance();
		AllBooksBookmarks = Library.Instance().allBookmarks();
		if (fbreader.Model != null) 
		{
			final long bookId = fbreader.Model.Book.getId();
			for (Bookmark bookmark : AllBooksBookmarks) {
				if (bookmark.getBookId() == bookId) {
					int pageStart = fbreader.BookTextView.getStartCursor().compareTo(bookmark);
					int pageEnd=fbreader.BookTextView.getEndCursor().compareTo(bookmark);
					if(pageStart!=pageEnd)
						return true;
				}
			}			
		}
		return false;		
	}
	
	
	public void deleteBookmarkForPage()
	{
		Bookmark mark=null;
		
		final FBReaderApp fbreader = (FBReaderApp) FBReaderApp.Instance();
		AllBooksBookmarks = Library.Instance().allBookmarks();
		if (fbreader.Model != null) 
		{
			final long bookId = fbreader.Model.Book.getId();
			for (Bookmark bookmark : AllBooksBookmarks) {
				if (bookmark.getBookId() == bookId) {
					int pageStart = fbreader.BookTextView.getStartCursor().compareTo(bookmark);
					int pageEnd=fbreader.BookTextView.getEndCursor().compareTo(bookmark);
					if(pageStart!=pageEnd)
					{
						mark=bookmark;
						break;
						
					}
				}
			}
			
			if(mark!=null)
				deleteBookmark(mark);
		}
	}
	
	
	public List<Bookmark> getThisBookmarks()
	{
		final FBReaderApp fbreader = (FBReaderApp) FBReaderApp.Instance();
		AllBooksBookmarks = Library.Instance().allBookmarks();		
		Collections.sort(AllBooksBookmarks, new Bookmark.ByTimeComparator());
		

		if (fbreader.Model != null) 
		{
			final long bookId = fbreader.Model.Book.getId();
			for (Bookmark bookmark : AllBooksBookmarks) {
				if (bookmark.getBookId() == bookId) {
					myThisBookBookmarks.add(bookmark);
				}
			}
			return myThisBookBookmarks;
		} else 
		{
			return null;
		}
	}
	

	public boolean openBookmark(Bookmark bookmark)
	{
		final FBReaderApp fbreader = (FBReaderApp) FBReaderApp.Instance();
		bookmark.onOpen();
		
		final long bookId = bookmark.getBookId();
		if ((fbreader.Model == null) || (fbreader.Model.Book.getId() != bookId)) {
			final Book book = Book.getById(bookId);
			if (book != null) {
				fbreader.openBook(book, bookmark, null);
				return true;
			} else {
				return false;
			}
		} else {
			fbreader.gotoBookmark(bookmark);
			return true;
		}
	}
	
	public void deleteBookmark(Bookmark bookmark)
	{
		if(bookmark!=null)
		{
			bookmark.delete();
			
			if(myThisBookBookmarks!=null)
				myThisBookBookmarks.remove(bookmark);
			if(AllBooksBookmarks!=null)
				AllBooksBookmarks.remove(bookmark);
		}
	}
	
	
	public void addBookmark()
	{
		final FBReaderApp fbreader = (FBReaderApp) FBReaderApp.Instance();
		final Bookmark bookmark = fbreader.addBookmark(20, true);
		bookmark.save();
		if(bookmark!=null)
			Log.i("bookmark added or not", "added");
		else
			Log.i("bookmark added or not", "not added");
	}
	
	
}
