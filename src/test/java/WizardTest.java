import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import java.io.*;
/**
 * @version (20220526)
 * 
 * (注意) Wizardクラス内に name, hp,mp および fire() が
 * 　　　　宣言されるまで、このテストクラスはエラーが表示される
 * 　　※感嘆符は全角文字とする
 **/
public class WizardTest {

    @Test
    public void testInitialValues()
    {
        // action
        Wizard w = new Wizard();

        // assertion
        String msg1 = "Wizardのフィールド";
        String msg2 = "が不正です!";
        assertEquals(null, w.name, msg1 + " name " + msg2);
        assertEquals( 100, w.hp,   msg1 + " hp "   + msg2);
        assertEquals(  20, w.mp,   msg1 + " mp "   + msg2);
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
        try {
            assertEquals(5, w.mp, "Wizard.fire()でのmpの処理が不正です!");
            String[] prints = bos.toString().split("\r\n|\n");
            assertEquals("魔法使いTaroは魔法を唱えた！", prints[0], "Wizard.fire()のprint出力が不正です!");
        } catch (AssertionError err) {
            System.setOut(originalOut);
            throw err;
        } catch (ArrayIndexOutOfBoundsException excpt) {
            System.setOut(originalOut);
            fail("コード中に適切なprint命令がありません!");
        }
        // undo the binding in System
        System.setOut(originalOut);
    }
}
