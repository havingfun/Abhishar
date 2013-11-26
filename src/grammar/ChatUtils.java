/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grammar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 *
 * @author FoUkat
 */
public class ChatUtils {

    /**
     * @param args the command line arguments
     */

    public HashMap ab;
    
    public static String parseInput(String inp)
    {
        String[] arr = inp.split(" ");
        String response = null;
        for(int i=0;i<arr.length;i++)
        {

        }
        return response;
    }

    public static void main(String[] args)throws Exception {
        // TODO code application logic here
        String inp = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(inp.equals("quit")!=true)
        {
                inp = br.readLine();
                String response = null;
                response = parseInput(inp);
                System.out.println(response);
        }

    }

}
