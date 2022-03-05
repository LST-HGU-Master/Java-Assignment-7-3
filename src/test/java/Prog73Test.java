import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.io.*;

public class Prog73Test {

    @Test
    public void testHelloWorld()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        // action
        Prog73.main(new String[]{"ハナコ"});

        // assertion
        assertEquals("魔法使いハナコは魔法を唱えた!\n", bos.toString());

        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        // action
        Prog73.main(new String[]{"タロウ"});

        // assertion
        assertEquals("魔法使いタロウは魔法を唱えた!\n", bos.toString());

        // undo the binding in System
        System.setOut(originalOut);
    }
}
