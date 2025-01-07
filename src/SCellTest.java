import org.junit.jupiter.api.Test;
import p1.Cell;

import static org.junit.jupiter.api.Assertions.*;

class SCellTest {

    @Test
    void isNumber() {
        String[] good = { "-35.32", "123", "21.03" , "0.002" , "2.6", "8.0" , "1.000" , "-9" , "-4" };
        for (int i = 0; i < good.length; i++) {
            boolean a = SCell.isNumber(good[i]);
            assertTrue(a);
        }

        String[] bad = {"sd56" , "1. 0" , "5.32a" , "58.%" , "89-3" };
        for (int i = 0; i < bad.length; i++) {
            boolean a = SCell.isNumber(bad[i]);
            assertFalse(a);
        }
    }

    @Test
    void isText() {
        String[] good = {"-4s", "biso", "abc", "hello world" , "2 *#" , "2..3", "8.0o" , "..." , "BSH" };
        for (int i = 0; i < good.length; i++) {
            boolean a = SCell.isText(good[i]);
            assertTrue(a);
        }

        String[] bad = {"56" , "=3 "/*, "3=()"*/ , "5.32" };
        for (int i = 0; i < bad.length; i++) {
            boolean a = SCell.isText(bad[i]);
            assertFalse(a);
        }
    }

    @Test
    void isForm() {


    }

    @Test
    void computeForm() {
    }

    @Test
    void valForm() {
    }

    @Test
    void mainOpIndex() {
        String a = "256"; // -1
        assertTrue(SCell.mainOpIndex(a) == -1);
        String b = "3+5"; //1
        assertTrue(SCell.mainOpIndex(b) == 1);
        String c = "(2+3)*(4+5)"; // 5
        assertTrue(SCell.mainOpIndex(c) == 5);
        String d = "(((5)))"; // -1
        assertTrue(SCell.mainOpIndex(d) == -1);
        String e = "3+(4*(5/2))"; // 1
        assertTrue(SCell.mainOpIndex(e) == 1);
        String f = "(A5+(2*3))-2"; //10
        assertTrue(SCell.mainOpIndex(f) == 10);
        String g = "((2+5)-3)+5+(4-2)"; //11
        assertTrue(SCell.mainOpIndex(g) == 11);
        String h = "(2+(3-(4+(5-6))+6)+7)"; //18
        assertTrue(SCell.mainOpIndex(h) == 18);
        String k = "(2+5)+(3+5)*3";
        assertTrue(SCell.mainOpIndex(k) == 5);
    }

    @Test
    void parentheses() {
        String[] good = { "hello (world)" , "256" , "3+5" , "(25)" , "(2+3)*(4+5)" , "(((5)))" , "3+(4*(5/2))" ,
                "(A5+(2*3))-2" , "((2+5)-3)+5+(4-2)" , "(2+(3-(4+(5-6))+6)+7)"};
        for (int i = 0; i < good.length; i++) {
            boolean a = SCell.parentheses(good[i]);
            assertTrue(a);
        }

        String[] bad = {"(25))" , "()" ,"25+(5" , "(4+5)+()" , "(5+7))-(7" , "(2+5))+(8+(7)" };
        for (int i = 0; i < bad.length; i++) {
            boolean a = SCell.parentheses(bad[i]);
        }
    }

    @Test
    void removeParen() {
        String a = "(2+5)";
        String b = SCell.removeParen(a);
        assertTrue(b.equals("2+5"));
        String c = "(1+2*(3-8)+2)";
        String d = SCell.removeParen(c);
        assertTrue(d.equals("1+2*(3-8)+2"));
        String e = "(2+5)*3";
        String f = SCell.removeParen(e);
        assertTrue(f.equals("(2+5)*3"));


    }
}