package ui_elements;

import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import base.Game;
import base.GameContent;

/*
 * The GameText is a class that handles dashboard texts.
 */

public class GameText extends UIElement {
	
	protected JTextField    text;
	//protected GameContent  content;
	
	public GameText(String id, String name, int width, int height, int posX, int posY) {
		super(id, posX, posY, width, height, new JTextField());
		this.text = (JTextField) this.getJComponent();
		setText(name);
		this.text.setFont(new Font("Ariel", Font.BOLD, 16));
	//	this.content = Game.Content();
	}
	
	public void setText(String name) {
		this.text.setText(name);
	}
	public String getText() {
		return this.text.getText();
	}

	//This function is a placeholder and should be overriden in derived specific buttons
	public void action() {

	}
	
}

