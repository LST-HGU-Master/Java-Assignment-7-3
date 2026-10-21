import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.*;
/**
 * @version (20220526)
 * @version (20261021) revised for method renaming, lifecycle management, explicit trailing newline validation, and index safety
 * 
 * (注意) Prog73クラス内に main() が
 * 　　　　宣言されるまで、このテストクラスはエラーが表示される
 * 　　※感嘆符は全角文字とする
 **/
public class Prog73Test {
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
    public void testMain()
    {
        String[] member = {"ハナコ","タロウ"};
        
        for (String name: member) {
            bos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(bos));

            Prog73.main(new String[]{ name });

            String rawOutput = bos.toString().replace("\r\n", "\n");

            // 1. 末尾改行のチェック
            assertTrue(rawOutput.endsWith("\n"), 
                "Prog73.main() の出力末尾に改行がありません! 各行の出力直後に改行（println）を入れているか確認してください。"
            );

            // 2. 行数チェック (1行分以上確認できるか)
            String[] prints = rawOutput.split("\n");
            assertTrue(prints.length >= 1, 
                "Prog73.main() の実行結果が確認できません! メッセージ出力の直後に改行（println）を入れているか確認してください。"
            );

            // 3. 出力メッセージ検証
            assertEquals("魔法使い" + name + "は魔法を唱えた！", prints[0].trim(), 
                "Prog73.main() の実行結果が指定メッセージと異なります!"
            );
        }
    }
}
