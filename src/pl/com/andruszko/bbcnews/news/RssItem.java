package pl.com.andruszko.bbcnews.news;

import java.util.Date;

import android.graphics.Bitmap;

/**
 * This code encapsulates RSS item.
 * We need Title, Description, publishDate, Bitmap (to cache), Image and link
 *
 * @author mandruszko
 */

public class RssItem {
	
	private String link;
	private String title;
	private String description;
	private Date pubDate;
	private Bitmap bitmap = null;
	private String image = null;
	
	//Setters and Getters section	
	public void setLink(String link) {
        this.link = link;
    }
	
	public String getLink() {
		return this.link;
	}
	
	public void setTitle(String title) {
        this.title = title;
    }
	
	public String getTitle() {
		return this.title;
	}
	
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
	public Bitmap getBitmap() {
		return this.bitmap;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setPublishDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	
	public Date getPublishDate() {
		return this.pubDate;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getImage() {
		return this.image;
	}
}
