package pl.com.andruszko.bbcnews.news;

import java.util.List;

import pl.com.andruszko.bbcnews.BaseFragment;
import pl.com.andruszko.bbcnews.MainActivity;
import pl.com.andruszko.bbcnews.R;
import pl.com.andruszko.bbcnews.Constants.Constants;
import pl.com.andruszko.bbcnews.utilities.ConnectionUtility;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

/**
 * A fragment displays an RSS feed
 * @author mandruszko
 */

public class NewsFragment extends BaseFragment{

	private RssDataAsyncTask mAsyncTask;
	
	public NewsFragment() {
		super();
	}	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_example) {
			loadNews(getView());
			return true;
		}

		return super.onOptionsItemSelected(item);	
	}
	
	@Override
	public void onAttach(Activity activity) {
		//enables refresh button in actionbar
		enableLoader(true);
		setTitle(R.string.title_news);
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);		
	}

	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// We load here news
		loadNews(getView());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_news, container,
				false);
	
		return rootView;
	}
	
	private void loadNews(final View rootView) {

		// Checking is Activity is alive
		if(getActivity() == null)
			return;		
		
		if(!ConnectionUtility.isOnline(getActivity())) {
			Toast.makeText(getActivity(), getText(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();
		}
		
		
		// If any task in use try to stop
		if(mAsyncTask != null && mAsyncTask.getStatus() != AsyncTask.Status.FINISHED) {
			mAsyncTask.cancel(true);
		}

		// Creating new async task to get data	
		mAsyncTask = new RssDataAsyncTask() {
			
			
			@Override
			protected void onPreExecute() {
				runLoader();
				super.onPreExecute();
			}			
			
			@Override
			protected void onPostExecute(List<RssItem> result) {
				// Get a ListView from main view
				ListView rssItems = (ListView) rootView.findViewById(R.id.newsListView);

				// Create a list adapter
				RssListAdapter adapter = new RssListAdapter(getActivity(),result);
				// Set list adapter for the ListView
				rssItems.setAdapter(adapter);

				// Set list view item click listener
				rssItems.setOnItemClickListener(new NewsListListener(result, (MainActivity) getActivity()));
				stopLoader();
			}
			
		};
        
		
        // Start download RSS task
		mAsyncTask.execute(Constants.RSS_URL);		
		
	}

}
