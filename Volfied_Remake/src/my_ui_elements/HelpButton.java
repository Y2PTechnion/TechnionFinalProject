package my_ui_elements;

import shapes.Shape.STATUS;
import ui_elements.GameButton;
import ui_elements.GameCheckbox;


public class HelpButton extends GameButton{

	public HelpButton(String id, String name, int width, int height, int posX, int posY) {
		super(id, name, width, height, posX, posY);
	}

	@Override
	public void action() {
		// The basic buttonAction prints the id of the button to the console.
		// Keep the call to super to preserve this behavior or remove it if you don't want the printing.
		super.action();

        GameCheckbox gameCheckBoxString   = new GameCheckbox("HelpButton", "Pablo\nYuval\nYossi", 40, 40, 200, 100, true);
        gameCheckBoxString.setSelected(true);
        gameCheckBoxString.setStatus(STATUS.SHOW);
	}
}
