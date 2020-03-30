import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArithmeticCodingTest {
    private ArithmeticCoding arithmeticCoding;

    @Before
    public void setUp() {
        char[] alphabet = {'a', 'b', 'c', '#'};
        double[] prob = {0.45, 0.3, 0.2, 0.05};
        char[] message = "caba#".toCharArray();
        arithmeticCoding = new ArithmeticCoding(message, prob, alphabet);
    }


    @Test
    public void shouldReturnCorrectCode() {
        assertEquals(0.8020425, arithmeticCoding.encode(), 0.0);
    }

    @Test
    public void shouldCorrectDecode() {
        assertEquals("caba#", arithmeticCoding.decode(0.8020425));
    }

    @Test
    public void shouldCorrectEncodeAndDecodeMessage() {
        String message = "ku+as";
        ArithmeticCoding arithmeticCoding = new ArithmeticCoding(message);
        assertEquals(0.9043200000000001, arithmeticCoding.encode(), 0.0);
        assertEquals(arithmeticCoding.decode(0.9043200000000001), message);
    }
}