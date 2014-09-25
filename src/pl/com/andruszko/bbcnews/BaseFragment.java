package pl.com.andruszko.bbcnews;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * A base of all fragments containing a basic methods.
 * @author mandruszko
 */
public class BaseFragment extends Fragment {

	// Titile of fragment
	private String mTitle;
	// Holder for Loader MenuItem
	MenuItem mLoaderMenuItem;
	// Variable to set Loader or not
	boolean hasLoader = false;
	
	/**
	* Switch to show loader in actionbar
	* @param void
	*/	
	public void enableLoader(boolean enabled) {
		hasLoader = enabled;
	}
	
	public BaseFragment() {
		setHasOptionsMenu(true);
	}
	
	/**
	* Sets title in ActionBar by String
	* @param String
	*/	
	public void setTitle(String title) {
		this.mTitle = title;
    }

	/**
	* Sets title in ActionBar by Resource Id
	* @param int
	*/		
	public void setTitle(int title) {
		this.mTitle = getResources().getString(title);
    }	

	@Override
	public void onResume() {
		((MainActivity) getActivity()).onSectionAttached(getTitle());
		super.onResume();
	}

	public String getTitle() {
		return this.mTitle;
	}
	
	@SuppressLint("NewApi")
	@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		if(hasLoader) {
			getActivity().getMenuInflater().inflate(R.menu.main, menu);       
        	mLoaderMenuItem = menu.findItem(R.id.action_example);
		}
    }		
	

	@SuppressLint("NewApi")
	public void stopLoader() {
		if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
		    if (mLoaderMenuItem != null) {
		    	mLoaderMenuItem.setActionView(null);
		    }
		}
	}

	@SuppressLint("NewApi")
	public void runLoader() {
		if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {		
		    if (mLoaderMenuItem != null) {
		    	mLoaderMenuItem.setActionView(R.layout.indeterminate_progress_action);
		    }
		}
	}	
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}
}