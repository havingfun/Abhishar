/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qaiiitm;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author FoUkat
 */
public class netCheck {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // Configure proxy ...
        proxySettings ps = new proxySettings();
        System.setProperty("http.proxySet", ps.getEnabled());
        System.setProperty("http.proxyHost", ps.getHost());
        System.setProperty("http.proxyPort", ps.getPort());
        System.setProperty("http.proxyType", "4");
        String proxyUser = ps.getUser(),
           proxyPassword = ps.getPass();

        // Open URL ...
        // URL of Remote Script.
        URL url = new URL("http://weannie.pannous.com/api?input=when+is+daylight+savings+time+in+the+us&locale=en_US&login=pandorabots&ip=169.254.178.212&botid=0&key=CKNgaaVLvNcLhDupiJ1R8vtPzHzWc8mhIQDFSYWj&exclude=Dialogues,ChatBot&out=json");
        //URL url = new URL("https://www.google.com");
        // Open New URL connection channel.
        URLConnection urlConn = url.openConnection();

        //proxy user and pass
        urlConn.setRequestProperty(
             "Proxy-Authorization",
             "Basic " + new sun.misc.BASE64Encoder().encode(
                  (proxyUser + ":" + proxyPassword).getBytes()
             )
        );

        BufferedReader in = new BufferedReader (
                              new InputStreamReader (
                                urlConn.getInputStream ()
                              )
                            );

        // Read it ...
        // Read it ...
        String inputLine;String resp = null;
        while ((inputLine = in.readLine()) != null)
        {
            System.out.println(inputLine);
            resp += inputLine;
        }
        jsonParser(resp);

        /*OutputStream outputStream;
        BufferedReader br;

        // we want to do output.
        /*urlConn.setDoOutput(true);


        // No caching
        urlConn.setUseCaches(false);

        // Specify the header content type.
        urlConn.setRequestProperty("Content-Type", "audio/x-flac; rate=16000");

        // Send POST output.
        outputStream = urlConn.getOutputStream();
        System.out.println(outputStream);

        FileInputStream fileInputStream = new FileInputStream(new File("out.flac"));

        byte[] buffer = new byte[256];

        while ((fileInputStream.read(buffer, 0, 256)) != -1) {
            outputStream.write(buffer, 0, 256);
        }

        fileInputStream.close();
        outputStream.close();

         *
         */
        // Get response data.
        /*br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

        String response = br.readLine();

        br.close();
        System.out.println(response);*/
    }

    public static void jsonParser(String page) throws Exception
    {
        String imgRef="";
        JSONArray outputJson = new JSONObject(page).getJSONArray("output");
        System.out.println(outputJson.getString(0));
        String text = null;
            if (outputJson.length() == 0) {
                    text = "FAILED";

                }
                else {
                    JSONObject firstHandler = outputJson.getJSONObject(0);
                    JSONObject actions = firstHandler.getJSONObject("actions");
                    if (actions.has("reminder")) {
                        Object obj = actions.get("reminder");
                        if (obj instanceof JSONObject) {
                            JSONObject sObj = (JSONObject) obj;
                            String date = sObj.getString("date");
                            date = date.substring(0, "2012-10-24T14:32".length());
                            //System.out.println("date="+date);
                            String duration = sObj.getString("duration");
                            //System.out.println("duration="+duration);

                            Pattern datePattern = Pattern.compile("(.*)-(.*)-(.*)T(.*):(.*)");
                            Matcher m = datePattern.matcher(date);
                            String year="", month="", day="", hour="", minute="";
                            if (m.matches())  {
                                year = m.group(1);
                                month = String.valueOf(Integer.parseInt(m.group(2))-1);
                                day = m.group(3);

                                hour = m.group(4);
                                minute = m.group(5);
                                text =  "<year>"+year+"</year>" +
                                        "<month>"+month+"</month>" +
                                        "<day>"+day+"</day>" +
                                        "<hour>"+hour+"</hour>" +
                                        "<minute>"+minute+"</minute>" +
                                        "<duration>"+duration+"</duration>";

                            }
                            else text = "FAILED";
                        }
                    }
                    else if (actions.has("say") ) {
                        Object obj = actions.get("say");
                        if (obj instanceof JSONObject) {
                            JSONObject sObj = (JSONObject) obj;
                            text = sObj.getString("text");
                            if (sObj.has("moreText")) {
                            JSONArray arr = sObj.getJSONArray("moreText");
                            for (int i = 0; i < arr.length(); i++) {
                                text += " " + arr.getString(i);
                            }
                            }
                        } else {
                            text = obj.toString();
                        }
                    }
                    if (actions.has("show") && !text.contains("Wolfram")
                            && actions.getJSONObject("show").has("images")) {
                        JSONArray arr = actions.getJSONObject("show").getJSONArray(
                                "images");
                        int i = (int)(arr.length() * Math.random());
                        //for (int j = 0; j < arr.length(); j++) System.out.println(arr.getString(j));
                        imgRef = arr.getString(i);
                        if (imgRef.startsWith("//")) imgRef = "http:"+imgRef;
                        imgRef = "<a href=\""+imgRef+"\"><img src=\""+imgRef+"\"/></a>";
                        //System.out.println("IMAGE REF="+imgRef);

                    }
                }
                
    }

}
