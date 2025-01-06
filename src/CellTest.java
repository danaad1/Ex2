import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class CellTest {

    @Test
    void isNumber() {
        String[] good = { "-35.32", "123", "21.03" , "0.002" , "2.6", "8.0" , "1.000" , "-9" , "-4" };
        for (int i = 0; i < good.length; i++) {
            boolean a = Cell.isNumber(good[i]);
            assertTrue(a);
        }

        String[] bad = {"sd56" , "1. 0" , "5.32a" , "58.%" , "89-3" };
        for (int i = 0; i < bad.length; i++) {
            boolean a = Cell.isNumber(bad[i]);
            assertFalse(a);
        }
    }

    @Test
    void isText() {
        String[] good = {"-4s", "biso", "abc", "hello world" , "2 *#" , "2..3", "8.0o" , "..." , "BSH" };
        for (int i = 0; i < good.length; i++) {
            boolean a = Cell.isText(good[i]);
            assertTrue(a);
        }

        String[] bad = {"56" , "=3 "/*, "3=()"*/ , "5.32" };
        for (int i = 0; i < bad.length; i++) {
            boolean a = Cell.isText(bad[i]);
            assertFalse(a);
        }
    }

    @Test
    void parentheses() {
        String[] good = { "hello (world)" , "256" , "3+5" , "(25)" , "(2+3)*(4+5)" , "(((5)))" , "3+(4*(5/2))" ,
                          "(A5+(2*3))-2" , "((2+5)-3)+5+(4-2)" , "(2+(3-(4+(5-6))+6)+7)"};
        for (int i = 0; i < good.length; i++) {
            boolean a = Cell.parentheses(good[i]);
            assertTrue(a);
        }

        String[] bad = {"(25))" , "()" ,"25+(5" , "(4+5)+()" , "(5+7))-(7" , "(2+5))+(8+(7)" };
        for (int i = 0; i < bad.length; i++) {
            boolean a = Cell.parentheses(bad[i]);
        }
    }


    @Test
    void mainOpIndex() {
        String a = "256"; // -1
        String b = "3+5"; //1
        String c = "(2+3)*(4+5)";
        String d = "";
        String e = "";
        String f = "";
        String g = "";
        String h = "";
        String i = "";
        String j = "";
        String k = "";
    }
}