// Add your documentation below:

import java.util.ArrayList;

public class SCell implements Cell {
    private String line;
    private int type;
    // Add your code here

    public SCell(String s) {
        // Add your code here
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

        if (isNumber(s)){
            setType(Ex2Utils.NUMBER); // If the data is a number, set the type to NUMBER
        }
        if(isForm()){
            setType(Ex2Utils.FORM); // If the data is a formula, set the type to FORM
        }
        if(isText()){
            setType(Ex2Utils.TEXT); // If the data is text, set the type to TEXT
        }
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

    /**
     *Checks if the provided string a follows a valid cell format, consisting of a letter (A-Z or a-z) followed by a
     * number (0-99). Returns true if the string matches this format, and false otherwise.
     * @param a The string to check. It is expected to represent a cell reference in
     *  *          the format of a letter followed by a number (e.g., "A1", "Z99").
     * @return `true` if the string `a` is in a valid cell format, otherwise `false`.
     */
    public static boolean isCell(String a) {
        boolean ans = true;
        String regex = "^[A-Za-z][0-99]"; // Regex pattern for cell format: a letter followed by a number
        if (!a.matches(regex)) {
            ans = false; // If the string does not match the format, set ans to false
        }
        return ans; // Return whether the string matches the cell format
    }

    /**
     * this function checks if a given string is a number
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
     * @return true when num is a text
     */
    public boolean isText(){
        boolean ans = true;
        String num = this.getData();
        // Check if the data is empty, a formula (starts with '='), a number, or a formula
        if (num.isEmpty() || num.charAt(0) == '=' || isNumber(num) || isForm()) { //if num is either formula or just a number
            ans = false; // If any of these conditions are true, it's not text
        }
        return ans; // Return whether the data is text
    }

    /**
     * this function checks if a given string is in the form of a formula
     * @return true when num is a valid formula
     */
    public boolean isForm(/*String num*/) {
        boolean ans = true;
        String str = getData();
        // If the string is empty, it cannot be a valid formula
        if (str.isEmpty()){
            return false;
        }

        // Check if the first character is '=', indicating a formula
        if (str.charAt(0) != '=') { // if the first index isn't '=' - not formula
            ans = false; // If the first character is not '=', it's not a formula
        } else {
            str = str.substring(1); // Remove the '=' sign for further checking

            // Allow optional signs (+ or -) at the start of the formula
            if (str.startsWith("-") || str.startsWith("+")){
                str = str.substring(1);
            }
            // Remove parentheses (if any) from the formula
            str = removeParen(str);

            // If the formula is empty after removing parentheses or does not match a valid formula pattern, return false
            if (str.isEmpty() || !valForm(str)) { // if there's only = in num and the rest isn't a valid formula
                ans = false; // Invalid formula format
                setType(Ex2Utils.ERR_FORM_FORMAT); // Set error type for invalid formula format
            }
        }

        return ans;

    }

    /**
     * Validates if the given string num is a valid formula, which can be a number, cell reference, or a mathematical
     * expression with operators, recursively checking both sides of any operators.
     * @param num num The string to validate, which represents a formula.
     * @return  `true` if the string `num` is a valid formula, and `false` otherwise.
     */
    public static boolean valForm(String num){
        // If the string contains parentheses, remove them
        if (parentheses(num)) {
            num = removeParen(num);
        }
        // Check if the string is a valid number
        if (isNumber(num)){ // if num is a number
            return true;
        }

        // Check if the string is a valid cell reference
        if (isCell(num)){ // if num is a cell
            return true;
        }

        // Find the main operator in the formula
        int opIndex = mainOpIndex(num);
        if (opIndex == -1){
            return false; // No operator found, so the formula is invalid
        }
        // Recursively validate both sides of the operator
        if ( valForm(num.substring(0 , mainOpIndex(num))) && valForm(num.substring(mainOpIndex(num)+1))){ //both sides of op index are forms
            return true; // Both sides of the operator are valid formulas
        }
        return false; // The formula is invalid
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
                    counter = 0 ; // TODO: לבדוק אם זה טוב
                }
            }
            if (num.charAt(i) == '-' || num.charAt(i) == '+'){
                counter -= 0.5; // value of +\-
                if (counter <= minCount){ // if value of arithmetic is the smallest - last to be calc'
                    maimIndex = i; // save index of minimal value arithmetic
                    minCount = counter;
                    counter = 0 ; // TODO: לבדוק אם זה טוב

                }
            }
            if (num.charAt(i) == '('){
                counter ++;
            }
            if (num.charAt(i) == ')'){
//                counter --;
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
    public static boolean parentheses (String num){
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

    /**
     * this function
     * @param num
     * @return
     */
    public static String removeParen (String num){
        String ans = num;
        int count = -1;
        if (!num.isEmpty() && num.charAt(0) == '('){// if the first index is an open parentheses
            count = 1;
            num = num.substring(1);
            int index = 0; // the index of the closing parentheses
            for (int i = 0; i < num.length(); i++) { //check if the correlating parentheses for the first index is the last
                String a = num.charAt(i) + "";
                switch (a) {
                    case "(":
                        count++;
                        break;
                    case ")":
                        count--;
                        break;
                }
                if (count == 0) {
                    index = i;
                    break;
                }
            }
            int w = num.length();
            if (index == num.length() - 1) {
                ans = removeParen(num.substring(0, index));
            }
        }
        return ans;
    }

//    public double computeForm(String form){
//        double ans = 0;
//        if (isNumber(form)){ // if num is a number
//            ans = Double.parseDouble(form);
//        }
//        if (isCell(form)){ // if num is a cell
//            ans = computeForm(form.getD) ;
//        }
//        if (parentheses(form)){
//            removeParen(form); // remove unnecessary parentheses
//            int opIndex = mainOpIndex(form);
//            if ( valForm(num.substring(0 , opIndex)) && valForm(num.substring(opIndex+1))){ //both sides of op index are forms
//                return true;
//            }
//        }
//        if ( valForm(num.substring(0 , mainOpIndex(num)-1)) && valForm(num.substring(mainOpIndex(num)+1))){ //both sides of op index are forms
//            return true;
//        }
//        return false;
//    }
    //    public double computeForm (Ex2Sheet sheet) {
////        String str = getData();
////        if (str.charAt(0)=='='){
////            str = str.substring(1);
////        }
////        return computeFormPart(str, sheet);
//        ArrayList<String> cellList = new ArrayList<String>();
//        cellList.add("j");
//        return computeFormPart(getData(), sheet, cellList);
//    }
//
//    public double computeFormPart (String form, Ex2Sheet sheet, ArrayList<String> cellList ) {
//        double ans = 0;
//
//        if (form.charAt(0)=='='){
//            form = form.substring(1);
//        }
//
//        if (parentheses(form)){
//            form = removeParen(form); // remove unnecessary parentheses
//        }
//
//        if (isNumber(form)){ // if num is a number
//            ans = Double.parseDouble(form);
//        }
//        else {
//            if (isCell(form)) {// if num is a cell
////                SCell c = new SCell(form);
//                if (cellList.contains(form)){
//                    return Ex2Utils.ERR_CYCLE_FORM;
//                }
//                cellList.add(form);
//                String ref = sheet.get(form).getData();
//                ans = computeFormPart(ref, sheet, cellList); //compute the content of the cell
//            }
//            else{
//
//                int opIndex = mainOpIndex(form);
//                double left = computeFormPart(form.substring(0 , opIndex), sheet, cellList);
//                double right = computeFormPart(form.substring(opIndex+1), sheet, cellList);
//
//                char op = form.charAt(opIndex) ;  //   "+" : "-" : "/" : "*;
//                switch (op) {
//                    case '+':
//                        ans = left + right;
//                        break;
//                    case '-':
//                        ans = left - right;
//                        break;
//                    case '/':
//                        ans = left / right;
//                        break;
//                    case '*':
//                        ans = left * right;
//                        break;
//                }
//            }
//
//
//
//        }
//        return ans;
//    }

}
