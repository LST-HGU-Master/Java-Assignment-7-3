import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.io.*;

public class WizardTest {

    @Test
    public void testInitialValues()
    {
        // action
        Wizard w = new Wizard();

        // assertion
        assertEquals(null, w.name);
        assertEquals(100, w.hp);
        assertEquals(20, w.mp);
    }

    @Test
    public void testFire()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        // action
        Wizard w = new Wizard();
        w.name = "Taro";
        w.mp = 10;
        w.fire();

        // assertion
        assertEquals(5, w.mp);
        assertEquals("魔法使いTaroは魔法を唱えた!\n", bos.toString());

        // undo the binding in System
        System.setOut(originalOut);
    }
}
