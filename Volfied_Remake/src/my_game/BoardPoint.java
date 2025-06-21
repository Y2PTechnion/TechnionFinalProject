package my_game;

/**
 * BoardPoint class
 * 
 * @implNote BoardPoint is used for point in Board coordinates, which is the grid. 
 *              Transformation to pixels (ScreenPoints) will be done by the board class.
 *
 *           <p>
 *           Bugs: (a list of bugs and other problems)
 * 
 * @author (YuvalYossiPablo)
 */
public class BoardPoint {
//  Private variables for the class
    private int x   = 0;
    private int y   = 0;

    public BoardPoint(int x, int y) {
        this.x  = x;
        this.y  = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x  = x;
    }

    public void setY(int y) {
        this.y  = y;
    }

    public boolean isEqual(BoardPoint boardPoint) {
        boolean bIsEqual    = false;
        if (this.x == boardPoint.getX()
            && this.y == boardPoint.getY())
        {
            bIsEqual    = true;
        }

        return bIsEqual;
    }
}