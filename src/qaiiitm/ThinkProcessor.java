/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qaiiitm;

import java.awt.event.KeyEvent;

/**
 *
 * @author FoUkat
 */
public class ThinkProcessor {


    public String Categories[] = {"maths","system","coding","movement","files"}; // All think Categories and their keywords. If kwywords doesn't match its chat mode.
    public String maths_key[] = {"ADD","MULTIPLY","SUBTRACT","DIVIDE","STORE"};
    public String system_key[] = {"open","close","type","erase","qa_system"};
    public String coding_key[] = {"fibonacci","variables","algorithm","template","library","datatype","code_template","wap"};
    public String movement_key[] = {"move","walk","push","pull","pick","identify","register","recognize"};
    public String files_key[] = {"cut","copy","paste","delete","enter"};

    
    public static boolean isType = false;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    ApplicationControl abhi = new ApplicationControl();
    public void load () throws Exception
    {
        abhi.init();
    }
    public void process(String task) throws Exception
    {
                task = task.toLowerCase();
                String workingDir = System.getProperty("usr.dir");
                String category = categorizeTask(task);
                if(category.equals("coding"))
                {
                    System.out.println(task);
                    if(task.contains("wap"))
                    {
                        task = task.replace("wap", "");
                        task = task.trim();
                    }
                    CodeWriter CW = new CodeWriter();
                    CW.process(task);                
                }
                else if(category.equals("system"))
                {
                    if(task.contains("open"))
                    {
                        task = task.replace("open","");
                        task = task.trim();
                        if(task.equals("firefox"))
                        {
                            Runtime.getRuntime().exec("firefox.exe");
                        }
                        else if(task.equals("notepad"))
                        {
                            Runtime.getRuntime().exec("notepad.exe");
                        }
                        else if(task.equals("compiler"))
                        {
                            Runtime.getRuntime().exec("devcpp.exe");
                        }
                    }
                    else if(task.contains("close"))
                    {
                           ApplicationControl AC = new ApplicationControl();
                           AC.init();
                           AC.abhishar.keyPress(KeyEvent.VK_ALT);
                           AC.abhishar.keyPress(KeyEvent.VK_F4);
                           AC.abhishar.keyRelease(KeyEvent.VK_ALT);

                    }
                    else if(task.contains("qa_system"))
                    {
                        QuestionAnswering QA = new QuestionAnswering();
                        QA.setVisible(true);
                    }
                }
                String Response = null;

                isType = false;
                if(task.equals("Start Typing"))
                {
                    isType = true;
                    return;
                }
                if(task.equals("End Typing"))
                {
                    isType = false;
                    return;
                }

                if(isType==true)
                {
                    abhi.type(task);

                }
                System.setProperty("usr.dir",workingDir);
    }

    public String categorizeTask(String task)
    {
        String Category = null;
        Category = matchKeywords(task);
        return Category;
    }

    public String matchKeywords(String task)
    {
        String task_words[] = task.split(" ");
        int match = -1;
        for(int i=0;i<maths_key.length;i++)
        {
            for(int j=0;j<task_words.length;j++)
            {
                if(task_words[j].contains(maths_key[i]))
                    return "maths";
            }
        }
        for(int i=0;i<movement_key.length;i++)
        {
            for(int j=0;j<task_words.length;j++)
            {
                if(task_words[j].contains(movement_key[i]))
                    return "movement";
            }
        }
        for(int i=0;i<files_key.length;i++)
        {
            for(int j=0;j<task_words.length;j++)
            {
                if(task_words[j].contains(files_key[i]))
                    return "files";
            }
        }
        for(int i=0;i<coding_key.length;i++)
        {
            for(int j=0;j<task_words.length;j++)
            {
                if(task_words[j].contains(coding_key[i]))
                    return "coding";
            }
        }
        for(int i=0;i<system_key.length;i++)
        {
            for(int j=0;j<task_words.length;j++)
            {
                if(task_words[j].contains(system_key[i]))
                    return "system";
            }
        }
        return "chat";
    }


}
