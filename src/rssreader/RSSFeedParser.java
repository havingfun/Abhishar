/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rssreader;

/**
 *
 * @author FoUkat
 */
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import qaiiitm.proxySettings;




 public class RSSFeedParser {
  static final String TITLE = "title";
  static final String DESCRIPTION = "description";
  static final String CHANNEL = "channel";
  static final String LANGUAGE = "language";
  static final String COPYRIGHT = "copyright";
  static final String LINK = "link";
  static final String AUTHOR = "author";
  static final String ITEM = "item";
  static final String PUB_DATE = "pubDate";
  static final String GUID = "guid";

  URL url;
  URLConnection urlConn = null;

  public RSSFeedParser(String feedUrl) {

      try {
      this.url = new URL(feedUrl);
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  public Feed readFeed(InputStream in) {
    Feed feed = null;
    try {
      boolean isFeedHeader = true;
      // Set header values intial to the empty string
      String description = "";
      String title = "";
      String link = "";
      String language = "";
      String copyright = "";
      String author = "";
      String pubdate = "";
      String guid = "";

      // First create a new XMLInputFactory
      XMLInputFactory inputFactory = XMLInputFactory.newInstance();
      // Setup a new eventReader

      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
      // Read the XML document
      while (eventReader.hasNext()) {
        XMLEvent event = eventReader.nextEvent();
        if (event.isStartElement()) {
          String localPart = event.asStartElement().getName()
              .getLocalPart();
          int check=0;
          if(localPart.equals("ITEM"))check = 0;
          else if(localPart.equals("TITLE"))check = 1;
          else if(localPart.equals("DESCRIPTION"))check = 2;
          else if(localPart.equals("LINK"))check = 3;
          else if(localPart.equals("GUID"))check = 4;
          else if(localPart.equals("LANGUAGE"))check = 5;
          else if(localPart.equals("AUTHOR"))check = 6;
          else if(localPart.equals("PUB_DATE"))check = 7;
          else if(localPart.equals("COPYRIGHT"))check = 8;


          switch (check) {
          case 0:
            if (isFeedHeader) {
              isFeedHeader = false;
              feed = new Feed(title, link, description, language,
                  copyright, pubdate);
            }
            event = eventReader.nextEvent();
            break;
          case 1:
            title = getCharacterData(event, eventReader);
            break;
          case 2:
            description = getCharacterData(event, eventReader);
            break;
          case 3:
            link = getCharacterData(event, eventReader);
            break;
          case 4:
            guid = getCharacterData(event, eventReader);
            break;
          case 5:
            language = getCharacterData(event, eventReader);
            break;
          case 6:
            author = getCharacterData(event, eventReader);
            break;
          case 7:
            pubdate = getCharacterData(event, eventReader);
            break;
          case 8:
            copyright = getCharacterData(event, eventReader);
            break;
          }
        } else if (event.isEndElement()) {
          if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
            FeedMessage message = new FeedMessage();
            message.setAuthor(author);
            message.setDescription(description);
            message.setGuid(guid);
            message.setLink(link);
            message.setTitle(title);
            feed.getMessages().add(message);
            event = eventReader.nextEvent();
            continue;
          }
        }
      }
    } catch (XMLStreamException e) {
      throw new RuntimeException(e);
    }
    return feed;
  }



  public Feed readFeed() {
    Feed feed = null;
    try {
      boolean isFeedHeader = true;
      // Set header values intial to the empty string
      String description = "";
      String title = "";
      String link = "";
      String language = "";
      String copyright = "";
      String author = "";
      String pubdate = "";
      String guid = "";

      // First create a new XMLInputFactory
      XMLInputFactory inputFactory = XMLInputFactory.newInstance();
      // Setup a new eventReader
      InputStream in = read();
      
      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
      // Read the XML document
      while (eventReader.hasNext()) {
        XMLEvent event = eventReader.nextEvent();
        if (event.isStartElement()) {
          String localPart = event.asStartElement().getName()
              .getLocalPart();
          int check=0;
          if(localPart.equals("ITEM"))check = 0;
          else if(localPart.equals("TITLE"))check = 1;
          else if(localPart.equals("DESCRIPTION"))check = 2;
          else if(localPart.equals("LINK"))check = 3;
          else if(localPart.equals("GUID"))check = 4;
          else if(localPart.equals("LANGUAGE"))check = 5;
          else if(localPart.equals("AUTHOR"))check = 6;
          else if(localPart.equals("PUB_DATE"))check = 7;
          else if(localPart.equals("COPYRIGHT"))check = 8;


          switch (check) {
          case 0:
            if (isFeedHeader) {
              isFeedHeader = false;
              feed = new Feed(title, link, description, language,
                  copyright, pubdate);
            }
            event = eventReader.nextEvent();
            break;
          case 1:
            title = getCharacterData(event, eventReader);
            break;
          case 2:
            description = getCharacterData(event, eventReader);
            break;
          case 3:
            link = getCharacterData(event, eventReader);
            break;
          case 4:
            guid = getCharacterData(event, eventReader);
            break;
          case 5:
            language = getCharacterData(event, eventReader);
            break;
          case 6:
            author = getCharacterData(event, eventReader);
            break;
          case 7:
            pubdate = getCharacterData(event, eventReader);
            break;
          case 8:
            copyright = getCharacterData(event, eventReader);
            break;
          }
        } else if (event.isEndElement()) {
          if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
            FeedMessage message = new FeedMessage();
            message.setAuthor(author);
            message.setDescription(description);
            message.setGuid(guid);
            message.setLink(link);
            message.setTitle(title);
            feed.getMessages().add(message);
            event = eventReader.nextEvent();
            continue;
          }
        }
      }
    } catch (XMLStreamException e) {
      throw new RuntimeException(e);
    }
    return feed;
  }

  private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
      throws XMLStreamException {
    String result = "";
    event = eventReader.nextEvent();
    if (event instanceof Characters) {
      result = event.asCharacters().getData();
    }
    return result;
  }

  private InputStream read() {
    try {
      return url.openStream();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}