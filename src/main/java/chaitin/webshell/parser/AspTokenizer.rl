// -*- coding: utf-8 -*-
%%{
    machine php_lexer;

    ws = '\r' | '\n' | ' ';

    integer = ('+'|'-')?[0-9]+;

    main :=
        |*
          integer => { emit(PhpTokenType.Integer, 0.1); };
          ws;
        *|
    ;

}%%

package chaitin.webshell.parser;

import java.util.*;


public class PhpTokenizer {
    public enum PhpTokenType {
        Integer
    }

    public class PhpToken {
        public byte[] data;
        public PhpTokenType type;
        public double wight;

        public PhpToken(byte[] data, PhpTokenType type, double wight) {
            this.data = data;
            this.type = type;
            this.wight = wight;
        }

        @Override
        public String toString() {
            return new String(data) + wight;
        }
    }

    List<PhpToken> tokens;
    byte[] data;

    int p, pe, eof, te, ts, cs, act;

    %% write data;

    public PhpTokenizer(byte[] data) {
        tokens = new LinkedList<PhpToken>();
        this.data = data;

        p = 0;
        pe = data.length;
        eof = pe;

        %% write init;
    }

    public void emit(PhpTokenType type, double weight) {
        tokens.add(new PhpToken(Arrays.copyOfRange(data, ts, te), type, weight));
    }


    public Boolean tokenize() {
        %% write exec;

        System.out.println(tokens);
        return p == pe;
    }

    public static void main(String[] args) {
        new PhpTokenizer("123 tést = -100".getBytes()).tokenize();
    }
}
