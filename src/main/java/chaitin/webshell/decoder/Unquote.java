package chaitin.webshell.decoder;

import java.io.ByteArrayOutputStream;
import java.util.LinkedList;
import java.util.List;

public class Unquote extends Decoder {
    @Override
    List<byte[]> decode(byte[] input) {
        List<byte[]> result = new LinkedList<byte[]>();
        return result;
    }

    static byte[] unquote(byte[] input) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        int pos = 0;
        while (pos < input.length) {
            if (pos + 2 < input.length
                    && input[pos] == '%'
                    && isxdigit(input[pos + 1])
                    && isxdigit(input[pos + 2])) {
                output.write(Unquote.ord(input[pos + 1], input[pos + 2]));
                pos += 3;
            } else {
                output.write(input[pos]);
                pos += 1;
            }
        }
        return output.toByteArray();
    }

    static boolean isxdigit(byte b) {
        return (b >= '0' && b <= '9')
                || (b >= 'a' && b <= 'f')
                || (b >= 'A' && b <= 'F');
    }

    static byte ord(byte a, byte b) {
        byte r = 0;
        if (a >= '0' && a <= '9') {
            a = (byte)(a & 0x0F);
        } else {
            a = (byte)((a & 0x0F) + 9);
        }
        if (b >= '0' && b <= '9') {
            b = (byte)(b & 0x0F);
        } else {
            b = (byte)((b & 0x0F) + 9);
        }
        return (byte)((a << 4) | b);
    }
}