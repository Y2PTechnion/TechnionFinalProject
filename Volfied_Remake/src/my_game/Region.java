package my_game;

public class Region {
//  Private variables for the class
	private         BoardPoint  location                                = null;
	private         boolean     visible                                 = true;
    // the id of the graphic element that represents this entity
	private         String      guid                                    = ""; 
    private static  int         MAXIMUM_NUMBER_OF_REGIONS_IN_GRID       = 0;
    private static  int         currentNumberOfConqueredRegionsInGrid   = 0;
	
	public Region (int x, int y) {
        this.location                           = new BoardPoint(x, y);
		this.guid                               = "lp_"+ x + "_" + y;
        this.visible                            = true;
        currentNumberOfConqueredRegionsInGrid   = 0;
	}
	
    public static void setMaximumNumberOfRegionsInGrid(int maximumNumberOfRegionsInGame) {
        MAXIMUM_NUMBER_OF_REGIONS_IN_GRID   = maximumNumberOfRegionsInGame;
    }

    public static int getMaximumNumberOfRegionsInGrid() {
        return (MAXIMUM_NUMBER_OF_REGIONS_IN_GRID);
    }

    public static void resetConqueredRegions() {
        currentNumberOfConqueredRegionsInGrid   = 0;
    }

    public static void setConqueredRegion() {
        currentNumberOfConqueredRegionsInGrid++;
    }

    public static int getNumberOfConqueredRegions() {
        return (currentNumberOfConqueredRegionsInGrid);
    }

    public static int getNumberOfUnconqueredRegions() {
        return (MAXIMUM_NUMBER_OF_REGIONS_IN_GRID - currentNumberOfConqueredRegionsInGrid);
    }

	public void hide() {
		visible = false;
	}

	public void show() {
		visible = true;
	}
	
	public String getGuid() {
		return guid;
	}
	
	public BoardPoint getLocation() {
		return location;
	}
	
	public boolean isShown() {
		return visible;
	}
}