/////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Final Project
// Files:            MainMenuGUI.java
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

package my_base;

import javax.swing.*;
import java.awt.*;


/**
 * MainMenuGUI class
 * 
 * @implNote This class implements a new MainMenuGUI in charge of the main menu of the game.
 *
 *           <p>
 *           Bugs: (a list of bugs and other problems)
 * 
 * @author (YuvalYossiPablo)
 */
public class MainMenuGUI 
{
	public enum Difficulty 
    {
		EASY (5, "Easy"),
		MODERATE (10, "Moderate"),
		HARD (15, "Hard"),
		IMPOSSIBLE (30, "Impossible");
		
        private final int       QUANTITY_OF_DIFFERENT_SMALL_ENEMIES;
        private final String    DESCRIPTION;

		private Difficulty(int quantityOfDifferentSmallEnemies, String description) 
        {
			this.QUANTITY_OF_DIFFERENT_SMALL_ENEMIES    = quantityOfDifferentSmallEnemies;
            this.DESCRIPTION                            = description;
		}
	}

    private static Difficulty selectedDifficulty    = Difficulty.MODERATE;

    public static int selectedDifficultyInMainMenuGUI()
    {
        return selectedDifficulty.QUANTITY_OF_DIFFERENT_SMALL_ENEMIES;
    }

    public static void main(String[] args) 
    {
        JFrame frame    = new JFrame("Zone Capture - Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 1));

        JLabel title    = new JLabel("Zone Capture", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(title);

        JButton startButton         = new JButton("Start Game");
        JButton aboutButton         = new JButton("About");
        JButton difficultyButton    = new JButton("Difficulty");
        JButton exitButton          = new JButton("Exit");

        startButton.addActionListener(e -> 
        {
            frame.dispose();
            MyGame game     = new MyGame();
            game.start();   // Start the game
        });

        aboutButton.addActionListener(e -> 
        {
            JOptionPane.showMessageDialog(frame,
                    "Welcome to Zone Capture\nMade by Y2P\nEnjoy",
                    "About", JOptionPane.INFORMATION_MESSAGE);
        });

        difficultyButton.addActionListener(e -> 
        {
            final int   QUANTITY_OF_ENUMERATORS = Difficulty.values().length;
            String[]    options = new String[QUANTITY_OF_ENUMERATORS];

            for (int index = 0; index < QUANTITY_OF_ENUMERATORS; index++) 
            {
                options[index]  = Difficulty.values()[index].DESCRIPTION;
            }           

            int choice  = JOptionPane.showOptionDialog(frame,
                    "Select Difficulty Level:",
                    "Difficulty",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);

            //  Sets the difficulty for the game
            selectedDifficulty  = Difficulty.values()[choice];
        });

        exitButton.addActionListener(e -> System.exit(0));

        frame.add(startButton);
        frame.add(difficultyButton);
        frame.add(aboutButton);
        frame.add(exitButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}