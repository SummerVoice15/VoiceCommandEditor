/*
 * Copyright 1999-2004 Carnegie Mellon University.
 * Portions Copyright 2004 Sun Microsystems, Inc.
 * Portions Copyright 2004 Mitsubishi Electric Research Laboratories.
 * All Rights Reserved.  Use is subject to license terms.
 *
 * See the file "license.terms" for information on usage and
 * redistribution of this file, and for a DISCLAIMER OF ALL
 * WARRANTIES.
 *
 */



import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;


/**
 * A simple HelloWorld demo showing a simple speech application built using Sphinx-4. This application uses the Sphinx-4
 * endpointer, which automatically segments incoming audio into utterances and silences.
 */
public class HelloWorld {

    public static void main(String[] args) {
        ConfigurationManager cm;

        if (args.length > 0) {
            cm = new ConfigurationManager(args[0]);
        } else {
            cm = new ConfigurationManager(HelloWorld.class.getResource("helloworld.config.xml"));
        }

        Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
        recognizer.allocate();

        // start the microphone or exit if the programm if this is not possible
        Microphone microphone = (Microphone) cm.lookup("microphone");
        if (!microphone.startRecording()) {
            System.out.println("Cannot start microphone.");
            recognizer.deallocate();
            System.exit(1);
        }

        System.out.println("Say: the command:");

        // loop the recognition until the programm exits.
        while (true) {
            System.out.println("Start speaking. Press Ctrl-C to quit.\n");

            Result result = recognizer.recognize();

            if (result != null) {
                String resultText = result.getBestFinalResultNoFiller();
                String [] text=resultText.split(" ");
               /* StringTokenizer s=new StringTokenizer(resultText);
                String C=s.nextToken();
                StringBuilder S=new StringBuilder();
                String word;
                word=s.nextToken();
               do{
            	   S.append(word);
            	   word=s.nextToken();
               
                
               }while(word!=null);*/
                String S=text[1]+" ";
                if(text.length>2){
                for (int i=2;i<text.length;i++)
                	S+= text[i]+" ";}
                if(text[0].equals("print"))
                		System.out.println("System.out.print("+ S +")");
                else if (text[0].equals("println"))
                	System.out.println("System.out.print("+ S +")");
                else 
                	if (text[0].equals("declare"))
                	System.out.println(S);
                
             
            }
        }
    }   }

