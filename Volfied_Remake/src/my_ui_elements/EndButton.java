package my_ui_elements;

import base.Game;
import my_base.MyContent;
import ui_elements.GameButton;

public class EndButton extends GameButton {
	
    private static boolean endButtonPushed = false;

	public EndButton(String id, String name, int width, int height, int posX, int posY) {
		super(id, name, width, height, posX, posY);
	}

    public static boolean endButtonPushed()
    {
        return endButtonPushed;
    }

	@Override
	public void action() {
		// The basic buttonAction prints the id of the button to the console.
		// Keep the call to super to preserve this behavior or remove it if you don't want the printing.
		super.action();
		//Game.excelDB().getTable("pokimonMoves").sortByKey();
        MyContent content = (MyContent) Game.Content();
//        content.gameOverShow(350, 320);
        endButtonPushed = true;
//		Game.endGame();
//		Game.excelDB().commit();
//		((MyContent)(Game.Content())).pokimon().showLastMoves();

	}

}
