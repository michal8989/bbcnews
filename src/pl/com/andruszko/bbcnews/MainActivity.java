package pl.com.andruszko.bbcnews;

import pl.com.andruszko.bbcnews.menu.NavigationDrawerFragment;
import pl.com.andruszko.bbcnews.news.NewsFragment;
import android.content.res.Configuration;
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
				replaceContent(new AboutFragment());					
			} break;
			//app exit
			case 2: { finish(); } break;
			default: {
				replaceContent(new NewsFragment());				
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
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
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
