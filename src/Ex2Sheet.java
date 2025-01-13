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
        if (isIn(x, y)) {
            SCell c = new SCell(s);
            table[x][y] = c;
        }
    }

    @Override
    public void eval() {
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                eval(i, j);
            }
        }
//        int[][] dd = depth();
//        int currentDepth = -1;
//
//        for (int d = 0; d < 10; d++) {
//            for (int i = 0; i < width(); i++) {
//                for (int j = 0; j < height(); j++) {
//                    if (dd[i][j] == currentDepth) {
//                        value(i, j);
//                    }
//                }
//            }
//        }

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

    /**
     * Calculates the depth of each cell in a 2D grid by recursively determining the number of steps required to resolve
     * its formula, considering any referenced cells. If a cyclic reference is detected, it returns an error value
     * (Ex2Utils.ERR_CYCLE_FORM).
     * @return A 2D array representing the depth of each cell. If a cyclic reference is encountered,
     *  *         the value will be `Ex2Utils.ERR_CYCLE_FORM`.
     */
    @Override
    public int[][] depth() {
        int[][] ans = new int[width()][height()];
        // Iterate over all cells to compute their depth
        for(int i = 0; i < width(); i++) {
            for(int j = 0; j < height(); j++) {
                ans[i][j] = cellDepth( i, j, new boolean[width()][height()]);
            }
        }




//        defultDepth(ans);
//        int depth = 0;
//        int count = 0;
//        int max = width() * height();
//        boolean computable = true;
//
//        while (count < max && computable) {
//            computable = false;
//            for (int i = 0; i < width(); i++) {
//                for (int j = 0; j < height(); j++) {
//                    if (canBeComputedNow(i, j)) { // if
//                        ans[i][j] = depth;
//                        count++;
//                        computable = true;
//                    }
//                }
//            } // end for
//            depth++;
//        } //end while
        return ans;
    }

    /**
     * Recursively computes the depth of a cell by resolving its formula and calculating the depth of referenced cells.
     * If a cyclic reference is detected, the method returns an error value (Ex2Utils.ERR_CYCLE_FORM).
     * @param x x-coordinate (column index) of the cell to be evaluated
     * @param y The y-coordinate (row index) of the cell to be evaluated.
     * @param visited A 2D boolean array to track cells that have  already  appeared in the recursive calculation to detect cycles
     * @return
     */
    public int cellDepth (int x, int y, boolean [][] visited) {
        int ans = 0;
        // If the cell has already been visited, return a cyclic reference error
        if (visited[x][y]) {
            return Ex2Utils.ERR_CYCLE_FORM;
        }
        // Retrieve the cell at position (x, y)
        SCell cell = get(x, y);
        if (cell.getType() != 3 ){ // if is formula
            return ans;
        }
        // Mark the current cell as visited to track the recursion stack
        visited[x][y] = true;

        String data = cell.getData();
        // If the cell contains a formula, process it
        if (data.startsWith("=")){
            String form = data.trim().substring(1);

            // Handle negative signs in the formula
            if (form.startsWith("-")){
                form = form.trim().substring(1);
            }

            if (form.matches("[A-Za-z]\\d+")){ // if form is in cel format
                SCell ref = get(form); // get the reference and compute its depth recursively
                if(ref != null){

                    int columnX = form.toUpperCase().charAt(0) - 'A'; // Extract the X indices from the cell reference
                    int rowY = Integer.parseInt(form.substring(1)); // Extract the y indices from the cell reference

                    // Recursively calculate the depth of the referenced cell
                    int depth = cellDepth(columnX, rowY, visited);

                    // If a cycle is detected in the reference, return the error code
                    if(depth == Ex2Utils.ERR_CYCLE_FORM){
                        visited[x][y] = false;
                        return Ex2Utils.ERR_CYCLE_FORM;
                    }
                    // Otherwise, update the depth of the current cell
                    ans = Math.max(ans, depth + 1);
                }
            }
        }
        // Mark the current cell as not visited before returning from the recursion
        visited[x][y] = false;
        return ans;
    }

    public boolean canBeComputedNow(int x, int y) {
        boolean ans = false;
        SCell a = get(x, y);
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
                case -2:
                    ans = "ERR_FORM_FORMAT";
                    break;
                case -1:
                    ans = "ERR_CYCLE_FORM";
                    break;
                case 1: // text
                    ans = c.getData();
                    break;
                case 2: // number
                    ans = String.valueOf(Double.parseDouble(c.getData()));
                    break;
                case 3: // formula
                    ArrayList<String> cellList = new ArrayList<String>();
                    cellList.add((char) (x + 65) + String.valueOf(y));
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


    public double computeForm(SCell cell, ArrayList<String> cellList) {
//        String str = getData();
//        if (str.charAt(0)=='='){
//            str = str.substring(1);
//        }
//        return computeFormPart(str, sheet);
//        ArrayList<String> cellList = new ArrayList<String>();
//        cellList.add(this);
        return computeFormPart(cell, cell.getData(), cellList);
    }

    public double computeFormPart(SCell cell, String form, ArrayList<String> cellList) {
        double ans = 0;

        // If the formula is empty, return 0 and set cell type to -2
        if (form.isEmpty()){
            cell.setType(-2);
            return ans;
        }
        // Remove leading '=' and handle signs if the formula starts with one
        if (form.startsWith("=")) {
            form = form.substring(1);
            if (form.startsWith("-") || form.startsWith("+")) {
                form = "0" + form;
            }
        }

        if (SCell.parentheses(form)) {
            form = SCell.removeParen(form); // remove unnecessary parentheses
        }
        else{
           cell.setType(-2);
           return ans;
        }
            // If the formula is a number, parse it and return
        if (SCell.isNumber(form)) { // if num is a number
                ans = Double.parseDouble(form);
        } else {
            if (SCell.isCell(form)) {// if num is a cell
//                SCell c = new SCell(form);
                    if (cellList.contains(form)) {
//                    SCell c = get(0,0);
                        cell.setType(-1);
                        return 0;
                        //Ex2Utils.ERR_CYCLE_FORM;
                    }
                    cellList.add(form);
                    SCell refCell = this.get(form);
                    //if the reference cell contains an error
                    if (refCell.getType() == -2){
                        cell.setType(-2);
                        return ans;
                    }
                    String ref = this.get(form).getData();
                    ans = computeFormPart(cell, ref, cellList); //compute the content of the cell
                } else {

                    int opIndex = SCell.mainOpIndex(form);
                    double left = computeFormPart(cell, form.substring(0, opIndex), cellList);
                    double right = computeFormPart(cell, form.substring(opIndex + 1), cellList);

                    char op = form.charAt(opIndex);  //   "+" : "-" : "/" : "*;
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
     *
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
        String checkForCell = c.toString();
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
