package pl.com.andruszko.bbcnews.news;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import pl.com.andruszko.bbcnews.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * News list adapter filled by async task
 * 
 * @author mandruszko
 */
class RssListAdapter extends BaseAdapter {
	List<RssItem> all = new ArrayList<RssItem>();

	private LayoutInflater mInflater;
	Drawable loadIcon;
	Drawable unloadIcon;
	String taskListVerticalTimeInterval;

	public RssListAdapter(Context context, List<RssItem> items) {
		this.mInflater = LayoutInflater.from(context);
		this.all = items;
	}

	@Override
	public int getCount() {
		return (all == null) ? 0 : all.size();
	}

	@Override
	public RssItem getItem(int position) {
		return all.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		NewsListItemViewHolder holder;

		final RssItem item = (RssItem) getItem(position);
		// use of ViewHolder Pattern
		if (convertView == null) {
			convertView = this.mInflater.inflate(R.layout.news_list_item,
					parent, false);

			holder = new NewsListItemViewHolder();
			holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
			holder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
			holder.tvDescription = (TextView) convertView
					.findViewById(R.id.tvDescription);
			holder.ivImage = (ImageView) convertView.findViewById(R.id.ivImage);

			convertView.setTag(holder);
		} else {
			holder = (NewsListItemViewHolder) convertView.getTag();
		}

		holder.ivImage.setImageResource(R.drawable.ic_launcher);
		holder.tvTitle.setText("");
		holder.tvDate.setText("");
		holder.tvDescription.setText("");

		holder.tvTitle.setText(item.getTitle());
		if (item.getPublishDate() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss",
					Locale.ENGLISH);
			holder.tvDate.setText(sdf.format(item.getPublishDate()));
		}
		holder.tvDescription.setText(item.getDescription());

		if (item.getBitmap() != null) {
			holder.ivImage.setImageBitmap(item.getBitmap());
		} else if (item.getImage() != null) {
			holder.ivImage.setTag(item.getTitle());
			BitmapDownloaderTask task = new BitmapDownloaderTask(item,
					holder.ivImage);
			task.execute(item.getImage());
		}

		return (View) convertView;
	}

}