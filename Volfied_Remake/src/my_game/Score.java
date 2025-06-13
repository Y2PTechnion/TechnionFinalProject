package my_game;

/**
 * This is a simple class that manages the score.
 * It is responsible for resetting and increasing the score, as well managing its text.
 */
public class Score {
//  Private variables for the class
	private int     score                       = 0;
    private double  conqueredRegionsPercentage  = 0.0;
	
	public void reset() {
		score                       = 0;
        conqueredRegionsPercentage  = 0.0;
	}
	
	public void add(int points) {
		score += points;
	}

    public void setConqueredRegionsPercentage(double conqueredRegionsPercentage) {
        this.conqueredRegionsPercentage = conqueredRegionsPercentage;
    }
	
	public void subtract(int points ) {
		score -= points;
	}
	
	public int points() {
		return score;
	}
	
	public String guid() {
		return "score";
	}

	public String guidPercentage() {
		return "percentage";
	}
	
	public String getText() {
		return "1UP: " + score;
	}

	public String getPercentage() {
        String  stringConquredRegionsPercentage = String.format("%.2f", conqueredRegionsPercentage);
		return (stringConquredRegionsPercentage + "  %") ;
	}
}