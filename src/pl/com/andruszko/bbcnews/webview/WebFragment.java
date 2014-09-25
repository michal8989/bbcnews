package pl.com.andruszko.bbcnews.webview;

import pl.com.andruszko.bbcnews.BaseFragment;
import pl.com.andruszko.bbcnews.R;
import pl.com.andruszko.bbcnews.Constants.Constants;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class WebFragment extends BaseFragment{

	String mLink; 
	String mTitle;
	
	public WebFragment() {
	}	
	
	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onAttach(Activity activity) {
		
		if(getArguments() != null) {
			mLink = getArguments().getString("link");
			mTitle = getArguments().getString("title");
		}
		
		setTitle(mTitle);
		super.onAttach(activity);
	}	

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.fragment_web, container,
				false);

		WebView webview = (WebView) rootView.findViewById(R.id.webView);	
		
		// We check it here second time because if we will switching 
		// Fragment in future (not replacing) onCreate will be not called
		if(getArguments() != null) {
			mLink = getArguments().getString("link");
		}
		 // JS support
		 webview.getSettings().setJavaScriptEnabled(true);
		 // Disabling the local file system access - security
		 webview.getSettings().setAllowFileAccess(false);
		 // Security class
		 webview.setWebViewClient(new NewsWebViewClient(getActivity()));		 
		 
		 // Let's display the progress in the activity title bar, like the
		 // browser app does.
		 final Activity activity = getActivity();
		 webview.setWebChromeClient(new WebChromeClient() {
		   public void onProgressChanged(WebView view, int progress) {
		     // Activities and WebViews measure progress with different scales.
		     // The progress meter will automatically disappear when we reach 100%
		     activity.setProgress(progress * 1000);
		   }
		 });

		if(mLink != null) {
			webview.loadUrl(mLink);
		} else {
			webview.loadUrl(Constants.DEFAULT_WWW);
		}
		 
		 webview.loadUrl(mLink);
		
		return rootView;
	}

}
