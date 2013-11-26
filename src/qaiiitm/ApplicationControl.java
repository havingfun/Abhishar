/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qaiiitm;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author FoUkat
 */
public class ApplicationControl {

        public static HashMap keyboard = new HashMap();
            static int keyInput[] = {
            KeyEvent.VK_A, KeyEvent.VK_B, KeyEvent.VK_C,KeyEvent.VK_D, KeyEvent.VK_E, KeyEvent.VK_F,
            KeyEvent.VK_G, KeyEvent.VK_H,KeyEvent.VK_I, KeyEvent.VK_J,KeyEvent.VK_K,KeyEvent.VK_L,
            KeyEvent.VK_M,KeyEvent.VK_N,KeyEvent.VK_O,KeyEvent.VK_P,KeyEvent.VK_Q,KeyEvent.VK_R,KeyEvent.VK_S,
            KeyEvent.VK_T,KeyEvent.VK_U,KeyEvent.VK_V,KeyEvent.VK_W,KeyEvent.VK_X,KeyEvent.VK_Y,KeyEvent.VK_Z,
            KeyEvent.VK_SPACE,KeyEvent.VK_ENTER };
      
    /**
     * @param args the command line arguments
     */

      public static Robot abhishar = null;

      public static void init() throws Exception
        {
          // A to Z
          keyboard.put("a",new Integer(KeyEvent.VK_A));keyboard.put("b",new Integer(KeyEvent.VK_B));keyboard.put("c",new Integer(KeyEvent.VK_C));keyboard.put("d",new Integer(KeyEvent.VK_D));
          keyboard.put("e",new Integer(KeyEvent.VK_E));keyboard.put("f",new Integer(KeyEvent.VK_F));keyboard.put("g",new Integer(KeyEvent.VK_G));keyboard.put("h",new Integer(KeyEvent.VK_H));
          keyboard.put("i",new Integer(KeyEvent.VK_I));keyboard.put("j",new Integer(KeyEvent.VK_J));keyboard.put("k",new Integer(KeyEvent.VK_K));keyboard.put("l",new Integer(KeyEvent.VK_L));
          keyboard.put("m",new Integer(KeyEvent.VK_M));keyboard.put("n",new Integer(KeyEvent.VK_N));keyboard.put("o",new Integer(KeyEvent.VK_O));keyboard.put("p",new Integer(KeyEvent.VK_P));
          keyboard.put("q",new Integer(KeyEvent.VK_Q));keyboard.put("r",new Integer(KeyEvent.VK_R));keyboard.put("s",new Integer(KeyEvent.VK_S));keyboard.put("t",new Integer(KeyEvent.VK_T));
          keyboard.put("u",new Integer(KeyEvent.VK_U));keyboard.put("v",new Integer(KeyEvent.VK_V));keyboard.put("w",new Integer(KeyEvent.VK_W));keyboard.put("x",new Integer(KeyEvent.VK_X));
          keyboard.put("y",new Integer(KeyEvent.VK_Y));keyboard.put("z",new Integer(KeyEvent.VK_Z));

          //Symbols
          keyboard.put("*",new Integer(KeyEvent.VK_ASTERISK));
          keyboard.put("@",new Integer(KeyEvent.VK_AMPERSAND));keyboard.put(".",new Integer(KeyEvent.VK_PERIOD));
          keyboard.put(" ",new Integer(KeyEvent.VK_SPACE));keyboard.put(",",new Integer(KeyEvent.VK_COMMA));
          keyboard.put("-",new Integer(KeyEvent.VK_MINUS));keyboard.put("*",new Integer(KeyEvent.VK_ASTERISK));
          keyboard.put("!",new Integer(KeyEvent.VK_EXCLAMATION_MARK));keyboard.put("(",new Integer(KeyEvent.VK_LEFT_PARENTHESIS));keyboard.put(")",new Integer(KeyEvent.VK_RIGHT_PARENTHESIS));
          keyboard.put("{",new Integer(KeyEvent.VK_OPEN_BRACKET));keyboard.put("}",new Integer(KeyEvent.VK_CLOSE_BRACKET));
          keyboard.put(";",new Integer(KeyEvent.VK_SEMICOLON));keyboard.put(":",new Integer(KeyEvent.VK_COLON));
          keyboard.put("\n",new Integer(KeyEvent.VK_ENTER));
          keyboard.put("[",new Integer(KeyEvent.VK_OPEN_BRACKET));
          keyboard.put("]",new Integer(KeyEvent.VK_CLOSE_BRACKET));

          //

          keyboard.put("0",new Integer(KeyEvent.VK_0));keyboard.put("1",new Integer(KeyEvent.VK_1));keyboard.put("2",new Integer(KeyEvent.VK_2));
          keyboard.put("3",new Integer(KeyEvent.VK_3));keyboard.put("4",new Integer(KeyEvent.VK_4));keyboard.put("5",new Integer(KeyEvent.VK_5));
          keyboard.put("6",new Integer(KeyEvent.VK_6));keyboard.put("7",new Integer(KeyEvent.VK_7));
          keyboard.put("9",new Integer(KeyEvent.VK_9));keyboard.put("8",new Integer(KeyEvent.VK_8));
          keyboard.put("/",new Integer(KeyEvent.VK_SLASH));keyboard.put("'",new Integer(KeyEvent.VK_QUOTE));
          keyboard.put("=",new Integer(KeyEvent.VK_EQUALS));keyboard.put("\\",new Integer(KeyEvent.VK_BACK_SLASH));
          

          // 0 to 9
          keyboard.put("zero",new Integer(KeyEvent.VK_0));keyboard.put("one",new Integer(KeyEvent.VK_1));keyboard.put("two",new Integer(KeyEvent.VK_2));keyboard.put("three",new Integer(KeyEvent.VK_3));
          keyboard.put("four",new Integer(KeyEvent.VK_4));keyboard.put("five",new Integer(KeyEvent.VK_5));keyboard.put("six",new Integer(KeyEvent.VK_6));keyboard.put("seven",new Integer(KeyEvent.VK_7));
          keyboard.put("nine",new Integer(KeyEvent.VK_9));keyboard.put("eight",new Integer(KeyEvent.VK_8));

          keyboard.put("up",new Integer(KeyEvent.VK_UP));keyboard.put("down",new Integer(KeyEvent.VK_DOWN));
          keyboard.put("left",new Integer(KeyEvent.VK_LEFT));keyboard.put("right",new Integer(KeyEvent.VK_RIGHT));
          keyboard.put("enter",new Integer(KeyEvent.VK_ENTER));keyboard.put("space",new Integer(KeyEvent.VK_SPACE));
          keyboard.put(" ",new Integer(KeyEvent.VK_SPACE));

          //Rest A
          keyboard.put("accept",new Integer(KeyEvent.VK_ACCEPT));keyboard.put("plus",new Integer(KeyEvent.VK_ADD));keyboard.put("alt",new Integer(KeyEvent.VK_ALT));
          keyboard.put("ampersand",new Integer(KeyEvent.VK_AMPERSAND));keyboard.put("asterisk",new Integer(KeyEvent.VK_ASTERISK));keyboard.put("again",new Integer(KeyEvent.VK_AGAIN));
          keyboard.put("all",new Integer(KeyEvent.VK_ALL_CANDIDATES));keyboard.put("at",new Integer(KeyEvent.VK_AT));

          // Rest B
          keyboard.put("back quote",new Integer(KeyEvent.VK_BACK_QUOTE));keyboard.put("back slash",new Integer(KeyEvent.VK_BACK_SLASH));
          keyboard.put("erase",new Integer(KeyEvent.VK_BACK_SPACE));keyboard.put("brace left",new Integer(KeyEvent.VK_BRACELEFT));
          keyboard.put("brace right",new Integer(KeyEvent.VK_BRACERIGHT));

          // Rest C
          keyboard.put("cancel",new Integer(KeyEvent.VK_CANCEL));keyboard.put("caps",new Integer(KeyEvent.VK_CAPS_LOCK));
          keyboard.put("clear",new Integer(KeyEvent.VK_CLEAR));keyboard.put("close bracket",new Integer(KeyEvent.VK_CLOSE_BRACKET));
          keyboard.put("code input",new Integer(KeyEvent.VK_CODE_INPUT));keyboard.put("colon",new Integer(KeyEvent.VK_COLON));keyboard.put("comma",new Integer(KeyEvent.VK_COMMA));
          keyboard.put("compose",new Integer(KeyEvent.VK_COMPOSE));keyboard.put("context menu",new Integer(KeyEvent.VK_CONTEXT_MENU));
          keyboard.put("control",new Integer(KeyEvent.VK_CONTROL));keyboard.put("copy",new Integer(KeyEvent.VK_COPY));keyboard.put("cut",new Integer(KeyEvent.VK_CUT));
          
          //Rest D
          keyboard.put("delete",new Integer(KeyEvent.VK_DELETE));keyboard.put("divide",new Integer(KeyEvent.VK_DIVIDE));keyboard.put("dollar",new Integer(KeyEvent.VK_DOLLAR));

          //Rest E
          keyboard.put("end",new Integer(KeyEvent.VK_END));keyboard.put("equals",new Integer(KeyEvent.VK_EQUALS));keyboard.put("escape",new Integer(KeyEvent.VK_ESCAPE));
          keyboard.put("euro",new Integer(KeyEvent.VK_EURO_SIGN));keyboard.put("exclamation",new Integer(KeyEvent.VK_EXCLAMATION_MARK));

          //Rest F
          keyboard.put("fullwidth",new Integer(KeyEvent.VK_FULL_WIDTH));

          //Rest G
          keyboard.put("greater",new Integer(KeyEvent.VK_GREATER));

          //Rest H
          keyboard.put("half width",new Integer(KeyEvent.VK_HALF_WIDTH));keyboard.put("home",new Integer(KeyEvent.VK_HOME));keyboard.put("help",new Integer(KeyEvent.VK_HELP));

          //Rest I
          keyboard.put("insert",new Integer(KeyEvent.VK_INSERT));

          //Rest L
          keyboard.put("left parenthesis",new Integer(KeyEvent.VK_LEFT_PARENTHESIS));keyboard.put("less",new Integer(KeyEvent.VK_LESS));

          //Rest M
          keyboard.put("minus",new Integer(KeyEvent.VK_MINUS));keyboard.put("multiply",new Integer(KeyEvent.VK_MULTIPLY));

          //Rest N
          keyboard.put("num lock",new Integer(KeyEvent.VK_NUM_LOCK));

          //Rest O
          keyboard.put("open bracket",new Integer(KeyEvent.VK_OPEN_BRACKET));

          //Rest P
          keyboard.put("page down",new Integer(KeyEvent.VK_PAGE_DOWN));keyboard.put("page up",new Integer(KeyEvent.VK_PAGE_UP));keyboard.put("pause",new Integer(KeyEvent.VK_PAUSE));
          keyboard.put("period",new Integer(KeyEvent.VK_PERIOD));keyboard.put("plus",new Integer(KeyEvent.VK_PLUS));keyboard.put("print screen",new Integer(KeyEvent.VK_PRINTSCREEN));
          
          //Rest Q
          keyboard.put("quote",new Integer(KeyEvent.VK_QUOTE));keyboard.put("double quotes",new Integer(KeyEvent.VK_QUOTEDBL));

          //Rest R
          keyboard.put("right parenthesis",new Integer(KeyEvent.VK_RIGHT_PARENTHESIS));

          // Rest S
          keyboard.put("page up",new Integer(KeyEvent.VK_SCROLL_LOCK));keyboard.put("semicolon",new Integer(KeyEvent.VK_SEMICOLON));keyboard.put("shift",new Integer(KeyEvent.VK_SHIFT));
          keyboard.put("subtract",new Integer(KeyEvent.VK_SUBTRACT));keyboard.put("stop",new Integer(KeyEvent.VK_STOP));keyboard.put("slash",new Integer(KeyEvent.VK_SLASH));

          // Rest T
          keyboard.put("tab",new Integer(KeyEvent.VK_TAB));

          // Rest U
          keyboard.put("underscore",new Integer(KeyEvent.VK_UNDERSCORE));keyboard.put("undo",new Integer(KeyEvent.VK_UNDO));

          // Rest W
          keyboard.put("start",new Integer(KeyEvent.VK_WINDOWS));


          abhishar = new Robot();


        }

      public static void type(String value)
      {
          System.out.println(value);
          int len = value.length();
          char [] strrr = value.toCharArray();
          for(int i=0;i<len;i++)
          {
                String a = String.valueOf(strrr[i]);
                boolean capital=false;
                if(a.charAt(0)>='A' && a.charAt(0)<='Z')capital=true;
                a = a.toLowerCase();
                if(a.equals("\""))a = "D";
                else if(a.equals("\\"))a="S";
                String shiftkeys[] = new String[]{":;","D'",">.","<,","~`","!1","@2","#3","$4","%5",
                "^6","&7","*8","(9",")0","_-","+=","|S","}]","{[","?/"};
                boolean flag = false;
                for(int j=0;j<shiftkeys.length;j++)
                {
                    if(shiftkeys[j].charAt(0)==a.charAt(0))
                    {
                        abhishar.keyPress(KeyEvent.VK_SHIFT);
                        int keyvaluenew = (Integer)keyboard.get(String.valueOf(shiftkeys[j].charAt(1)));
                        System.out.println(keyvaluenew);

                        abhishar.keyPress(keyvaluenew);
                        abhishar.keyRelease(KeyEvent.VK_SHIFT);
                        flag=true;
                        break;
                    }
                }
                if(flag==false)
                {
                    int keyvalue = (Integer)keyboard.get(a);
                    System.out.println(strrr[i]+" "+keyvalue);
                    if(capital==false)
                    {
                        abhishar.keyPress(keyvalue);
                    }
                    else
                    {
                        abhishar.keyPress(KeyEvent.VK_SHIFT);
                        abhishar.keyPress(keyvalue);
                        abhishar.keyRelease(KeyEvent.VK_SHIFT);
                    }
                }
           }
      }

      public static void type2()
      {
          //for(int i=65;i<255;i++)
          //{
              //System.out.println(i);
              abhishar.keyPress(KeyEvent.VK_ASTERISK);
              abhishar.keyRelease(KeyEvent.VK_ASTERISK);

          //}

      }
      
    public static void main(String[] args) throws Exception {

        init();
        Runtime.getRuntime().exec("notepad.exe");
        abhishar.delay(4000);
        Thread T=new Thread(new Runnable() {

            @Override
            public void run() {
                    try {
                        File ab = new File("g:/abc.java");

                        BufferedReader bc = new BufferedReader(new FileReader(ab));

                        String line = null;
                        while((line = bc.readLine())!=null)
                        {
                                    type(line);
                             
                        }
                        abhishar.delay(1000);
                        
                    } catch (IOException ex) {
                        Logger.getLogger(ApplicationControl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  }
        } );
        T.start();
    }

}
