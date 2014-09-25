package pl.com.andruszko.bbcnews.news;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * AsyncTask to download images in news
 * @author mandruszko
 */
class BitmapDownloaderTask extends AsyncTask<String, Void, Bitmap> {
	// Weak reference to ImageView to easy release this View.
    private final WeakReference<ImageView> imageViewReference;
    RssItem mItem;
    public BitmapDownloaderTask(RssItem item, ImageView imageView) {
        this.imageViewReference = new WeakReference<ImageView>(imageView);
        this.mItem = item;
    }

    @Override
    // Actual download method, run in the task thread
    protected Bitmap doInBackground(String... params) {
    	URL url;
    	Bitmap bmp = null;
		try {
			url = new URL(params[0]);
			bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

        return bmp;
    }

    @Override
    // Once the image is downloaded, associates it to the imageView
    protected void onPostExecute(Bitmap bitmap) {
        if (isCancelled()) {
            bitmap = null;
        }

        if (imageViewReference != null) {
            ImageView imageView = imageViewReference.get();
            mItem.setBitmap(bitmap);
            if (imageView != null && imageView.getTag() == mItem.getTitle()) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}