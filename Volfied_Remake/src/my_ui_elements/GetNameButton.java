package my_ui_elements;

import ui_elements.GameButton;
import ui_elements.InputDialog;

public class GetNameButton extends GameButton{
	
    private static String playerNameString = "";

	public GetNameButton(String id, String name, int width, int height, int posX, int posY) {
		super(id, name, width, height, posX, posY);
	}

    public static String getPlayerName() 
    {
        return playerNameString;
    }

    private void setPlayerName(String playerName) {
        playerNameString = playerName;
    }

	@Override
	public void action() {
		// The basic buttonAction prints the id of the button to the console.
		// Keep the call to super to preserve this behavior or remove it if you don't want the printing.
		super.action();
		//Game.excelDB().getTable("pokimonMoves").sortByKey();
	    String playerNameString = InputDialog.showInputDialog("Enter your name:", "Submit");
        if (null != playerNameString && !playerNameString.isEmpty()) 
        {
            setPlayerName(playerNameString);
            // Optionally, you can also print the name to the console or use it in your game logic.
            System.out.println("User input: " + playerNameString);
        } 
        else 
        {
            System.out.println("No input provided.");
        }
	}
}
