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
public class BoardPoint 
{
//  Private variables for the class
    private int row     = 0;
    private int column  = 0;

    public BoardPoint(int row, int column) 
    {
        this.row    = row;
        this.column = column;
    }

    public int getColumn() 
    {
        return this.column;
    }

    public int getRow() 
    {
        return this.row;
    }

    public void setColumn(int column) 
    {
        this.column = column;
    }

    public void setRow(int row) 
    {
        this.row    = row;
    }

    public boolean isEqual(BoardPoint boardPoint) 
    {
        boolean bIsEqual    = false;
        if (this.column == boardPoint.getColumn()
            && this.row == boardPoint.getRow())
        {
            bIsEqual    = true;
        }

        return bIsEqual;
    }
}