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

}