/////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Final Project
// Files:            TipManager.java
// Semester:         Spring 2025
//
// Author:           YuvalYossiPablo
// Email:            
// CS Login:         
// Lecturer's Name:  Rami Marelly, Ph.D.
// Lab Section:      00860222
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If pair programming is allowed:
//                   1. Read PAIR-PROGRAMMING policy (in cs302 policy) 
//                   2. choose a partner wisely
//                   3. REGISTER THE TEAM BEFORE YOU WORK TOGETHER 
//                      a. one partner creates the team
//                      b. the other partner must join the team
//                   4. complete this section for each program file.
//
// Pair Partner:     Yossi Huttner
// Email:            yossihuttner@yahoo.com
// CS Login:         yossef.h@campus.technion.ac.il
// Lecturer's Name:  Rami Marelly, Ph.D.
// Lab Section:      00860222
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If pair programming is allowed:
//                   1. Read PAIR-PROGRAMMING policy (in cs302 policy) 
//                   2. choose a partner wisely
//                   3. REGISTER THE TEAM BEFORE YOU WORK TOGETHER 
//                      a. one partner creates the team
//                      b. the other partner must join the team
//                   4. complete this section for each program file.
//
// Pair Partner:     Yuval Shechter
// Email:            yuvalshe@gmail.com
// CS Login:         y.shechter@campus.technion.ac.il
// Lecturer's Name:  Rami Marelly, Ph.D.
// Lab Section:      00860222
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If pair programming is allowed:
//                   1. Read PAIR-PROGRAMMING policy (in cs302 policy) 
//                   2. choose a partner wisely
//                   3. REGISTER THE TEAM BEFORE YOU WORK TOGETHER 
//                      a. one partner creates the team
//                      b. the other partner must join the team
//                   4. complete this section for each program file.
//
// Pair Partner:     Pablo Daniel Jelsky
// Email:            PabloDanielJelsky@gmail.com
// CS Login:         pablo.jelsky@campus.technion.ac.il
// Lecturer's Name:  Rami Marelly, Ph.D.
// Lab Section:      00860222
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   must fully acknowledge and credit those sources of help.
//                   Instructors and TAs do not have to be credited here,
//                   but tutors, roommates, relatives, strangers, etc do.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   The headers in this file were taken as an example from
//                   https://pages.cs.wisc.edu/~cs302/resources/guides/commenting.html
//
//////////////////////////// 80 columns wide //////////////////////////////////

package my_game;

import base.Game;
import base.GameCanvas;
import my_base.MyContent;
import my_ui_elements.GetNameButton;
import shapes.Text;

import java.awt.Color;

/**
 * TipManager displays motivational tips on the canvas every few seconds.
 * It cycles through a predefined list of tips.
 */
public class TipManager 
{
    private GameCanvas      canvas;
    private String[]        tips;
    private int             currentTipIndex         = 0;
    private long            lastUpdateTime          = 0;
    private final int       MILLISECONDS_IN_SECOND  = 1000;
    private final long      TIP_INTERVAL_MS         = 10 * MILLISECONDS_IN_SECOND; // 10 seconds
    private final String    TIP_GUID                = "motivational_tip";

    public TipManager(GameCanvas canvas) 
    {
        this.canvas = canvas;
        this.tips   = new String[] 
        {
            "The only way to do great work is to love what you do.",
            "Success is not final, failure is not fatal.",
            "Believe you can and you're halfway there.",
            "Don’t watch the clock; do what it does. Keep going.",
            "You miss 100% of the shots you don’t take.",
            "Hardships prepare ordinary people for extraordinary destiny.",
            "It always seems impossible until it’s done.",
            "Dream big and dare to fail.",
            "Start where you are. Use what you have. Do what you can.",
            "The future belongs to those who believe in their dreams.",
            "You've got this!",
            "Keep pushing, you're almost there!",
            "Every challenge is a step closer to victory!",
            "Stay focused and keep moving forward!",
            "Believe in your skills!",
            "One step at a time, you can do it!",
            "Don't give up, greatness takes time!",
            "You're stronger than you think!",
            "Keep your eyes on the prize!",
            "Victory is just around the corner!",
            "Keep going, you are doing great!",
            "Every effort counts!",
            "Stay strong, stay focused!",
            "You're unstoppable!",
            "Keep the momentum!",
            "You're on fire!",
            "Push through, you can make it!",
            "Stay determined!",
            "You are closer than you think!",
            "Keep your head up!",
            "You are a champion!",
            "Stay in the zone!",
            "Keep fighting!",
            "You are making progress!",
            "Stay sharp!",
            "You are a warrior!",
            "Keep your spirit high!",
            "You are destined for greatness!",
            "Every day is a new opportunity!",
            "Success is a journey, not a destination.",         
            "Your hard work will pay off!",
            "Stay positive, stay strong!",
            "You are capable of amazing things!",   
            "Keep your dreams alive!",
            "Every setback is a setup for a comeback.", 
            "Success is not for the lazy.",
            "You are the master of your destiny.",  
            "Your only limit is you.",
            "Keep your eyes on the goal!",
            "Success is the sum of small efforts, repeated day in and day out.",
            "The harder you work for something, the greater you'll feel when you achieve it.",
            "Dream it. Wish it. Do it.",
            "Success doesn't just find you. You have to go out and get it.",
            "The key to success is to focus on goals, not obstacles.",
            "Dream big, work hard, stay focused, and surround yourself with good people."
        };
    }

    /**
     * Call this method periodically (e.g. every game loop) to update the tip.
     */
    public void update() 
    {
        long currentTime    = System.currentTimeMillis();
        if (currentTime - lastUpdateTime >= TIP_INTERVAL_MS) 
        {
            showTip(tips[currentTipIndex]);
            currentTipIndex = (currentTipIndex + 1) % tips.length;
            lastUpdateTime      = currentTime;
        }
    }

    /**
     * Displays the current tip on the canvas.
     */
    private void showTip(String tip) 
    {
        if (null != canvas)
        {
            Text    tipText = (Text) canvas.getShape(TIP_GUID);
            if (null != tipText)
            {
                canvas.deleteShape(TIP_GUID);   // Remove previous tip
            }
            tipText     = new Text(TIP_GUID, tip, 100, 900);   // Bottom of screen
            tipText.setColor(Color.LIGHT_GRAY);
            tipText.setFontSize(20);
            canvas.addShape(tipText);
        }
        else
        {
            MyContent   content = (MyContent)(Game.Content());
            content.tipLine().showText("" != GetNameButton.getPlayerName() 
                ? GetNameButton.getPlayerName() + ", " + tip
                : "" + tip, Color.ORANGE, 4 * MILLISECONDS_IN_SECOND);
        }
    }
}
