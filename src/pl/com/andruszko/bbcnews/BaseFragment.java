package pl.com.andruszko.bbcnews;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class BaseFragment extends Fragment {

	private String mTitle;
	
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
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}
}