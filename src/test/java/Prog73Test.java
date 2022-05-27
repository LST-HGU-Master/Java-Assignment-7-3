import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import java.io.*;
/**
 * @version (20220526)
 * 
 * (注意) Prog73クラス内に main() が
 * 　　　　宣言されるまで、このテストクラスはエラーが表示される
 * 　　※感嘆符は全角文字とする
 **/
public class Prog73Test {

    @Test
    public void testHelloWorld()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos;
        
        String[] member = {"ハナコ","タロウ"};
        
        for (String name: member) {
            bos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(bos));

            // action
            Prog73.main(new String[]{ name });

            // assertion
            String[] prints = bos.toString().split("\r\n|\n");
            try {
                assertEquals("魔法使い" + name + "は魔法を唱えた！", prints[0]);
            } catch (AssertionError err) {
                System.setOut(originalOut);
                throw err;
            } catch (ArrayIndexOutOfBoundsException excpt) {
                System.setOut(originalOut);
                fail("コード中に適切なprint命令がありません!");
            }
        }

        // undo the binding in System
        System.setOut(originalOut);
    }
}
