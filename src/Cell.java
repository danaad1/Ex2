
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
    public boolean isText (String num){
        boolean ans = true;
        

        return ans;
    }

    /**
     * this function checks if a given string is in the form of a formula
     * @param num
     * @return true when num is a valid formula
     */
    public boolean isForm (String num) {
        boolean ans = true;
        String a = "123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ()+-*/"; //change, don't want it this way

        if (num.charAt(0) != '='){
            ans = false;
        }
        else {

        }



        return ans;
    }

    double computeForm (String num) {
        double ans = 0;

        return ans;

    }

    /**
     * this private function checks if a given string is a valid cell [A-Z][0-99]
     * @param a
     * @return
     */
    private boolean isCell(String a){
        boolean ans = true;
        String regex = "^[A-Z][0-99]";
        if (!a.matches(regex)) {
            ans = false;
        }
        return ans;
    }

    /**
     *
     * @param num
     * @return
     */
    private boolean valOpForm (String num) {
        boolean ans = true;
        return ans;
    }

}
