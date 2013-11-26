/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qaiiitm;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FoUkat
 */
public class CodeWriter {
    
    private static String codepath = "/res/data/thinkTemplates/code/";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws Exception {
        // TODO code application logic here
        System.out.println("load_template".contains("template"));
        process("fibonacci");
    }

    public static String refine(String task)
    {
        return task;
    }

    public static void process(String task) throws Exception
    {
                            try {
                                ApplicationControl AC = new ApplicationControl();
                                AC.init();
            Runtime.getRuntime().exec("notepad.exe");
            Thread.sleep(2000);
            String workingDir = System.getProperty("user.dir");
            String finaltask = refine(task);
            File logs = new File(workingDir + codepath + finaltask + ".txt");
            try {
                BufferedReader bc = new BufferedReader(new FileReader(logs));
                String line = null;
                while ((line = bc.readLine()) != null) {
                    System.out.println(line);
                    AC.type(line);
                    AC.abhishar.keyPress(KeyEvent.VK_ENTER);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
                            } catch (IOException ex)
                            {
                                 Logger.getLogger(CodeWriter.class.getName()).log(Level.SEVERE, null, ex);
                            }

    }

}
