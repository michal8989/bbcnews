package pl.com.andruszko.bbcnews.news;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * The Class contains a list of RssItems which is being filled while the parser
 * is working
 * 
 * @author mandruszko
 */
public class RssHandler extends DefaultHandler {

	// List of items parsed
	private List<RssItem> rssItems;
	// Used to reference item while parsing
	private RssItem currentItem;
	// Helps to get image Url form params
	String tmpImage = null;
	// Parsing title indicator
	private parserField parsingElement;
	//fix to SAX &amp; chars
	private parserField lastParsingElement;
	// Variable used to fix SAX bug with links to articles
	private String tmpLink;
	
	public RssHandler() {
		rssItems = new ArrayList<RssItem>();
	}

	// Fields to helps parse data
	public static enum parserField {
		link, title, description, pubDate, image
	};
	
	// We have an access method which returns a list of items that are read from
	// the RSS feed. This method will be called when parsing is done.
	public List<RssItem> getItems() {
		return rssItems;
	}

	// The StartElement method creates an empty RssItem object.
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		if ("item".equals(qName)) {
			currentItem = new RssItem();
		} else if ("link".equals(qName)) {
			parsingElement = parserField.link;
		} else if ("title".equals(qName)) {
			parsingElement = parserField.title;
		} else if ("description".equals(qName)) { 
			parsingElement = parserField.description;			
		} else if ("pubDate".equals(qName)) { 
			parsingElement = parserField.pubDate;
		} else if ("media:thumbnail".equals(qName)) { 
			tmpImage = attributes.getValue(2);
			currentItem.setImage(tmpImage);
			parsingElement = parserField.image;
		}

	}

	// The EndElement method adds the current RssItem to the list when a closing item tag.
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		
		lastParsingElement = parsingElement;
		if ("item".equals(qName)) {
			rssItems.add(currentItem);
			currentItem = null;
		} else {
			parsingElement = null;
		}
	}

	// Characters method fills current RssItem object with data when title and
	// link tag content is being processed
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		if(parsingElement != null) { 
			switch(parserField.valueOf(parsingElement.toString())) {
				case link: { 
					// Fix for SAX cutting links on "&"
					if(currentItem != null){
						if(lastParsingElement.equals(parsingElement)) {
							tmpLink = tmpLink+new String(ch, start, length);
						} else if (parsingElement == parserField.link){
							tmpLink = new String(ch, start, length);
						}
						lastParsingElement = parsingElement;
						currentItem.setLink(tmpLink);
					}
				} break;
				case title: { 
					if(currentItem != null){
						currentItem.setTitle(new String(ch, start, length));
					}				
				} break;
				case description: {
					if(currentItem != null){
						currentItem.setDescription(new String(ch, start, length));
					}				
				} break;
				case pubDate: { 
					if(currentItem != null){
						try {
							currentItem.setPublishDate(new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss zzz", Locale.ENGLISH).parse(new String(ch, start, length)));
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}				
				} break;
				case image: { 
					currentItem.setImage(tmpImage);
					tmpImage = null;
				} break;
				default: { 
					
				} break;
			}
		}
	}
}