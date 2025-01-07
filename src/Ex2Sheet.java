import java.io.IOException;
// Add your documentation below:

public class Ex2Sheet implements Sheet {
    private Cell[][] table;
    // Add your code here

    // ///////////////////
    public Ex2Sheet(int x, int y) {
        table = new SCell[x][y];
        for(int i=0;i<x;i=i+1) {
            for(int j=0;j<y;j=j+1) {
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
        Cell a = table[x][y];
        int cellType = a.getType();
//        TEXT=1, NUMBER=2, FORM=3, ERR_FORM_FORMAT=-2, ERR_CYCLE_FORM=-1, ERR=-1

        switch(cellType) {
            case  1: // text
                ans = a.getData().toString(); // צריך טוסטרינג?
            case 2: // number
                ans = a.getData().toString(); // צריך טוסטרינג?
            case 3: //form
                ans = eval(x, y);
            case -2: //ERR_FORM_FORMAT
            case -1:  //ERR_CYCLE_FORM
        }

        // Add your code here

        Cell c = get(x,y);
        if(c!=null) {
            ans = c.toString();
        }

        /////////////////////
        return ans;
    }

    @Override
    public Cell get(int x, int y) {

        return table[x][y];
    }

    @Override
    public Cell get(String cords) {
        Cell ans = null;
        // Add your code here

        /////////////////////
         String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String xCord = ""; // x coordinates
        String yCord = ""; // y coordinates
        String cell = "[" + xCord + "]" + "[" + yCord + "]" ;  // the string form of the cell
        if (isCell(cords)){
            xCord = String.valueOf(letters.indexOf(cords.charAt(0))); //the string value of the cell letter
            yCord = cords.substring(1); // only the number of the cell
            ans.setData(cell); // change cell and accordingly
        }
        if (isIn(Integer.parseInt(xCord), Integer.parseInt(yCord))) {
            ans = null;
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
        Cell c = new SCell(s);
        table[x][y] = c;
        // Add your code here

        /////////////////////
    }
    @Override
    public void eval() {
        int[][] dd = depth();
        // Add your code here

        // ///////////////////
    }

    @Override
    public boolean isIn(int xx, int yy) {
        boolean ans = xx>=0 && yy>=0;
        // Add your code here

        /////////////////////
        return ans;
    }

    @Override
    public int[][] depth() {
        int[][] ans = new int[width()][height()];
        // Add your code here

        // ///////////////////
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
        if(get(x,y)!=null) {ans = get(x,y).toString();}
        // Add your code here

        /////////////////////

        return ans;
    }

    public static boolean isCell(String a){
        boolean ans = true;
        String regex = "^[A-Z][0-99]";
        if (!a.matches(regex)) {
            ans = false;
        }
        return ans;
    }
}
