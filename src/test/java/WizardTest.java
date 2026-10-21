import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.*;
/**
 * @version (20220526)
 * @version (20261021) revised for lifecycle management, explicit trailing newline validation, and index safety
 * 
 * (注意) Wizardクラス内に name, hp,mp および fire() が
 * 　　　　宣言されるまで、このテストクラスはエラーが表示される
 * 　　※感嘆符は全角文字とする
 **/

public class WizardTest {
    InputStream originalIn;
    PrintStream originalOut;
    ByteArrayOutputStream bos;
    StandardInputStream in;

    @BeforeEach
    void before() {
        // back up binding
        originalIn  = System.in;
        originalOut = System.out;
        // modify binding
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        in = new StandardInputStream();
        System.setIn(in);
    }

    @AfterEach
    void after() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
    
    @Test
    public void testInitialValues()
    {
        Wizard w = new Wizard();

        assertNull(w.name, "Wizard のフィールド name の初期値が null ではありません!");
        assertEquals(100, w.hp, "Wizard のフィールド hp の初期値が 100 ではありません!");
        assertEquals(20, w.mp, "Wizard のフィールド mp の初期値が 20 ではありません!");
    }

    @Test
    public void testFire()
    {
        Wizard w = new Wizard();
        w.name = "Taro";
        w.mp = 10;
        w.fire();

        // 1. MP消費の検証 (10 -> 5)
        assertEquals(5, w.mp, "Wizard.fire() での mp の消費処理（5減算）が正しくありません!");

        // 2. 末尾改行のチェック
        String rawOutput = bos.toString().replace("\r\n", "\n");
        assertTrue(rawOutput.endsWith("\n"), 
            "Wizard.fire() の出力末尾に改行がありません! print ではなく println を使用しているか確認してください。"
        );

        // 3. 出力メッセージの検証
        String[] prints = rawOutput.split("\n");
        assertTrue(prints.length >= 1, "Wizard.fire() の出力結果が確認できません!");
        assertEquals("魔法使いTaroは魔法を唱えた！", prints[0], "Wizard.fire() の出力メッセージが指定と異なります!");
    }
}
