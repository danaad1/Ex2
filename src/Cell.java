
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
            Double.parseDouble(num);
            double forMe = Double.parseDouble(num); // just checking
        }
        catch (NumberFormatException e) {
            ans = false;
        }
        return ans;
    }
    public boolean isText (String num){
        boolean ans = true;
        

        return ans;
    }

    public boolean isForm (String num) {
        boolean ans = true;
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


}
