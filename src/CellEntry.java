// Add your documentation below:

public class CellEntry  implements Index2D {
    private int _x;
    private int _y;

    /**
     * this constructor
     * @param x
     * @param y
     */
    public CellEntry(int x, int y) {
      this._x = x;
      this._y = y;
    }
    public  CellEntry(String str) {
        if (SCell.isCell(str)){
            _x = Integer.parseInt(str.substring(0,1));
            _y = Integer.parseInt(str.substring(1));
        }
    }

    /**
     * this function checks if a CellEntry is in a valid cell form [A-Za-z][0-99]
     * @return true when CellEntry is valid
     */
    @Override
    public boolean isValid() {

        // add index is null || is empty
        String regex = "[0-25]";
        String REGEX ="[0-99]";
        if (String.valueOf(_x).matches(regex) && String.valueOf(_y).matches(REGEX)){
            return true;
        }
        return false;
    }

    /**
     * this function returns the x value of CellEntry
     * @return x value of CellEntry
     */
    @Override
    public int getX() {
        if(isValid()){
            return _x;
        }
        return Ex2Utils.ERR;
    }

    /**
     * this function returns the y value of CellEntry
     * @return y value of CellEntry
     */
    @Override
    public int getY() {
        if(isValid()){
            return _y;
        }
        return Ex2Utils.ERR;
    }
    @Override
    public String toString(){
      String ans = "invalid index";
        if (isValid()){
            String a = String.valueOf((char) ('A' + getX()));
            String l = String.valueOf(getY());
            ans = a + l;
        }

        return ans;
    }
}
 //to string //