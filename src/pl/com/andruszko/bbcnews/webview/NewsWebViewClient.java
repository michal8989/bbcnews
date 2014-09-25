package pl.com.andruszko.bbcnews.webview;

import pl.com.andruszko.bbcnews.R;
import pl.com.andruszko.bbcnews.Constants.Constants;
import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Class sets the security and behaviour for the webview
 *
 * @author mandruszko
 */

public class NewsWebViewClient extends WebViewClient {

	Context mContext;

	
	public NewsWebViewClient(Context context) {
		this.mContext = context;
	}
	
	// Overriding the URL, preventing to visit other domains
	 @Override
	    public boolean shouldOverrideUrlLoading(WebView wView, String url)
	    {
	     String hostName = Uri.parse(url).getHost();
		     //condition should be more specified 
	     	 if(!hostName.contains(Constants.DOMAIN_PATTERN)) {
		    	 Toast.makeText(this.mContext, this.mContext.getText(R.string.webview_operation_denied), Toast.LENGTH_SHORT).show();
		      return true;
		     }
	     return false;
	    }
	 
	 // intercept URL loading from an IFRAME or src attribute or XmlHttpRequests.
	 @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	 @Override
	    public WebResourceResponse shouldInterceptRequest(final WebView view, String url)
	    {
		 String hostName = Uri.parse(url).getHost();
		// condition should be more specified 
	     if(!hostName.contains(Constants.DOMAIN_PATTERN)) {
	     	return getResourceManually();
	     } else {
	      return super.shouldInterceptRequest(view, url);
	     }
	    }
	 
	    private WebResourceResponse getResourceManually()
	    {
	    	// TODO implement method to download resources manually.	    	
	        // return getUtf8EncodedWebResourceResponse(new StringBufferInputStream("Blah!"));
	        return WebResourceResponse();
	    }

	   private WebResourceResponse WebResourceResponse() {
			return null;
		}

	// When received data is partially or empty   
	public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
		Toast.makeText(this.mContext, this.mContext.getText(R.string.webview_receive_error) + description, Toast.LENGTH_SHORT).show();
	}
	 
}
