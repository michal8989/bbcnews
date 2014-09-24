package pl.com.andruszko.bbcnews;

import java.util.Date;

import android.graphics.Bitmap;

/**
 * This code encapsulates RSS item.
 * We need Title, Description, publishDate, Image
 *
 * @author mandruszko
 */

public class RssItem {
	
	private int id;
	private String title;
	private String description;
	private Date publishDate;
	private Bitmap image;
	
	//Setters and Getters section
	public void setId(int id) {
        this.id = id;
    }	
	
	public int getId() {
		return this.id;
	}
	
	public void setTitle(String title) {
        this.title = title;
    }
	
	public String getTitle() {
		return this.title;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	public Date getPublishDate() {
		return this.publishDate;
	}
	
	public void setImage(Bitmap image) {
		this.image = image;
	}
	
	public Bitmap getImage() {
		return this.image;
	}
}
