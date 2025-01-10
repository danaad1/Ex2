import java.io.IOException;
// Add your documentation below:

public class Ex2Sheet implements Sheet {
    private SCell[][] table;
    // Add your code here

    // ///////////////////
    public Ex2Sheet(int x, int y) {
        table = new SCell[x][y];
        for (int i = 0; i < x; i = i + 1) {
            for (int j = 0; j < y; j = j + 1) {
                table[i][j] = new SCell(Ex2Utils.EMPTY_CELL);
            }
        }
        eval();
    }

    public Ex2Sheet() {
        this(Ex2Utils.WIDTH, Ex2Utils.HEIGHT);
    }

    @Override
    public String value(int x, int y) {
        String ans = Ex2Utils.EMPTY_CELL;
        SCell a = table[x][y];
//        int cellType = a.getType();

        // Add your code here

        SCell c = get(x, y);
        if (c != null) {
            if (c.getType() == 3) {
                ans = eval(x, y);
            }
//            switch (cellType) {
//                case 1: // text
//                    ans = a.getData(); // צריך טוסטרינג?
//                case 2: // number
//                    ans = a.getData().toString(); // צריך טוסטרינג?
//                case 3: //form
//                    ans = eval(x, y);
//                case -2: //ERR_FORM_FORMAT
//                case -1:  //ERR_CYCLE_FORM
//            }
            ans = c.toString();// מחזיר את הערך של התא אבל לא מחשב נוסחא

        }

        /////////////////////
        //        TEXT=1, NUMBER=2, FORM=3, ERR_FORM_FORMAT=-2, ERR_CYCLE_FORM=-1, ERR=-1
// אם פורמולה לחשב ולהחזיר את הערך של הפורמולה אם לא פורמולה לעשות
        return ans;
    }

    @Override
    public SCell get(int x, int y) {

        return table[x][y];
    }

    @Override
    public SCell get(String cords) {
        SCell ans = null;
        // Add your code here

        /////////////////////
//        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String cell = cellVal(cords);
        int xCord = Integer.parseInt(cell.substring(0, 1)); // x coordinates
        int yCord = Integer.parseInt(cell.substring(1));// y coordinates
        if (isIn(xCord, yCord)) { // if the cell is in the table
            ans = get(xCord, yCord);
        }
        return ans;
    }

    @Override
    public int width() {

        return table.length;
    }

    @Override
    public int height() {

        return table[0].length;
    }

    @Override
    public void set(int x, int y, String s) {
        if (isIn(x, y)){
        SCell c = new SCell(s);
        table[x][y] = c;
        }
    }

    @Override
    public void eval() {
        int[][] dd = depth();
        int currentDepth = -1;

        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                if (dd[i][j] == currentDepth){
                   value(i, j);
                }
            }
        }


        // Add your code here

        // ///////////////////
    }

    @Override
    public boolean isIn(int xx, int yy) {
        boolean ans = xx >= 0 && xx < 26 && yy >= 0 && yy < 100;

        // Add your code here

        /////////////////////
        return ans;
    }

    @Override
    public int[][] depth() {
        int[][] ans = new int[width()][height()];
        defultDepth(ans);
        int depth = 0;
        int count = 0;
        int max = width()*height();
        boolean computable = true;

        while (count < max && computable) {
            computable = false;
            for (int i = 0; i < width(); i++) {
                for (int j = 0; j < height(); j++) {
                    if(canBeComputedNow(i,j)) { // if
                        ans [i][j] = depth;
                        count ++;
                        computable = true;
                    }
                }
            } // end for
            depth ++;
        } //end while
        return ans;
    }

    public boolean canBeComputedNow (int x, int y) {
        boolean ans = false;
        SCell a = get(x, y);
//        String x = a.setData();
        if (a.getType() == 3 && containsValCellName(a)) {
            ans = false;
        }



        return ans;
    }

    @Override
    public void load(String fileName) throws IOException {
        // Add your code here

        /////////////////////
    }

    @Override
    public void save(String fileName) throws IOException {
        // Add your code here

        /////////////////////
    }

    @Override
    public String eval(int x, int y) {
        String ans = null;
        Cell c = get(x, y);
        if (get(x, y) != null) {
            ans = c/*get(x, y)*/.toString();
        }
        // Add your code here

        /////////////////////

        if(c.getType() == 3) {


        }

        return ans;
    }

//    public static boolean isCell(String a) {
//        boolean ans = true;
//        String regex = "^[A-Za-z][0-99]"; // צריך להיות הצמד לסוף
//        if (!a.matches(regex)) {
//            ans = false;
//        }
//        return ans;
//    }

    public String cellVal(String s) {
        String ans = null;
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerLetters = letters.toLowerCase();
        String x = null;
        String y = null;
        if (SCell.isCell(s)) {
            x = s.substring(1); // only the number of the cell
            if (letters.contains(String.valueOf(s.charAt(0)))) { // if letter is upper case
                y = String.valueOf(letters.indexOf(s.charAt(0))); //the string value of the cell letter
            } else { //// if letter is lower case
                y = String.valueOf(lowerLetters.indexOf(s.charAt(0))); //the string value of the cell letter
            }

            ans = y + x;
        }
        return ans;
    }

    public void defultDepth(int[][] a) {
//        int[][] ans = new int[width()][height()];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
               a[i][j] = 0;     // TEMP: -1;
            }
        }
    }

    public boolean containsValCellName(Cell c) {
        boolean ans = false;
        boolean found = false;
        String checkForCell  = c.toString();
        for (int i = 0; i < checkForCell.length(); i++) {
            for (int j = i + 2; j <= checkForCell.length(); j++) {
                String substring = checkForCell.substring(i, j);
                if (SCell.isCell(substring)) {
                    found = true;
                }
            }
            if (found) {
                break;
            }
        }
        return ans;
    } //containsValCellName

}
