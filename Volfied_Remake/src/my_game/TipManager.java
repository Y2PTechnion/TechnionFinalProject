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
    private int             currentTipIndex = 0;
    private long            lastUpdateTime = 0;
    private final long      TIP_INTERVAL_MS  = 10000; // 10 seconds
    private final String    TIP_GUID = "motivational_tip";

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
            "The future belongs to those who believe in their dreams."
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
            content.tipLine().showText(GetNameButton.getPlayerName() + " " + tip, Color.RED, 4000);
        }
    }
}
