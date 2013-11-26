/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rssreader;

/**
 *
 * @author FoUkat
 */
public class ReadTest {
  public static void main(String[] args) {
    RSSFeedParser parser = new RSSFeedParser("http://www.vogella.com/article.rss");
    Feed feed = parser.readFeed();
    System.out.println(feed);
    for (FeedMessage message : feed.getMessages()) {
      System.out.println(message);

    }

  }
}