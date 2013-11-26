/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qaiiitm;

import java.io.*;
import java.net.*;
import qaiiitm.proxySettings;
/**
 *
 * @author FoUkat
 */
public class NewsProcessor {


    static String google_news = "http://news.google.com/news/feeds?output=rss&q=";



    public static String rawRequest(String task) throws Exception {
        //proxySettings ps = new proxySettings();
        //inputFile = new File("out.flac");
        // Configure proxy ...
        //System.setProperty("http.proxySet", ps.getEnabled());
        //System.setProperty("http.proxyHost", ps.getHost());
        //System.setProperty("http.proxyPort", ps.getPort());
        //System.setProperty("http.proxyType", "4");
        //String proxyUser = ps.getUser(),
         //  proxyPassword = ps.getPass();
        // Open URL ...
        // URL of Remote Script.
        URL url = new URL(google_news + task);
        //URL url = new URL("https://www.google.com");
        // Open New URL connection channel.
        URLConnection urlConn = url.openConnection();

        //proxy user and pass
        //urlConn.setRequestProperty(
          //   "Proxy-Authorization",
            // "Basic " + new sun.misc.BASE64Encoder().encode(
              //    (proxyUser+ ":" + proxyPassword).getBytes()
             //)
        //);


        OutputStream outputStream;
        BufferedReader br;

        BufferedReader in = new BufferedReader (
                              new InputStreamReader (
                                urlConn.getInputStream ()
                              )
                            );

        // Read it ...
        String inputLine;String resp = null;
        while ((inputLine = in.readLine()) != null)
        {
            System.out.println(inputLine);
            resp += inputLine;
        }

        return resp;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws Exception {
        // TODO code application logic here
        String req=null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        req = br.readLine();
        String res = rawRequest(req);
        System.out.println(res);
    }

    
}
