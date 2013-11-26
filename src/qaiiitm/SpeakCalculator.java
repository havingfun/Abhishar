/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qaiiitm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author FoUkat
 */
public class SpeakCalculator {

    /**
     * @param args the command line arguments
     */

    public static double add(double a,double b)
    {
        return a+b;
    }

    public static double subtract(double a,double b)
    {
        return a-b;
    }

    public static double multiply(double a,double b)
    {
        return a*b;
    }

    public static double divide(double a,double b)
    {
                return a/b;
    }

    public static long add(long a,long b)
    {
        return a+b;
    }

    public static long subtract(long a,long b)
    {
        return a-b;
    }

    public static long multiply(long a,long b)
    {
        return a*b;
    }

    public static long divide(long a,long b)
    {
                return a/b;
    }

    public static String WithSeparator(long number) {
        if (number < 0) {
            return "-" + WithSeparator(-number);
        }
        if (number / 1000L > 0) {
            return WithSeparator(number / 1000L) + ","
                    + String.format("%1$03d", number % 1000L);
        } else {
            return String.format("%1$d", number);
        }
    }
    private static String[] numerals = { "zero", "one", "two",
            "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
            "seventeen", "eighteen", "ninteen", "twenty", "thirty", "forty",
            "fifty", "sixty", "seventy", "eighty", "ninety", "hundred" };

    private static long[] values = { 0, 1, 2, 3, 4, 5, 6, 7, 8,
            9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 30, 40, 50, 60, 70,
            80, 90, 100 };

    private static ArrayList<String> list = new ArrayList<String>(
            Arrays.asList(numerals));
    public static long parseNumerals(String text) throws Exception {
        long value = 0;
        String[] words = text.replaceAll(" and ", " ").split("\\s");
        for (String word : words) {
            if (!list.contains(word)) {
                throw new Exception("Unknown token : " + word);
            }

            long subval = getValueOf(word);
            if (subval == 100) {
                if (value == 0)
                    value = 100;
                else
                    value *= 100;
            } else
                value += subval;
        }

        return value;
    }

    private static long getValueOf(String word) {
        return values[list.indexOf(word)];
    }

    private static String[] operations = { "add", "subtract", "divide", "multiply" };

    private static String[] words = { "trillion", "billion", "million", "thousand" };

    private static long[] digits = { 1000000000000L, 1000000000L,1000000L, 1000L };

    public static long parse(String text) throws Exception {
        text = text.toLowerCase().replaceAll("[\\-,]", " ").replaceAll(" and "," ");
        long totalValue = 0;
        boolean processed = false;
        for (int n = 0; n < words.length; n++) {
            int index = text.indexOf(words[n]);
            if (index >= 0) {
                String text1 = text.substring(0, index).trim();
                String text2 = text.substring(index + words[n].length()).trim();

                if (text1.equals(""))
                    text1 = "one";

                if (text2.equals(""))
                    text2 = "zero";

                totalValue = parseNumerals(text1) * digits[n]+ parse(text2);
                processed = true;
                break;
            }
        }

        if (processed)
            return totalValue;
        else
            return parseNumerals(text);
    }

    public static long num1,num2;
    public static double dnum1,dnum2;
    public static int operationNumber;

    public static String analyzeNumberQuery(String number) throws Exception
    {
        String numCheck1[] = number.split("with");
        String numCheck2[] = number.split("to");
        String numCheck3[] = number.split("from");

        if(numCheck1.length == 2)
        {
            String literal1 = numCheck1[0];
            String literal2 = numCheck1[1];
            for(int i=0;i<4;i++)
            {
                if(literal1.contains(operations[i]))
                {
                    String newlit1 = literal1.substring(literal1.lastIndexOf(operations[i])+operations[i].length()+1, literal1.length()-1);
                    newlit1 = newlit1.trim();
                    literal2 = literal2.trim();
                    boolean flag1 = false;   // true if num1 is already numeral
                    boolean flag2 = false;   // true if num2 is already numberal
                    for(int j=0;j<10;j++)
                    {
                        if(newlit1.charAt(0)-'0'==values[j])
                        {
                            num1 = Integer.valueOf(newlit1);
                            flag1 = true;
                        }
                        if(literal2.charAt(0)-'0'==values[j])
                        {
                            num2 = Integer.valueOf(literal2);
                            flag2 = true;
                        }
                    }
                    if(flag1==false)num1 = parse(newlit1);
                    if(flag2==false)num2 = parse(literal2);
                    operationNumber = i;
                    break;
                }
            }
            return "Done";
        }
        else if(numCheck2.length == 2)
        {
            String literal1 = numCheck2[0];
            String literal2 = numCheck2[1];
            for(int i=0;i<4;i++)
            {
                if(literal1.contains(operations[i]))
                {
                    String newlit1 = literal1.substring(literal1.lastIndexOf(operations[i])+operations[i].length()+1, literal1.length()-1);
                    newlit1 = newlit1.trim();
                    literal2 = literal2.trim();
                     boolean flag1 = false;   // true if num1 is already numeral
                    boolean flag2 = false;   // true if num2 is already numberal
                    for(int j=0;j<10;j++)
                    {
                        if(newlit1.charAt(0)-'0'==values[j])
                        {
                            num1 = Integer.valueOf(newlit1);
                            flag1 = true;
                        }
                        if(literal2.charAt(0)-'0'==values[j])
                        {
                            num2 = Integer.valueOf(literal2);
                            flag2 = true;
                        }
                    }
                    if(flag1==false)num1 = parse(newlit1);
                    if(flag2==false)num2 = parse(literal2);
                    operationNumber = i;
                    break;
                }
            }
            return "Done";
        }
        else if(numCheck3.length == 2)
        {
            String literal1 = numCheck3[1];
            String literal2 = numCheck3[0];
            for(int i=0;i<4;i++)
            {
                if(literal2.contains(operations[i]))
                {
                    String newlit2 = literal2.substring(literal2.lastIndexOf(operations[i])+operations[i].length()+1, literal2.length()-1);
                    newlit2 = newlit2.trim();
                    literal1 = literal1.trim();
                    boolean flag1 = false;   // true if num1 is already numeral
                    boolean flag2 = false;   // true if num2 is already numberal
                    for(int j=0;j<10;j++)
                    {
                        if(newlit2.charAt(0)-'0'==values[j])
                        {
                            num1 = Integer.valueOf(newlit2);
                            flag1 = true;
                        }
                        if(literal1.charAt(0)-'0'==values[j])
                        {
                            num2 = Integer.valueOf(literal1);
                            flag2 = true;
                        }
                    }
                    if(flag1==false)num1 = parse(newlit2);
                    if(flag2==false)num2 = parse(literal1);
                    operationNumber = i;
                    break;
                }
            }
            return "Done";
        }
        else

        {
            return "Error in Parsing!";
        }

    }

    public static long solveQuery(String task) throws Exception
    {
        String forCheck = analyzeNumberQuery(task);
        if(forCheck.equals("Error in Parsing"))
                return -123404321;
        return mathsFunction(num1,num2,operationNumber);
    }

    public static long mathsFunction(long num1,long num2,int operationNumber)
    {
        long res=0;
            switch (operationNumber)
            {
                case 0:
                    res = add(num1,num2);
                    break;
                case 1:
                        res = subtract(num1,num2);
                        break;
                case 2:
                        if(num2==0)
                                res = -123404321;
                        else
                                res = divide(num1,num2);
                        break;
                case 3:
                        res = multiply(num1,num2);
                        break;
            }
            return res;

    }
    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true)
        {
            String ab = br.readLine();
            long res = solveQuery(ab);
            System.out.println(res);
        }
    }

}
