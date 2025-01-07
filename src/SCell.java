// Add your documentation below:

public class SCell implements Cell {
    private String line;
    private int type;
    // Add your code here

    public SCell(String s) {
        // Add your code here
        line = s;
        setData(s);
    }

    @Override
    public int getOrder() {

        // Add your code here

        return 0;
        // ///////////////////
    }

    //@Override
    @Override
    public String toString() {
        return getData();
    }

    @Override
    public void setData(String s) {
        // Add your code here
        line = s;
        /////////////////////
    }
    @Override
    public String getData() {

        return line;
    }

    @Override
    public int getType() {

        return type;
    }

    @Override
    public void setType(int t) {

        type = t;
    }

    @Override
    public void setOrder(int t) {
        // Add your code here

    }

    public static boolean isCell(String a) {
        boolean ans = true;
        String regex = "^[A-Za-z][0-99]"; // צריך להיות הצמד לסוף
        if (!a.matches(regex)) {
            ans = false;
        }
        return ans;
    }

    /**
     * this function checks if a given string is a number
     *
     * @param num
     * @return
     */
    public static boolean isNumber(String num){
        boolean ans = true;
        try {
            Double.parseDouble(num); //try casting to double
        }
        catch (NumberFormatException e) {
            ans = false; // if casting fails num isn't a valid number
        }
        return ans;
    }
    /**
     * this function checks is a given string is in the form of a text
     * @param num
     * @return true when num is a text
     */
    public static boolean isText(String num){
        boolean ans = true;
        if (num.charAt(0) == '=' || isNumber(num) || isForm(num)) { //if num is either formula or just a number
            ans = false;
        }

        return ans;
    }

    /**
     * this function checks if a given string is in the form of a formula
     * @param num
     * @return true when num is a valid formula
     */
    public static boolean isForm(String num) {
        boolean ans = true;
        if (num.charAt(0) != '='){
            ans = false;
        }
        else {
            num = num.substring(1);
            if (!valForm(num)){
                ans = false;
            }

        }

        return ans;
    }

    double computeForm (String num) {
        double ans = 0;

        return ans;

    }

    public static boolean valForm(String num){
//        boolean ans = true;
        if (isNumber(num)){ // if num is a number
            return true;
        }
        if (isCell(num)){ // if num is a cell
            return true;
        } // remove unnecessary parentheses//////////////////////////////////////////////////////////////////
        if ( valForm(num.substring(0 , mainOpIndex(num)-1)) && valForm(num.substring(mainOpIndex(num)+1))){ //both sides of op index are forms
            return true;
        }
        return false;
//        return ans;
    }

    public static int mainOpIndex (String num){
        double counter = 0 ;
        int maimIndex = -1;
        double minCount = 0 ;

        for (int i = 0; i < num.length(); i++){
            if (num.charAt(i) == '*' || num.charAt(i) == '/'){
                counter -= 0.25; // value of *\/
                if (counter <= minCount){ // if value of arithmetic is the smallest - last to be calc'
                    maimIndex = i; // save index of minimal value arithmetic
                    minCount = counter;
                }
            }
            if (num.charAt(i) == '-' || num.charAt(i) == '+'){
                counter -= 0.5; // value of +\-
                if (counter <= minCount){ // if value of arithmetic is the smallest - last to be calc'
                    maimIndex = i; // save index of minimal value arithmetic
                    minCount = counter;
                }
            }
            if (num.charAt(i) == '('){
                counter ++;
            }
            if (num.charAt(i) == ')'){
                counter = 0;
            }
        }
        return maimIndex;
    }

    /**
     * this function checks if a string contains parentheses in the right format (for every opening parentheses there is a correlating closing one)
     * @param num the given string
     * @return true when parentheses are valid
     */
    public static boolean parentheses(String num){
        int count = 0 ;
        for (int i = 0; i < num.length(); i++){
            if (num.charAt(i) == '('){ // count amount of '('
                count++;
            }
            if (num.charAt(i) == ')'){ // count amount of ')'
                count--;
            }
            if (count <0){ //if there are more ')' than there are '(' - invalid
                return false;
            }
        } //end of for
        if (count != 0 ){ // if '(' and ')' amounts aren't equal
            return false;
        }
        return true;
    }





}
