package pl.com.andruszko.bbcnews;

import pl.com.andruszko.bbcnews.menu.NavigationDrawerFragment;
import pl.com.andruszko.bbcnews.news.NewsFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;
	/**
	 * Used to show Toast to exit.
	 */
	boolean toastShowed = false; 
	
	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_PROGRESS);
		
		setContentView(R.layout.activity_main);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		ActionBar actionBar = getSupportActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#db4a37")));
		
		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		
	}

	
	@Override
	public void onNavigationDrawerItemSelected(int position) {

		switch(position) {
			case 1: { 				
				replaceContent(new AboutFragment()); // About section				
			} break;
			case 2: { finish(); } break; //app exit
			default: {
				replaceContent(new NewsFragment());	// News section			
			} break;
		}

	}

	// update the main content by replacing fragments
	public void replaceContent(Fragment fragment) {
		
		FragmentManager fragmentManager = getSupportFragmentManager();

		fragmentManager
		.beginTransaction()
		.replace(R.id.container,
				fragment).addToBackStack(null).commit();		
	}
	
	public void onSectionAttached(String title) {	
		mTitle = title;
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onResume() {
		toastShowed = false;
		super.onResume();
	}
	
	@Override
	public void onBackPressed() {
		
		if (mNavigationDrawerFragment.isDrawerOpen()) {
			mNavigationDrawerFragment.closeDrawers();
			return;
		}

		if(getSupportFragmentManager().getBackStackEntryCount() == 1) {
			if(toastShowed == false) {
				Toast.makeText(this, getText(R.string.exit_info), Toast.LENGTH_SHORT).show();
				toastShowed = true;
			} else {
				finish();
			}
		} else if (getSupportFragmentManager().getBackStackEntryCount() > 0){
		    getSupportFragmentManager().popBackStackImmediate();
		    toastShowed = false;
		} 


	}

}
