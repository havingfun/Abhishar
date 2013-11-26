/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qaiiitm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.util.calendar.Gregorian;

/**
 *
 * @author FoUkat
 */
public class NotesProcessor {

    public static HashMap notes = new HashMap();
    private static String contentPath = "/res/data/savedcontent/";
    private static String varPath = "/res/data/variables/";
    private static String logPath = "/res/data/logs/";
    private static Calendar calendar  = new GregorianCalendar();
    private static Date date = new Date();
    public static HashMap months = new HashMap();
    public static String message;
    public static String fetchedVariable="";
    public static String fetchedContent="";
    public static String fetchedLog="";
    public static String todayLog = "";
    public static String result = "";
    public static void init()
    {
          months.put("jan",new Integer(1));months.put("january",new Integer(1));
          months.put("feb",new Integer(2));months.put("february",new Integer(2));
          months.put("mar",new Integer(3));months.put("march",new Integer(3));
          months.put("apr",new Integer(4));months.put("april",new Integer(4));
          months.put("may",new Integer(5));months.put("jun",new Integer(6));months.put("june",new Integer(6));
          months.put("jul",new Integer(7));months.put("july",new Integer(7));
          months.put("aug",new Integer(8));months.put("august",new Integer(8));
          months.put("sep",new Integer(9));months.put("sept",new Integer(9));months.put("september",new Integer(9));
          months.put("oct",new Integer(10));months.put("october",new Integer(10));
          months.put("nov",new Integer(11));months.put("novem",new Integer(11));months.put("november",new Integer(11));
          months.put("dec",new Integer(12));months.put("december",new Integer(12));

    }

    public static int[] findDate(String predicate)
    {
        int dates[] = new int[6];
        int curyear = date.getYear() + 1900;
        int curmonth = date.getMonth() + 1;
        int curdate = date.getDate();
        int curhour = date.getHours();
        int curminute = date.getMinutes();
        int curseconds = date.getSeconds();
        
        // Finding Year from predicate
        int cury=-1;
        for(int i=0;i<predicate.length()-3;i++)
        {
            if(i!=0)
            {
                if(predicate.charAt(i-1)==' ' && predicate.charAt(i)>='0' && predicate.charAt(i)<='9'
                        && predicate.charAt(i+1)>='0' && predicate.charAt(i+1)<='9'
                        && predicate.charAt(i+2)>='0' && predicate.charAt(i+2)<='9'
                        && predicate.charAt(i+3)>='0' && predicate.charAt(i+3)<='9'
                        )
                {
                    String cur = "";
                    cur+=predicate.charAt(i);
                    cur+=predicate.charAt(i+1);
                    cur+=predicate.charAt(i+2);
                    cur+=predicate.charAt(i+3);
                    System.out.println(cur);
                    predicate = predicate.replaceFirst(cur,"");

                    cury = Integer.valueOf(cur);
                    break;
                }
            }
        }
            if(predicate.contains("this year"))
            {
                cury = date.getYear() + 1900;
                predicate = predicate.replaceFirst("this year", "");
            }
            else if(predicate.contains("next year"))
            {
                    cury = date.getYear() + 1900 + 1;
                    predicate = predicate.replaceFirst("next year", "");
            }
            else if(predicate.contains("years"))
            {
                String parts[] = predicate.split(" ");
                for(int j=0;j<parts.length;j++)
                    if(parts[j].equals("years"))
                    {
                        cury = date.getYear() + 1900 + Integer.valueOf(parts[j - 1]);
                        predicate = predicate.replaceFirst(parts[j],"");
                        predicate = predicate.replaceFirst(parts[j-2],"");
                        predicate = predicate.replaceFirst(parts[j - 1],"");
                        break;
                    }
            }

        if(cury!=-1)
                curyear = cury;

        // finding month from predicate

        int curm = -1;
        String mmonths[] = new String[25];
        mmonths[1]= "jan"; mmonths[0]="january";mmonths[3]="feb";mmonths[2]="february";mmonths[4]="march";mmonths[5]="mar";mmonths[7]="apr";
        mmonths[6]= "april"; mmonths[8]="may";mmonths[10]="jun";mmonths[9]="june";mmonths[11]="july";mmonths[12]="jul";mmonths[14]="aug";
        mmonths[13]= "august"; mmonths[17]="sep";mmonths[16]="sept";mmonths[15]="september";mmonths[19]="oct";mmonths[18]="october";
        mmonths[22]="nov";
        mmonths[21]= "novem"; mmonths[20]="november";mmonths[24]="dec";mmonths[23]="december";
        for(int i=0;i<25;i++)
        {
            if(predicate.contains(mmonths[i]))
            {
                curm = (Integer)months.get(mmonths[i]);
                predicate = predicate.replaceFirst(mmonths[i],"");
                break;
            }
        }

        if(predicate.contains("this month"))
        {
            curm = date.getMonth() + 1;
            predicate = predicate.replaceFirst("this month","");
        }
           else if(predicate.contains("next month"))
        {
            curm = date.getMonth() + 2;
            predicate = predicate.replaceFirst("next month","");
        }
           else if(predicate.contains("months"))
            {
                String parts[] = predicate.split(" ");
                for(int j=0;j<parts.length;j++)
                    if(parts[j].equals("months"))
                    {
                        curm = date.getMonth() +  Integer.valueOf(parts[j - 1])+1;
                        predicate = predicate.replaceFirst(parts[j],"");
                        predicate = predicate.replaceFirst(parts[j-2],"");
                        predicate = predicate.replaceFirst(parts[j - 1],"");
                        break;
                    }
            }
            if(curm!=-1)
            {
                curyear+= curm/13;
                curmonth = curm%12;
                if(curmonth==0)curmonth = 12;
            }
        // finding today's date
        int curd = -1;
        if(predicate.contains("today") || predicate.contains("tonight"))
        {
                predicate = predicate.replaceFirst("today", "");
                predicate = predicate.replaceFirst("tonight", "");
                curd = date.getDate();
        }
        else if(predicate.contains("tommorrow") || predicate.contains("tomorrow") || predicate.contains("next day"))
        {
            predicate = predicate.replaceFirst("tommorrow", "");
            predicate = predicate.replaceFirst("tomorrow", "");
            predicate = predicate.replaceFirst("next day", "");
            curd = date.getDate()+1;
            if(curd>28 && curmonth==2)
            {
                curmonth = 3;
                curd = 1;
            }
            else if(curd > 30 && (curmonth == 4 || curmonth == 6 || curmonth == 9 || curmonth == 11))
            {
                curd  = 1;curmonth++;
            }
            else if(curd>31)
            {
                curd=1;
                curmonth++;
                if(curmonth==13)
                        curmonth=1;
            }
        }
        else if(predicate.contains("yesterday"))
        {
            predicate = predicate.replaceFirst("yesterday", "");
            curd = date.getDate()-1;
            if(curd<0)
            {
                if(curmonth == 2)
                       curd = 31;
                else if(curmonth == 4 || curmonth == 6 || curmonth == 9 || curmonth == 11)
                        curd = 30;
                else
                        curd = 31;
                curmonth--;
                if(curmonth==-1)
                {
                    curmonth = 12;
                    curyear--;
                }

            }
        }
        else if(predicate.contains("days"))
            {
                String parts[] = predicate.split(" ");
                for(int j=0;j<parts.length;j++)
                    if(parts[j].equals("days"))
                    {
                        curd = date.getDate() +  Integer.valueOf(parts[j - 1]);
                        predicate = predicate.replaceFirst(parts[j],"");
                        predicate = predicate.replaceFirst(parts[j-2],"");
                        predicate = predicate.replaceFirst(parts[j - 1],"");
                        break;
                    }
            }

       
       if(curd!=-1)
       {

           int mmon[] = new int[13];
           mmon[1] = 31;mmon[2] = 28;mmon[3]=31;mmon[4]=30;mmon[5]=31;mmon[6]=30;mmon[7]=31;mmon[8]=31;
           mmon[9] = 30;mmon[10] = 31;mmon[11]=30;mmon[12]=31;
           int ct = curmonth;
           while(curd>mmon[curmonth])
           {
               curd-=mmon[curmonth++];
               ct++;
               if(curmonth==13)curmonth=1;
           }
           curyear+=(ct/13);
           curmonth = ct%12;
           if(curmonth==0)curmonth=12;
           curdate = curd;
       }
        

        String newp[] = predicate.split(" ");
        for(int j=0;j<newp.length;j++)
        {
            if(newp[j].contains("/"))
            {
                String news[] = newp[j].split("/");
                if(news.length==3)
                {
                    curdate = Integer.valueOf(news[0]);
                    curmonth = Integer.valueOf(news[1]);
                    if(news[2].length()==4)
                            curyear = Integer.valueOf(news[2]);
                    else
                            curyear = Integer.valueOf("20"+news[2]);
                }
                else if(news.length == 2)
                {
                    curdate = Integer.valueOf(news[0]);
                    curmonth = Integer.valueOf(news[1]);
                }
                predicate = predicate.replaceFirst(newp[j],"");
                break;

            }
        }

        for(int j=0;j<newp.length;j++)
        {
            if(newp[j].contains(":"))
            {
                String news[] = newp[j].split(":");
                if(news.length==3)
                {
                    curhour = Integer.valueOf(news[0]);
                    curminute = Integer.valueOf(news[1]);
                    curseconds = Integer.valueOf(news[2]);
                }
                else if(news.length == 2)
                {
                    curhour = Integer.valueOf(news[0]);
                    curminute = Integer.valueOf(news[1]);
                }
                predicate = predicate.replaceFirst(newp[j],"");
                break;
            }
        }
        if(predicate.contains("pm") || predicate.contains("PM") || predicate.contains("p.m.") || predicate.contains("P.M."))
        {
            curhour += 12;
            predicate.replaceFirst("pm","");
            predicate.replaceFirst("PM","");
            predicate.replaceFirst("p.m.","");
            predicate.replaceFirst("P.M.","");
        }

        int curh = -1;
        if(predicate.contains("hours"))
        {
                String parts[] = predicate.split(" ");
                for(int j=0;j<parts.length;j++)
                    if(parts[j].equals("hours"))
                    {
                        curh = date.getHours() + Integer.valueOf(parts[j - 1]);
                        predicate = predicate.replaceFirst(parts[j],"");
                        predicate = predicate.replaceFirst(parts[j-2],"");
                        predicate = predicate.replaceFirst(parts[j - 1],"");
                        if(curh>24)
                        {
                            curdate+=curh/24;
                            int mmon[] = new int[13];
                           mmon[1] = 31;mmon[2] = 28;mmon[3]=31;mmon[4]=30;mmon[5]=31;mmon[6]=30;mmon[7]=31;mmon[8]=31;
                           mmon[9] = 30;mmon[10] = 31;mmon[11]=30;mmon[12]=31;
                           int ct = curmonth;
                           while(curdate>mmon[curmonth])
                           {
                               curdate-=mmon[curmonth++];
                               ct++;
                               if(curmonth==13)curmonth=1;
                           }
                           curyear+=(ct/13);
                           curmonth = ct%12;
                           if(curmonth==0)curmonth=12;
                           curh%=24;
                        }
                         break;
                    }
        }
        if(curh!=-1)
                curhour = curh;
        int curmn = -1;
        if(predicate.contains("minutes"))
        {
                String parts[] = predicate.split(" ");
                for(int j=0;j<parts.length;j++)
                    if(parts[j].equals("minutes"))
                    {
                        curmn = date.getMinutes() + Integer.valueOf(parts[j - 1]);
                        predicate = predicate.replaceFirst(parts[j],"");
                        predicate = predicate.replaceFirst(parts[j-2],"");
                        predicate = predicate.replaceFirst(parts[j - 1],"");
                        if(curmn>59)
                        {
                            curh+=curmn/60;
                            curmn%=60;
                            if(curh>24)
                            {
                                curdate+=curh/24;
                                curh%=24;
                            }
                        }
                         break;
                    }
        }
        if(curmn!=-1)
                curminute = curmn;
        System.out.println(predicate);

        dates[0] = curyear;
        dates[1] = curmonth;
        dates[2] = curdate;
        dates[3] = curhour;
        dates[4] = curminute;
        dates[5] = curseconds;
        message = predicate;
        return dates;
    }

    public static String removeDate()
    {
        return message;
    }


    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        // TODO code application logic here
        init();
        //System.out.println(System.getProperty("user.dir"));
        String ab = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true)
        {
            try {
                ab = br.readLine();
                if(ab.equals("EXIT"))break;
            } catch (IOException ex) {
                Logger.getLogger(NotesProcessor.class.getName()).log(Level.SEVERE, null, ex);
            }
            //process("ADD LOG " +ab);
            process("CHECK LOG "+ab);
        }
        //process("ADD TO CONTENTS helloworld = Hello World \nIt's great to see you all!!!");
        
    }
    public static void process(String task)
    {
        init();
        String workingDir = System.getProperty("user.dir");
        Date b = new Date();
        Calendar c = new GregorianCalendar(Locale.ENGLISH) ;
        if(task.contains("ADD LOG"))
           {
               PrintWriter pw = null;
            try {
                String[] temp = task.split("ADD LOG");
                String task1 = temp[1];
                int[] arr = findDate(task1);
                System.out.println(arr[2]+"/"+arr[1]+"/"+arr[0]+" "+arr[3]+":"+arr[4]+":"+arr[5]);
                String taskfinal = removeDate();
                c.set(arr[0], arr[1]-1, arr[2], arr[3], arr[4], arr[5]);
                File logs = new File(workingDir + logPath + "log.txt");

                BufferedReader bc = new BufferedReader(new FileReader(logs));
                String inputLine;String resp = "";
                while ((inputLine = bc.readLine()) != null)
                {
                        //System.out.println(inputLine);
                        resp += inputLine;
                        resp+="\n";
                }
                //logs.setWritable(true);
                pw = new PrintWriter(logs);
                pw.append(resp+c.getTimeInMillis()+ "`!`" + taskfinal);
            } catch (Exception ex) {
                Logger.getLogger(NotesProcessor.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                pw.close();
            }
               result = "LOG SUCCESSFULLY ADDED";
        }
            else if(task.contains("CHECK LOG"))
            {
                String[] temp = task.split("CHECK LOG");
                String task1 = temp[1];
                int[] arr = findDate(task1);
                System.out.println(arr[2]+"/"+arr[1]+"/"+arr[0]+" "+arr[3]+":"+arr[4]+":"+arr[5]);
                File logs = new File(workingDir + logPath + "log.txt");
                String resp = "";
                todayLog="";
                try {

                                BufferedReader bc = new BufferedReader(new FileReader(logs));
                                String line = null;
                                while ((line = bc.readLine()) != null) {
                                    String los[] = line.split("`!`");
                                    c.setTimeInMillis(Long.valueOf(los[0]));
                                    if((c.get(c.YEAR)==arr[0] && c.get(c.MONTH)==arr[1]-1 && c.get(c.DATE)==arr[2]))
                                    {
                                        resp+=c.getTime()+" "+los[1]+"\n";System.out.println(c.getTime()+" "+los[1]);
                                    }
                                    if(date.getYear()+1900==c.get(c.YEAR) && c.get(c.MONTH)==date.getMonth() && c.get(c.DATE)==date.getDate())
                                        todayLog+=line;
                                }
                            } catch (IOException ex)
                            {
                                    Logger.getLogger(NotesProcessor.class.getName()).log(Level.SEVERE, null, ex);
                            }
                if(resp.equals(""))
                        resp = "NO MEETING SCHEDULED";
                fetchedLog = resp;
                result = fetchedLog;
            }
            else if(task.contains("FETCH VAR"))
            {

                
                String task1 = task.replace("FETCH VAR","");
                task1 =task1.trim();
                //System.out.println(task1);
                File logs = new File(workingDir + varPath + "variables.txt");
                try {

                                BufferedReader bc = new BufferedReader(new FileReader(logs));
                                String line = null;
                                while ((line = bc.readLine()) != null) {
                                        if(line.contains(task1+"`@`"))
                                        {
                                            String pr[] = line.split("`@`");
                                            fetchedVariable = pr[1].trim();
                                            break;
                                        }
                                }
                            } catch (IOException ex)
                            {
                                    Logger.getLogger(NotesProcessor.class.getName()).log(Level.SEVERE, null, ex);
                            }
                 System.out.println(fetchedVariable);
                 result = fetchedVariable;
            }
            else if(task.contains("UPDATE VAR"))
            {
                String task1 = task.replace("UPDATE VAR","");
                task1 =task1.trim();
                String operands[] = task1.split("SET");
                String varname = operands[0].trim();
                String newval  = operands[1].trim();
                System.out.println(varname+" "+newval);
                String resp="";
                File logs = new File(workingDir + varPath + "variables.txt");
                try {

                                BufferedReader bc = new BufferedReader(new FileReader(logs));
                                String line = null;
                                resp="";
                                while ((line = bc.readLine()) != null) {
                                        if(line.contains(varname+"`@`"))
                                        {
                                            String pr[] = line.split("`@`");
                                            pr[1] = newval;
                                            resp+=pr[0]+"`@`"+pr[1];
                                        }
                                        else
                                            resp+=line;
                                        resp+="\n";
                                }
                            } catch (IOException ex)
                            {
                                    Logger.getLogger(NotesProcessor.class.getName()).log(Level.SEVERE, null, ex);
                            }
                File loggs = new File(workingDir + varPath + "variables.txt");
                PrintWriter pww = null;

                try{
                    pww = new PrintWriter(loggs);
                    System.out.println(resp);
                    pww.write(resp);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    pww.close();
                }
                result = "Variable Updated Successfully";
            }
            else if(task.contains("ADD VAR"))
            {
                String task1 = task.replace("ADD VAR","");
                task1 =task1.trim();
                String operands[] = task1.split("AS");
                String varname = operands[0].trim();
                String newval  = operands[1].trim();
                System.out.println(varname+" "+newval);
                String resp="";
                boolean flag=false;
                File logs = new File(workingDir + varPath + "variables.txt");
                try {

                                BufferedReader bc = new BufferedReader(new FileReader(logs));
                                String line = null;
                                resp="";
                                flag = false;
                                while ((line = bc.readLine()) != null) {
                                        if(line.contains(varname+"`@`"))
                                        {
                                            flag = true;
                                            String pr[] = line.split("`@`");
                                            pr[1] = newval;
                                            resp+=pr[0]+"`@`"+pr[1];
                                        }
                                        else
                                            resp+=line;
                                        resp+="\n";
                                }
                            } catch (IOException ex)
                            {
                                    Logger.getLogger(NotesProcessor.class.getName()).log(Level.SEVERE, null, ex);
                            }

                File loggs = new File(workingDir + varPath + "variables.txt");
                PrintWriter pww = null;

                try{
                    if(flag==false)
                        resp+=varname+"`@`"+newval+"\n";
                    pww = new PrintWriter(loggs);
                    System.out.println(resp);
                    pww.write(resp);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    pww.close();
                }
                result = "Variable Added Successfully";
            }
            else if(task.contains("DELETE VAR"))
            {
                String task1 = task.replace("DELETE VAR","");
                task1 =task1.trim();
                String resp="";
                boolean flag=false;
                File logs = new File(workingDir + varPath + "variables.txt");
                try {

                                BufferedReader bc = new BufferedReader(new FileReader(logs));
                                String line = null;
                                resp="";
                                flag = false;
                                while ((line = bc.readLine()) != null) {
                                        if(!(line.contains(task1+"`@`")))
                                            resp+=line+"\n";
                                        
                                }
                            } catch (IOException ex)
                            {
                                    Logger.getLogger(NotesProcessor.class.getName()).log(Level.SEVERE, null, ex);
                            }

                File loggs = new File(workingDir + varPath + "variables.txt");
                PrintWriter pww = null;

                try{
                    pww = new PrintWriter(loggs);
                    System.out.println(resp);
                    pww.write(resp);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    pww.close();
                }
                result = "Variable Deleted Successfully";

            }
            else if(task.contains("ADD TO CONTENTS"))
            {
                String task1 = task.replace("ADD TO CONTENTS","");
                String operands[] = task1.split("AS");
                String varname = operands[0].trim();
                String newval  = operands[1].trim();
                task1 =task1.trim();
                File logs = new File(workingDir + contentPath + varname+".txt");
                PrintWriter pww = null;

                try{
                    pww = new PrintWriter(logs);
                    pww.write(newval);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    pww.close();
                }
                result = "Content Added Successfully";
            }
            else if(task.contains("FETCH CONTENT"))
            {
                String task1 = task.replace("FETCH CONTENT","");
                task1 =task1.trim();
                String resp="";
                boolean flag=false;
                File logs = new File(workingDir + contentPath + task1+".txt");
                try {

                                BufferedReader bc = new BufferedReader(new FileReader(logs));
                                String line = null;
                                resp="";
                                flag = false;
                                while ((line = bc.readLine()) != null) {
                                    resp+=line+"\n";
                                }
                            } catch (IOException ex)
                            {
                                    Logger.getLogger(NotesProcessor.class.getName()).log(Level.SEVERE, null, ex);
                            }
                      fetchedContent = resp;

                      result = fetchedContent!=""?fetchedContent:"No Such File";
                      System.out.println(fetchedContent);
            }
            else if(task.contains("DELETE CONTENT"))
            {
                String task1 = task.replace("DELETE CONTENT","");
                task1 =task1.trim();
                File logs = new File(workingDir + contentPath + task1+".txt");
                logs.delete();
                result = "Content Deleted Successfully";
            }
    }
}
