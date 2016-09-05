// -*- coding: utf-8 -*-
%%{
    machine php_lexer;

    ws = '\r' | '\n' | ' ' | '\t' | (0x00 .. 0x1F);
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
                     ("/*" ( ( any - '*' ) | ( '*' ( any - '/' ) ) )* "*/") % { count_cmt += 1; } 
                 )
              );
    value_cast = '(' ws* ('int' | 'real' | 'double' | 'float' | 'string' | 'binary' | 'array' | 'object' | 'boolean' | 'bool' | 'unset') ws* ')';


    main := |*
              label => { score += keyword_score(); };
              number;
              string => { score += string_score(); };
              backtick_string => { score += 0.5 + string_score(); };
              comment;
              '(' => { count_par += 1; };
              ')' => { if (count_par > 0) { count_par -= 1; } else { black = true; } };
              '[' => { count_bracket += 1; };
              ']' => { if (count_bracket > 0) { count_bracket -= 1; } else { black = true; } };
              '{' => { count_brace += 1; };
              '}' => { if (count_brace > 0) { count_brace -= 1; } else { black = true; } };
              ('.=' | '+=' | '-=' | '*=' | '/=' | '!=') => { /*score += 0.1;*/ };
              #'$' label => { count_var += 1; };
              #'${' label '}' => { count_svar += 1; };
              value_cast => { score += 1.5; };
              '<?php' => { score -= 999.0; };
              '<?' => { score -= 999.0; };
              '<%' => { score -= 999.0; };
              ('=' | ',' | '%' | '+' | '-' | '*' | '/' | ';' | '?' | ':' | '!' | '.' | '&' | '|' | '^' | '~' | '<' | '>' | '@');
              '$' => { /*score += 0.1;*/ };
              ws+;
              any => { score -= 1; };
            *|;

}%%

package chaitin.webshell.parser;


import chaitin.utils.Base64;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;



public class PhpScore {

    byte[] data;
    boolean black;
    boolean white;
    int count_token;
    int count_var;
    int count_svar;
    int count_cmt;
    int count_par;
    int count_bracket;
    int count_brace;

    Set<String> existed_key = new HashSet<String>();

    static Map<String, Double> keyword = new HashMap<String, Double>() {
        private static final long serialVersionUID = 6899997024892413801L;
    {

        put("PHP_SELF", 1.6);
        put("_GET", 1.2);
        put("_POST", 1.6);
        put("_COOKIE", 1.5);
        put("_FILE", 1.2);
        put("_ENV", 1.2);
        put("_SESSION", 1.5);
        put("_REQUEST", 1.5);
        put("_SERVER", 1.6);
        put("array_map", 1.4);
        put("assert", 1.3);
        put("array_slice", 1.5);
        put("base64_decode", 2.0);
        put("base_convert", 1.7);
        put("edoced_46esab", 2.0);
        put("call_user_func", 1.0);
        put("call_user_func_array", 1.0);
        put("chr", 0.4);
        put("create_function", 0.8);
        put("curl_exec", 1.4);
        put("curl_multi_exec", 1.4);
        put("dirname", 1.0);
        put("echo", 1.0);
        put("error_reporting", 1.2);
        put("eval", 1.0);
        put("exec", 1.0);
        put("exit", 0.5);
        put("file_put_contents", 1.2);
        put("gzdecode", 1.2);
        put("implode", 1.5);
        //put("include", 0.9);
        put("include_once", 1.6);
        put("ini_set", 1.1);
        put("isset", 1.1);
        put("movefile", 1.2);
        put("ob_start", 1.5);
        put("parse_str", 1.5);
        put("passthru", 1.8);
        put("pcntl_exec", 1.7);
        put("phpinfo", 1.9);
        put("prege_replace", 1.5);
        put("proc_open", 1.7);
        //put("require", 0.9);
        put("require_once", 1.6);
        put("session_start", 1.5);
        put("set_magic_quotes_runtime", 1.0);
        put("set_time_limit", 1.4);
        put("shell_exec", 1.4);
        put("str_rot13", 1.2);
        put("strrev", 1.1);
        put("system", 0.5);


        put("_memberaccess", -10.0);
        put("allowstaticmethodaccess", -10.0);
        put("alert", -1.8);
        put("document", -1.8);
        put("fromcharcode", -10.0);
        put("getdeclaredfield", -10.0);
        put("parseint", -5.0);
        put("println", -4.0);
        put("prototype", -5.0);
        put("setaccessible", -10.0);
        put("string", -1.0);
        put("tostring", -4.0);
    }};

    %% write data;

    int p, pe, eof;
    int te, ts, cs, act;


    public PhpScore(byte[] data) {
        this.data = data;

        black = false;
        white = false;
        count_cmt = 0;
        count_var = 0;
        count_svar = 0;
        count_par = 0;
        count_token = 0;
        count_bracket = 0;
        count_brace = 0;

    }

    double string_score() {
        byte[] sb = Arrays.copyOfRange(data, ts + 1, te - 1);
        String s = new String(sb);
        //System.out.println(s);
        if (keyword.containsKey(s) && !existed_key.contains(s)) {
            existed_key.add(s);
            return keyword.get(s);
        }
        if (s.length() >= 10) {
            byte[] bsb = Base64.decode_base64(Base64.longest_sub_base64(sb));
            double s1 = new PhpScore(bsb).score();
            double s2 = new PhpScore(sb).score();
            s1 = s1 > s2 ? s1 : s2;
            s1 = s1 > 0 ? s1 : 0;
            return s1;
        }
        return 0.0;
    }

    double keyword_score() {
        byte[] sb = Arrays.copyOfRange(data, ts, te);
        String s = new String(sb);
        //System.out.println(s);
        if (keyword.containsKey(s) && !existed_key.contains(s)) {
            existed_key.add(s);
            return keyword.get(s);
        }
        byte[] bsb = Base64.decode_base64(Base64.longest_sub_base64(sb));
        double s1 = new PhpScore(bsb).score();
        return s1 > 0.0 ? s1 : 0.0;
    }


    double tokenize() {
        p = 0;
        pe = data.length;
        eof = pe;
        double score = 0.0;
        %% write init;
        %% write exec;
        return score;
    }

    public double score() {
        double score = 0.0;
        if (data.length > 0 && (data[0] == '[' || data[0] == '{')) {
            score -= 3;
        }

        score += tokenize();

        score += count_cmt > 0 ? 1.0 : 0;
        score += count_svar * 0.5;
        score += count_var > 0 ? 0.6 : 0;
        black |= p != pe;
        //if (black) {
        //    score = 0.0;
        //}
        return score;
    }

    public static void main(String[] args) {
        System.out.println(new PhpScore("a(b(c".getBytes()).score());
        System.out.println(new PhpScore("eval($_GET['a']);".getBytes()).score());
        System.out.println(new PhpScore("560648;@ini_set(\"display_errors\",\"0\");@set_time_limit(0);@set_magic_quotes_runtime(0);echo(\"->|\");;echo @fwrite(fopen(base64_decode($_POST[\"z1\"]),\"w\"),base64_decode($_POST[\"z2\"]))".getBytes()).score());
                    
    }
}
