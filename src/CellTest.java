import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @org.junit.jupiter.api.Test
    void isNumber() {
        String[] good = { "-35.32", "123", "21.03" , "0.002" , "2.6", "8.0" , "1.000" , "-9" , "-4" };
        for (int i = 0; i < good.length; i++) {
            boolean a = Cell.isNumber(good[i]);
            assertTrue(a);
        }

        String[] bad = {"sd56" /*, " 56"*/ , "1. 0" , "5.32a" , "58.%" , "89-3" };
        for (int i = 0; i < bad.length; i++) {
            boolean a = Cell.isNumber(bad[i]);
            assertFalse(a);
        }
    }

    @org.junit.jupiter.api.Test
    void isText() {
    }

    @org.junit.jupiter.api.Test
    void isForm() {
    }

    @org.junit.jupiter.api.Test
    void computeForm() {
    }
}