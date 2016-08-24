package chaitin.webshell.decoder;

import java.util.LinkedList;
import java.util.List;

public class Multipart extends Decoder {
    @Override
    List<byte[]> decode(byte[] input) {
        List<byte[]> result = new LinkedList<byte[]>();
        return result;
    }
/*
    static List<Pair<byte[], byte[]>> multipart(byte[] input) {
        List<Pair<byte[], byte[]>> result = new LinkedList<Pair<byte[], byte[]>>();
        int pos = Multipart.accept(input, 0, "--".getBytes());
        if (pos == 0) {
            return result;
        }
        int pos_boundary = pos;
        while (pos < input.length && input[pos] != '\r' && input[pos] != '\n') {
            pos += 1;
        }
        byte[] boundary = Arrays.copyOfRange(input, pos_boundary, pos);
        return result;
    }

    static int accept(byte[] input, int pos, byte[] sub) {
        int i = 0;
        for (i = 0; i < sub.length && pos + i < input.length && sub[i] == input[pos + i]; ++i) {
            ;
        }
        return i == sub.length ? pos + i : 0;
    }

    static int accept_line(byte[] input, int pos) {

    }*/
}