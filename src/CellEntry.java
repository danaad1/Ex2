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
        String regex = "[0-25]"; // Valid x-range: 0 to 25
        String REGEX ="[0-99]"; // Valid y-range: 0 to 99
        // Check if both x and y match the valid ranges
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
      String ans = "invalid index"; // Default message if the CellEntry is invalid
        if (isValid()){ // Check if the CellEntry is valid
            String a = String.valueOf((char) ('A' + getX())); // Convert x-coordinate to corresponding letter
            String l = String.valueOf(getY()); // Convert y-coordinate to string
            ans = a + l; // Combine letter and number to form the final cell reference
        }

        return ans;
    }
}
 //to string //