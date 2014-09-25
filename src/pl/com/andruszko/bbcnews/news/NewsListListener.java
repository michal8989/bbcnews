package pl.com.andruszko.bbcnews.news;

import java.util.List;

import pl.com.andruszko.bbcnews.MainActivity;
import pl.com.andruszko.bbcnews.webview.WebFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class NewsListListener implements OnItemClickListener {

	// List item's reference
	List<RssItem> listItems;
	// Calling activity reference
	MainActivity mActivity;

	/** We will set those references in our constructor. */
	public NewsListListener(List<RssItem> aListItems, MainActivity anActivity) {
		listItems = aListItems;
		mActivity = anActivity;
	}

	/** Start a browser with url from the rss item. */
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

		WebFragment webFragment = new WebFragment();
		Bundle bundle = new Bundle();
		bundle.putString("link", listItems.get(pos).getLink());
		bundle.putString("title", listItems.get(pos).getTitle());
		webFragment.setArguments(bundle);

		mActivity.replaceContent(webFragment);
	}
}