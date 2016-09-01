// -*- coding: utf-8 -*-
%%{
    machine php_lexer;

    ws = '\r' | '\n' | ' ' | '\t' | 0x01;
    label = ('_' | alpha) ('_' | alnum)*;
    number = ('0' 
                ('x'i xdigit+)
              | ('b'i ('0' .. '1'))
              | ('o'i ('0' .. '7')))
            | (digit+ ('.' digit*)? ('e'i ('+' | '-') digit+)?
            );
    string = (
                ( "'" ( ( any - '\\' - "'" ) | ( '\\'  any ) )* "'" ) |
                ( '"' ( ( any - '\\' - '"' ) | ( '\\'  any ) )* '"' )
             );
    backtick_string = '`' (any - '`') '`';
    comment = (
                 (
                     ( "//" | '#' ) ( any - '\r' - '\n')* ( '\r' | '\n' )
                 ) |
                 (
                     ("/*" ( ( any - '*' ) | ( '*' ( any - '/' ) ) )* "*/") % { score += 0.2; } 
                 )
              );
    value_cast = '(' ws* ('int' | 'real' | 'double' | 'float' | 'string' | 'binary' | 'array' | 'object' | 'boolean' | 'bool' | 'unset') ws* ')';


    main := (
              label > { ps = p; } % { score += keyword_score(); }  |
              number |
              string % { score += 0.2; } |
              backtick_string % { score += 1.0; } |
              comment % { score += 0.2; } |
              '(' > { count_par += 1; } |
              ')' > { if (count_par > 0) { count_par -= 1; } else { black = true; } } |
              '[' > { count_bracket += 1; } |
              ']' > { if (count_bracket > 0) { count_bracket -= 1; } else { black = true; } } |
              '{' > { count_brace += 1; } |
              '}' > { if (count_brace > 0) { count_brace -= 1; } else { black = true; } } |
              ('.=' | '+=' | '-=' | '*=' | '/=' | '!=') % { score += 0.1; } |
              '$' label % { score += 0.3; } |
              '${' label '}' % { score += 0.5; } |
              #value_cast % { score += 1.5; } |
              ('=' | ',' | '%' | '+' | '-' | '*' | '/' | ';' | '?' | ':' | '!' | '.' | '&' | '|' | '^' | '~' | '<' | '>' | '@') |
              '$' > { score += 0.1; } |
              ws+)**;

}%%

package chaitin.webshell.parser;

import java.util.*;


public class PhpScore {

    byte[] data;
    double score;
    boolean black;
    boolean white;
    int count_token;
    int count_par;
    int count_bracket;
    int count_brace;

    static Map<String, Double> keyword = new HashMap<String, Double>() {
        private static final long serialVersionUID = 6899997024892413801L;
    {
        put("_GET", 1.2);
        put("_POST", 1.3);
        put("_COOKIE", 1.5);
        put("_FILE", 1.2);
        put("_ENV", 1.2);
        put("_SESSION", 1.5);
        put("_REQUEST", 1.5);
        put("_SERVER", 1.5);
        put("array_map", 1.4);
        put("assert", 1.4);
        put("array_slice", 1.5);
        put("base64_decode", 2.0);
        put("call_user_func", 2.0);
        put("call_user_func_array", 2.0);
        put("create_function", 2.0);
        put("curl_exec", 1.4);
        put("curl_multi_exec", 1.4);
        put("eval", 1.0);
        put("exec", 1.0);
        put("file_put_contents", 2.0);
        put("gzdecode", 2.0);
        put("implode", 1.5);
        put("include", 0.9);
        put("include_once", 1.6);
        put("ini_set", 1.1);
        put("movefile", 1.2);
        put("ob_start", 1.5);
        put("parse_str", 1.5);
        put("passthru", 1.8);
        put("pcntl_exec", 1.7);
        put("phpinfo", 1.4);
        put("prege_replace", 1.5);
        put("proc_open", 1.7);
        put("require", 0.9);
        put("require_once", 1.6);
        put("session_start", 1.5);
        put("set_magic_quotes_runtime", 2.0);
        put("set_time_limit", 1.4);
        put("shell_exec", 1.4);
        put("str_rot13", 2.0);
        put("system", 1.0);
    }};

    int p, pe, eof, ps, cs;

    %% write data;

    public PhpScore(byte[] data) {
        this.data = data;

        score = 0.0;
        black = false;
        white = false;
        count_par = 0;
        count_token = 0;
        count_bracket = 0;
        count_brace = 0;

        p = 0;
        pe = data.length;
        eof = pe;

        %% write init;
    }

    double keyword_score() {
        String s = new String(Arrays.copyOfRange(data, ps, p));
        //System.out.println(s);
        if (keyword.containsKey(s)) {
            return keyword.get(s);
        }
        return 0.0;
    }

    public double score() {
        %% write exec;

        black |= p != pe;
        if (black) {
            score = 0.0;
        }
        return score;
    }

    public static void main(String[] args) {
        System.out.println(new PhpScore("a(b(c".getBytes()).score());
        System.out.println(new PhpScore("eval($_GET['a']);".getBytes()).score());
        System.out.println(new PhpScore("560648;@ini_set(\"display_errors\",\"0\");@set_time_limit(0);@set_magic_quotes_runtime(0);echo(\"->|\");;echo @fwrite(fopen(base64_decode($_POST[\"z1\"]),\"w\"),base64_decode($_POST[\"z2\"]))".getBytes()).score());
                    
    }
}
