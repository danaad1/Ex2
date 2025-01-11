// Add your documentation below:

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
            setType(Ex2Utils.NUMBER);
        }
        if(isForm(s)){
            setType(Ex2Utils.FORM);
        }
        if(isText(s)){
            setType(Ex2Utils.TEXT);
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

        if (num.isEmpty() || num.charAt(0) == '=' || isNumber(num) || isForm(num)) { //if num is either formula or just a number
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
        if (num.isEmpty()){
            return false;
        }

        if (num.charAt(0) != '=') { // if the first index isn't '=' - not formula
            ans = false;
        } else {
            num = num.substring(1);
            num = removeParen(num);
            if (num.isEmpty() || !valForm(num)) { // if there's only = in num and the rest isn't a valid formula
                ans = false;
            }
        }

        return ans;

    }

    public double computeForm () {
        return computeFormPart(getData().substring(1));
    }

    public double computeFormPart (String form) {
        double ans = 0;
        if (parentheses(form)){
            form = removeParen(form); // remove unnecessary parentheses
        }

        if (isNumber(form)){ // if num is a number
            ans = Double.parseDouble(form);
        }
        else {
            if (isCell(form)) {// if num is a cell
                SCell c = new SCell(form);
                ans = computeFormPart(c.getData()); //compute the content of the cell
            }
            else{
//                int opIndex = mainOpIndex(form);
//                if (parentheses(form)){
//                    form = removeParen(form); // remove unnecessary parentheses
////                    opIndex = mainOpIndex(form);
//                }
                int opIndex = mainOpIndex(form);
                double left = computeFormPart(form.substring(0 , opIndex));
                double right = computeFormPart(form.substring(opIndex+1));

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

    public static boolean valForm(String num){
//        boolean ans = true;
        if (parentheses(num)) {
            num = removeParen(num);
        }
        if (isNumber(num)){ // if num is a number
            return true;
        }
        if (isCell(num)){ // if num is a cell
            return true;
        }
//        if (parentheses(num)){
//            num = removeParen(num); // remove unnecessary parentheses
//            int opIndex = mainOpIndex(num);
//            if ( valForm(num.substring(0 , opIndex)) && valForm(num.substring(opIndex+1))){ //both sides of op index are forms
//                return true;
//            }
//        }
        int opIndex = mainOpIndex(num);
        if (opIndex == -1){
            return false;
        }
        if ( valForm(num.substring(0 , mainOpIndex(num))) && valForm(num.substring(mainOpIndex(num)+1))){ //both sides of op index are forms
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

}
