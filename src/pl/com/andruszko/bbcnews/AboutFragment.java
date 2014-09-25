package pl.com.andruszko.bbcnews;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment displays static info about author, boring...
 *
 * @author mandruszko
 */

public class AboutFragment extends BaseFragment{

	public AboutFragment() {
	}	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}	

	@Override
	public void onAttach(Activity activity) {
		setTitle(R.string.title_about);
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.fragment_about, container,
				false);
		
		return rootView;
	}

}
