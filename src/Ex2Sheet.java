import java.io.IOException;
import java.util.ArrayList;
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
        // Add your code here

//        SCell c = get(x, y);
        if (a != null) {
            ans = eval(x, y);
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

        for (int d=0; d<10; d++) {
            for (int i = 0; i < width(); i++) {
                for (int j = 0; j < height(); j++) {
                    if (dd[i][j] == currentDepth) {
                        value(i, j);
                    }
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
        String ans = "";
        SCell c = get(x, y);
        if (c != null) {
            switch (c.getType()) {
                case -1:
                    ans = "error";
                    break;
                case 1: // text
                    ans = c.getData();
                    break;
                case 2: // number
                    ans = String.valueOf(Double.parseDouble(c.getData()));
                    break;
                case 3: // formula
                    ArrayList<String> cellList = new ArrayList<String>();
                    cellList.add((char)(x+65)+String.valueOf(y));
                    ans = String.valueOf(computeForm(c, cellList));
            }
//            ans = c.toString();
        }
        // Add your code here

        /////////////////////
        return ans;
    }
//    public boolean containsCellLoop(SCell cell) {
//        boolean ans = false;
////        String content = cell.getData();
//        ArrayList<String> cellList = new ArrayList<String>();
//        cellList.add(String.valueOf(cell));
//        if (containsValCellName(cell)){
//
//
//        }
//       return ans;
//    }


    public double computeForm (SCell cell, ArrayList<String> cellList) {
//        String str = getData();
//        if (str.charAt(0)=='='){
//            str = str.substring(1);
//        }
//        return computeFormPart(str, sheet);
//        ArrayList<String> cellList = new ArrayList<String>();
//        cellList.add(this);
        return computeFormPart(cell, cell.getData(), cellList);
    }

    public double computeFormPart (SCell cell, String form, ArrayList<String> cellList ) {
        double ans = 0;

//        String form = cell.getData();

        if (form.charAt(0)=='='){
            form = form.substring(1);
        }

        if (SCell.parentheses(form)){
            form = SCell.removeParen(form); // remove unnecessary parentheses
        }

        if (SCell.isNumber(form)){ // if num is a number
            ans = Double.parseDouble(form);
        }
        else {
            if (SCell.isCell(form)) {// if num is a cell
//                SCell c = new SCell(form);
                if (cellList.contains(form)){
//                    SCell c = get(0,0);
                    cell.setType(-1);
                    return 0;
                    //Ex2Utils.ERR_CYCLE_FORM;
                }
                cellList.add(form);
                String ref = this.get(form).getData();
                ans = computeFormPart(cell, ref, cellList); //compute the content of the cell
            }
            else{

                int opIndex = SCell.mainOpIndex(form);
                double left = computeFormPart(cell, form.substring(0 , opIndex), cellList);
                double right = computeFormPart(cell, form.substring(opIndex+1), cellList);

                char op = form.charAt(opIndex) ;  //   "+" : "-" : "/" : "*;
                switch (op) {
                    case '+':
                        ans = left + right;
                        break;
                    case '-':
                        ans = left - right;
                        break;
                    case '/':
                        ans = left / right;
                        break;
                    case '*':
                        ans = left * right;
                        break;
                }
            }



        }
        return ans;
    }


    /**
     * this function receives a cell represented as a string (i.e A0) and returns its numeric value as a string (i.e 00)
     * @param s a cell represented as a string (i.e A0)
     * @return the cell numeric value as a string (i.e 00)
     */
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

    public boolean containsValCellName(SCell c) {
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
        return found;
    } //containsValCellName

//    public String findCell (String str){
//
//
//    }
//
//    public boolean cellLoop (SCell cell){ //to be called only if cell contains formula
//        ArrayList<String> cellList = new ArrayList<String>();
//        String thisCell = cellVal(String.valueOf(cell));
//        cellList.add(thisCell);
//
//       String a = cell.getData().substring(1); // -"="
//        if (SCell.mainOpIndex(a)==-1){
//            if (cellList.contains(a)){
//                return true;
//            }
//
//        }
//        else{
//
//        }
//    }


}
