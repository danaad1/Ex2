
public class Cell {

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
            double forMe = Double.parseDouble(num); // just checking
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
        }



        return false;
//        return ans;
    }

    public int mainOp (String num){
        double counter = 0 ;
        int maimIndex = -1;
        double minCount = 0 ;

        for (int i = 0; i < num.length(); i++){
            if (num.charAt(i) == '*' || num.charAt(i) == '/'){
                counter += 0.5;
                if (counter <= minCount){
                    maimIndex = i;
                    minCount = counter;
                }
            }
            if (num.charAt(i) == '-' || num.charAt(i) == '+'){
                counter += 0.25;
                if (counter <= minCount){
                    maimIndex = i;
                    minCount = counter;
                }
            }
            if (num.charAt(i) == ')'){
                counter = 0;
            }
        }
        return maimIndex;
    }

    public boolean parentheses (String num){
        int count = 0 ;
        for (int i = 0; i < num.length(); i++){
            if (num.charAt(i) == '('){
                count++;
            }
            if (num.charAt(i) == ')'){
                count--;
            }
            if (count <0){
                return false;
            }
        } //end of for
        if (count != 0 ){
            return false;
        }
        return true;
    }


    /**
     * this private function checks if a given string is a valid cell [A-Z][0-99]
     * @param a
     * @return
     */
    public static boolean isCell(String a){
        boolean ans = true;
        String regex = "^[A-Z][0-99]";
        if (!a.matches(regex)) {
            ans = false;
        }
        return ans;
    }

}
