package pl.com.andruszko.bbcnews.news;

import java.util.List;

import android.content.Context;
import android.os.AsyncTask;

class RssDataAsyncTask extends AsyncTask<String, Void, List<RssItem>> {
	
	Context mContext;
	
	public RssDataAsyncTask() {
	}
	
	public RssDataAsyncTask(Context context) {
		this.mContext = context;
	}
	
	@Override
	protected List<RssItem> doInBackground(String... urls) {

		try {
			// Create RSS reader
			RssReader rssReader = new RssReader(urls[0]);
			
			// Parse RSS, get items
			return rssReader.getItems();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
