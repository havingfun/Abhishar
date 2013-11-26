package org.alicebot.ab;
import java.io.*;
/* Program AB Reference AIML 2.0 implementation
        Copyright (C) 2013 ALICE A.I. Foundation
        Contact: info@alicebot.org

        This library is free software; you can redistribute it and/or
        modify it under the terms of the GNU Library General Public
        License as published by the Free Software Foundation; either
        version 2 of the License, or (at your option) any later version.

        This library is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
        Library General Public License for more details.

        You should have received a copy of the GNU Library General Public
        License along with this library; if not, write to the
        Free Software Foundation, Inc., 51 Franklin St, Fifth Floor,
        Boston, MA  02110-1301, USA.
*/

import org.alicebot.ab.utils.IOUtils;
/**
 * Class encapsulating a chat session between a bot and a client
 */
public class Chat {
    public Bot bot;
    public String customerId = MagicStrings.unknown_customer_id;
    public History<History> thatHistory= new History<History>("that");
    public History<String> requestHistory=new History<String>("request");
    public History<String> responseHistory=new History<String>("response");
    public History<String> inputHistory=new History<String>("input");
    public Predicates predicates = new Predicates();
    public static String matchTrace = "";
    public static boolean locationKnown = false;
    public static String longitude;
    public static String latitude;

    /**
     * Constructor  (defualt customer ID)
     *
     * @param bot    the bot to chat with
     */
    public Chat(Bot bot)  {
        this(bot, "0");
    }

    /**
     * Constructor
     * @param bot             bot to chat with
     * @param customerId      unique customer identifier
     */
    public Chat(Bot bot, String customerId) {
        this.customerId = customerId;
        this.bot = bot;
        History<String> contextThatHistory = new History<String>();
        contextThatHistory.add(MagicStrings.default_that);
        thatHistory.add(contextThatHistory);
        addPredicates();
        predicates.put("topic", MagicStrings.default_topic);
    }

    /**
     * Load all predicate defaults
     */
    void addPredicates() {
        try {
            predicates.getPredicateDefaults(MagicStrings.config_path+"/predicates.txt") ;
        } catch (Exception ex)  {
            ex.printStackTrace();
        }
    }

    /**
     * Chat session terminal interaction
     */
    public void chat () {
        BufferedWriter bw = null;
        String logFile = MagicStrings.log_path+"/log_"+customerId+".txt";
        try {
            //Construct the bw object
            bw = new BufferedWriter(new FileWriter(logFile, true)) ;
            String request="SET PREDICATES";
            String response = multisentenceRespond(request);
            while (!request.equals("quit")) {
                System.out.print("Human: ");
				request = IOUtils.readInputTextLine();
                response = multisentenceRespond(request);
                System.out.println("Robot: "+response);
                bw.write("Human: "+request);
                bw.newLine();
                bw.write("Robot: "+response);
                bw.newLine();
                bw.flush();
            }
            bw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Return bot response to a single sentence input given conversation context
     *
     * @param input         client input
     * @param that          bot's last sentence
     * @param topic         current topic
     * @param contextThatHistory         history of "that" values for this request/response interaction
     * @return              bot's reply
     */
    String respond(String input, String that, String topic, History contextThatHistory) {
        String response;
        inputHistory.add(input);
        response = AIMLProcessor.respond(input, that, topic, this);
        String normResponse = bot.preProcessor.normalize(response);
        normResponse = JapaneseTokenizer.morphSentence(normResponse); //response.trim(); //
        String sentences[] = bot.preProcessor.sentenceSplit(normResponse);
        for (int i = 0; i < sentences.length; i++) {
          that = sentences[i];
          //System.out.println("That "+i+" '"+that+"'");
          if (that.trim().equals("")) that = MagicStrings.default_that;
          contextThatHistory.add(that);
        }
        return response.trim()+"  ";
    }

    /**
     * Return bot response given an input and a history of "that" for the current conversational interaction
     *
     * @param input       client input
     * @param contextThatHistory  history of "that" values for this request/response interaction
     * @return    bot's reply
     */
    String respond(String input, History<String> contextThatHistory) {
        History hist = thatHistory.get(0);
        String that;
        if (hist == null) that = MagicStrings.default_that;
        else that = hist.getString(0);
        return respond(input, that, predicates.get("topic"), contextThatHistory);
    }

    /**
     * return a compound response to a multiple-sentence request. "Multiple" means one or more.
     *
     * @param request      client's multiple-sentence input
     * @return
     */
    public String multisentenceRespond(String request) {
        String response="";
        matchTrace="";
        /*thatHistory.printHistory();
        inputHistory.printHistory();
        requestHistory.printHistory();
        responseHistory.printHistory();*/
        try {
        String norm = bot.preProcessor.normalize(request);
        norm = JapaneseTokenizer.morphSentence(norm);
        if (MagicBooleans.trace_mode) System.out.println("normalized = "+norm);
        String sentences[] = bot.preProcessor.sentenceSplit(norm);
        History<String> contextThatHistory = new History<String>("contextThat");
        for (int i = 0; i < sentences.length; i++) {
            //System.out.println("Human: "+sentences[i]);
            AIMLProcessor.trace_count = 0;
            String reply = respond(sentences[i], contextThatHistory);
            response += "  "+reply;
            //System.out.println("Robot: "+reply);
        }
        requestHistory.add(request);
        responseHistory.add(response);
        thatHistory.add(contextThatHistory);
        //if (MagicBooleans.trace_mode)  System.out.println(matchTrace);
        } catch (Exception ex) {
            ex.printStackTrace();
            return MagicStrings.error_bot_response;
        }

        bot.writeLearnfIFCategories();
        return response.trim();
    }


    public static void setMatchTrace(String newMatchTrace) {
		matchTrace = newMatchTrace;
	}
}
