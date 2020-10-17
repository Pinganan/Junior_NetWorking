import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;


public class binary {

    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final Charset ASCII = Charset.forName("US-ASCII");
    private static final Charset ISO = Charset.forName("ISO-8859-1");

    public static void main(String[] args) {

        String text = "åµåµåµ";
        byte ptext[] = text.getBytes(ISO);
        String value = new String(ptext, UTF_8);
        System.out.println(value);

    }
}