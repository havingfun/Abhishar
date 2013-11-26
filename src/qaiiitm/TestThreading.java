/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qaiiitm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FoUkat
 */
public class TestThreading {

    /**
     * @param args the command line arguments
     */
    public static int demo = 0;
    public static String[] existing = {
                    "literature.aiml",
                    "xfind.aiml",
                    "stories.aiml",
                    "sports.aiml",
                    "sex.aiml",
                    "science.aiml",
                    "salutations.aiml",
                    "religion.aiml",
                    "pyschology.aiml",
                    "psychology.aiml",
                    "politics.aiml",
                    "pickup.aiml",
                    "personality.aiml",
                    "numbers.aiml",
                    "music.aiml",
                    "mp6.aiml",
                    "mp5.aiml",
                    "mp4.aiml",
                    "mp3.aiml",
                    "mp2.aiml",
                    "mp1.aiml",
                    "mp0.aiml",
                    "knowledge.aiml",
                    "iu.aiml",
                    "interjection.aiml",
                    "inquiry.aiml",
                    "imponderables.aiml",
                    "humor.aiml",
                    "history.aiml",
                    "gossip.aiml",
                    "geography.aiml",
                    "emotion.aiml",
                    "drugs.aiml",
                    "default.aiml",
                    "date.aiml",
                    "computers.aiml",
                    "bot_profile.aiml",
                    "bot.aiml",
                    "atomic.aiml",
                    "astrology.aiml"};
    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        final Scanner scan = new Scanner(System.in);
        ProcessBuilder pb = new ProcessBuilder("cmd");
        pb.redirectErrorStream();
        final Process process = pb.start();
        OutputStream stdin = process.getOutputStream ();
        InputStream stderr = process.getErrorStream ();
        InputStream stdout = process.getInputStream ();
        

        final BufferedReader reader = new BufferedReader (new InputStreamReader(stdout));
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
        final BufferedReader error = new BufferedReader (new InputStreamReader(stderr));
        Thread T=new Thread(new Runnable() {

            @Override
            public void run() {
                while(true)
                {
                        /*if(demo==0)
                        {
                            for(int i=0;i<existing.length;i++)
                            {
                                try {
                                     writer.write("/load stored_AIML/" + existing[i]+"\n");
                                     writer.flush();
                                } catch (IOException ex) {
                                    Logger.getLogger(TestThreading.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            demo++;
                        }
                        else
                        {
                         *
                         */

                                String input = scan.nextLine();
                                input += "\n";
                                try {
                                        writer.write(input);
                                        writer.flush();
                                    } catch (IOException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                        //}
                  }

            }
        } );
        T.start();

        Thread P=new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        System.out.println("Stdout: " + line);}
                    line = null;
                    while ((line = error.readLine()) != null) {
                        System.out.println("Stdout: " + line);
                    }
                    try {
                        process.waitFor();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TestThreading.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(TestThreading.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } );
        P.start();

    }

}
