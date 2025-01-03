import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @org.junit.jupiter.api.Test
    void isNumber() {
        String[] good = {"123",};
        for (int i = 0; i < good.length; i++) {
            boolean a = Cell.isNumber(good[i]);
            assertTrue(a);
        }

        String[] bad = {"sd56" , " 56"};
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