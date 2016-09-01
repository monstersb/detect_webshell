
// line 1 "PhpScore.rl"
// -*- coding: utf-8 -*-

// line 49 "PhpScore.rl"


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

    
// line 74 "PhpScore.java"
private static byte[] init__php_lexer_actions_0()
{
	return new byte [] {
	    0,    1,    1,    1,    2,    1,    3,    1,    4,    1,    5,    1,
	    6,    1,    7,    1,    8,    1,    9,    1,   10,    1,   11,    1,
	   12,    1,   13,    1,   14,    1,   15,    2,    0,    5,    2,    2,
	    6,    2,    2,    7,    2,    2,    8,    2,    2,    9,    2,    2,
	   10,    2,    2,   11,    2,    2,   15,    2,    3,    1,    2,    3,
	    6,    2,    3,    7,    2,    3,    8,    2,    3,    9,    2,    3,
	   10,    2,    3,   11,    2,    3,   15,    2,    4,    1,    2,    4,
	    6,    2,    4,    7,    2,    4,    8,    2,    4,    9,    2,    4,
	   10,    2,    4,   11,    2,    4,   15,    2,    5,    1,    2,    5,
	    6,    2,    5,    7,    2,    5,    8,    2,    5,    9,    2,    5,
	   10,    2,    5,   11,    2,    5,   15,    2,   12,    1,    2,   12,
	    6,    2,   12,    7,    2,   12,    8,    2,   12,    9,    2,   12,
	   10,    2,   12,   11,    2,   12,   15,    2,   13,    6,    2,   13,
	    7,    2,   13,    8,    2,   13,    9,    2,   13,   10,    2,   13,
	   11,    2,   13,   15,    2,   14,    1,    2,   14,    6,    2,   14,
	    7,    2,   14,    8,    2,   14,    9,    2,   14,   10,    2,   14,
	   11,    2,   14,   15,    3,    0,    5,    1,    3,    0,    5,    6,
	    3,    0,    5,    7,    3,    0,    5,    8,    3,    0,    5,    9,
	    3,    0,    5,   10,    3,    0,    5,   11,    3,    0,    5,   15
	};
}

private static final byte _php_lexer_actions[] = init__php_lexer_actions_0();


private static short[] init__php_lexer_key_offsets_0()
{
	return new short [] {
	    0,    0,    2,    4,    6,    6,    7,    8,    9,   10,   12,   14,
	   20,   25,   33,   33,   67,  102,  136,  170,  204,  240,  274,  312,
	  347,  380,  414,  450,  483,  521,  555,  588
	};
}

private static final short _php_lexer_key_offsets[] = init__php_lexer_key_offsets_0();


private static char[] init__php_lexer_trans_keys_0()
{
	return new char [] {
	   34,   92,   10,   13,   39,   92,   42,   47,   96,   96,   43,   45,
	   48,   57,   48,   57,   65,   70,   97,  102,   95,   65,   90,   97,
	  122,   95,  125,   48,   57,   65,   90,   97,  122,    1,   13,   33,
	   34,   35,   36,   39,   40,   41,   44,   47,   48,   91,   93,   94,
	   96,  123,  125,    9,   10,   32,   38,   42,   46,   49,   57,   58,
	   64,   65,   90,   95,  122,  124,  126,    1,   13,   33,   34,   35,
	   36,   39,   40,   41,   44,   47,   48,   61,   91,   93,   94,   96,
	  123,  125,    9,   10,   32,   38,   42,   46,   49,   57,   58,   64,
	   65,   90,   95,  122,  124,  126,    1,   13,   33,   34,   35,   36,
	   39,   40,   41,   44,   47,   48,   91,   93,   94,   96,  123,  125,
	    9,   10,   32,   38,   42,   46,   49,   57,   58,   64,   65,   90,
	   95,  122,  124,  126,    1,   13,   33,   34,   35,   36,   39,   40,
	   41,   44,   47,   48,   91,   93,   94,   96,  123,  125,    9,   10,
	   32,   38,   42,   46,   49,   57,   58,   64,   65,   90,   95,  122,
	  124,  126,    1,   13,   33,   34,   35,   36,   39,   40,   41,   44,
	   47,   48,   91,   93,   94,   96,  123,  125,    9,   10,   32,   38,
	   42,   46,   49,   57,   58,   64,   65,   90,   95,  122,  124,  126,
	    1,   13,   33,   34,   35,   36,   39,   40,   41,   42,   43,   47,
	   48,   61,   91,   93,   94,   96,  123,  125,    9,   10,   32,   44,
	   45,   46,   49,   57,   58,   64,   65,   90,   95,  122,  124,  126,
	    1,   13,   33,   34,   35,   36,   39,   40,   41,   44,   47,   48,
	   91,   93,   94,   96,  123,  125,    9,   10,   32,   38,   42,   46,
	   49,   57,   58,   64,   65,   90,   95,  122,  124,  126,    1,   13,
	   33,   34,   35,   36,   39,   40,   41,   44,   46,   47,   69,   88,
	   91,   93,   94,   96,  101,  120,  123,  125,    9,   10,   32,   38,
	   42,   45,   48,   57,   58,   64,   65,   90,   95,  122,  124,  126,
	    1,   13,   33,   34,   35,   36,   39,   40,   41,   44,   47,   69,
	   91,   93,   94,   96,  101,  123,  125,    9,   10,   32,   38,   42,
	   46,   48,   57,   58,   64,   65,   90,   95,  122,  124,  126,    1,
	   13,   33,   34,   35,   36,   39,   40,   41,   44,   47,   91,   93,
	   94,   96,  123,  125,    9,   10,   32,   38,   42,   46,   48,   57,
	   58,   64,   65,   90,   95,  122,  124,  126,    1,   13,   33,   34,
	   35,   36,   39,   40,   41,   44,   47,   48,   91,   93,   94,   96,
	  123,  125,    9,   10,   32,   38,   42,   46,   49,   57,   58,   64,
	   65,   90,   95,  122,  124,  126,    1,   13,   33,   34,   35,   36,
	   39,   40,   41,   44,   46,   47,   69,   91,   93,   94,   96,  101,
	  123,  125,    9,   10,   32,   38,   42,   45,   48,   57,   58,   64,
	   65,   90,   95,  122,  124,  126,    1,   13,   33,   34,   35,   36,
	   39,   40,   41,   44,   47,   91,   93,   94,   96,  123,  125,    9,
	   10,   32,   38,   42,   46,   48,   57,   58,   64,   65,   90,   95,
	  122,  124,  126,    1,   13,   33,   34,   35,   36,   39,   40,   41,
	   44,   47,   91,   93,   94,   95,   96,  123,  125,    9,   10,   32,
	   38,   42,   46,   48,   57,   58,   64,   65,   70,   71,   90,   97,
	  102,  103,  122,  124,  126,    1,   13,   33,   34,   35,   36,   39,
	   40,   41,   44,   47,   48,   91,   93,   94,   96,  123,  125,    9,
	   10,   32,   38,   42,   46,   49,   57,   58,   64,   65,   90,   95,
	  122,  124,  126,    1,   13,   33,   34,   35,   36,   39,   40,   41,
	   44,   47,   91,   93,   94,   96,  123,  125,    9,   10,   32,   38,
	   42,   46,   48,   57,   58,   64,   65,   90,   95,  122,  124,  126,
	    1,   13,   33,   34,   35,   36,   39,   40,   41,   44,   47,   48,
	   91,   93,   94,   96,  123,  125,    9,   10,   32,   38,   42,   46,
	   49,   57,   58,   64,   65,   90,   95,  122,  124,  126,    0
	};
}

private static final char _php_lexer_trans_keys[] = init__php_lexer_trans_keys_0();


private static byte[] init__php_lexer_single_lengths_0()
{
	return new byte [] {
	    0,    2,    2,    2,    0,    1,    1,    1,    1,    2,    0,    0,
	    1,    2,    0,   18,   19,   18,   18,   18,   20,   18,   22,   19,
	   17,   18,   20,   17,   18,   18,   17,   18
	};
}

private static final byte _php_lexer_single_lengths[] = init__php_lexer_single_lengths_0();


private static byte[] init__php_lexer_range_lengths_0()
{
	return new byte [] {
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    1,    3,
	    2,    3,    0,    8,    8,    8,    8,    8,    8,    8,    8,    8,
	    8,    8,    8,    8,   10,    8,    8,    8
	};
}

private static final byte _php_lexer_range_lengths[] = init__php_lexer_range_lengths_0();


private static short[] init__php_lexer_index_offsets_0()
{
	return new short [] {
	    0,    0,    3,    6,    9,   10,   12,   14,   16,   18,   21,   23,
	   27,   31,   37,   38,   65,   93,  120,  147,  174,  203,  230,  261,
	  289,  315,  342,  371,  397,  426,  453,  479
	};
}

private static final short _php_lexer_index_offsets[] = init__php_lexer_index_offsets_0();


private static byte[] init__php_lexer_trans_targs_0()
{
	return new byte [] {
	   17,   14,    1,   18,   18,    2,   17,    4,    3,    3,    6,    5,
	   21,    5,    0,    8,   25,    0,   10,   10,    0,   27,    0,   28,
	   28,   28,    0,   13,   13,   13,    0,   13,   31,   13,   13,   13,
	    0,    1,   15,   15,   16,    1,    2,   19,    3,   15,   15,   15,
	   20,   22,   15,   15,   15,    7,   15,   15,   15,   15,   16,   26,
	   15,   24,   24,   15,    0,   15,   15,   16,    1,    2,   19,    3,
	   15,   15,   15,   20,   22,   29,   15,   15,   15,    7,   15,   15,
	   15,   15,   16,   26,   15,   24,   24,   15,    0,   15,   15,   16,
	    1,    2,   19,    3,   15,   15,   15,   20,   22,   15,   15,   15,
	    7,   15,   15,   15,   15,   16,   26,   15,   24,   24,   15,    0,
	   15,   15,   16,    1,    2,   19,    3,   15,   15,   15,   20,   22,
	   15,   15,   15,    7,   15,   15,   15,   15,   16,   26,   15,   24,
	   24,   15,    0,   15,   15,   16,    1,    2,   19,    3,   15,   15,
	   15,   20,   22,   15,   15,   15,    7,   12,   15,   15,   15,   16,
	   26,   15,   30,   30,   15,    0,   15,   15,   16,    1,    2,   19,
	    3,   15,   15,    5,   16,    2,   22,   29,   15,   15,   15,    7,
	   15,   15,   15,   15,   16,   26,   15,   24,   24,   15,    0,   15,
	   15,   16,    1,    2,   19,    3,   15,   15,   15,   20,   22,   15,
	   15,   15,    7,   15,   15,   15,   15,   16,   26,   15,   24,   24,
	   15,    0,   15,   15,   16,    1,    2,   19,    3,   15,   15,   15,
	   23,   20,    9,   11,   15,   15,   15,    7,    9,   11,   15,   15,
	   15,   15,   16,   26,   15,   24,   24,   15,    0,   15,   15,   16,
	    1,    2,   19,    3,   15,   15,   15,   20,    9,   15,   15,   15,
	    7,    9,   15,   15,   15,   15,   16,   23,   15,   24,   24,   15,
	    0,   15,   15,   16,    1,    2,   19,    3,   15,   15,   15,   20,
	   15,   15,   15,    7,   15,   15,   15,   15,   16,   24,   15,   24,
	   24,   15,    0,   15,   15,   16,    1,    2,   19,    3,   15,   15,
	   15,   20,   22,   15,   15,   15,    7,   15,   15,   15,   15,   16,
	   26,   15,   24,   24,   15,    0,   15,   15,   16,    1,    2,   19,
	    3,   15,   15,   15,   23,   20,    9,   15,   15,   15,    7,    9,
	   15,   15,   15,   15,   16,   26,   15,   24,   24,   15,    0,   15,
	   15,   16,    1,    2,   19,    3,   15,   15,   15,   20,   15,   15,
	   15,    7,   15,   15,   15,   15,   16,   27,   15,   24,   24,   15,
	    0,   15,   15,   16,    1,    2,   19,    3,   15,   15,   15,   20,
	   15,   15,   15,   24,    7,   15,   15,   15,   15,   16,   28,   15,
	   28,   24,   28,   24,   15,    0,   15,   15,   16,    1,    2,   19,
	    3,   15,   15,   15,   20,   22,   15,   15,   15,    7,   15,   15,
	   15,   15,   16,   26,   15,   24,   24,   15,    0,   15,   15,   16,
	    1,    2,   19,    3,   15,   15,   15,   20,   15,   15,   15,    7,
	   15,   15,   15,   15,   16,   30,   15,   30,   30,   15,    0,   15,
	   15,   16,    1,    2,   19,    3,   15,   15,   15,   20,   22,   15,
	   15,   15,    7,   15,   15,   15,   15,   16,   26,   15,   24,   24,
	   15,    0,    0
	};
}

private static final byte _php_lexer_trans_targs[] = init__php_lexer_trans_targs_0();


private static short[] init__php_lexer_trans_actions_0()
{
	return new short [] {
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,   29,    0,   11,   13,    0,
	    0,    0,   15,   17,    0,    0,   19,   21,    0,    0,    0,    0,
	    0,    1,    1,    0,    0,    0,    0,    0,    0,    0,   29,    0,
	   11,   13,    0,    0,    0,    0,   15,   17,    0,    0,   19,   21,
	    0,    0,    0,    0,    0,    1,    1,    0,    0,    5,    5,    5,
	    5,    5,   76,    5,   58,   61,    5,    5,    5,   64,   67,    5,
	    5,   70,   73,    5,    5,    5,    5,    5,   55,   55,    5,    0,
	    9,    9,    9,    9,    9,  124,    9,  106,  109,    9,    9,    9,
	  112,  115,    9,    9,  118,  121,    9,    9,    9,    9,    9,  103,
	  103,    9,    0,    0,    0,    0,    0,    0,   29,    0,   11,   13,
	    0,    0,    0,   15,   17,    0,    0,    0,   21,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,   29,
	    0,   11,   13,    0,    0,    0,    0,    0,   15,   17,    0,    0,
	   19,   21,    0,    0,    0,    0,    0,    1,    1,    0,    0,   31,
	   31,   31,   31,   31,  224,   31,  200,  204,   31,   31,   31,  208,
	  212,   31,   31,  216,  220,   31,   31,   31,   31,   31,  196,  196,
	   31,    0,    0,    0,    0,    0,    0,   29,    0,   11,   13,    0,
	    0,    0,    0,    0,   15,   17,    0,    0,    0,    0,   19,   21,
	    0,    0,    0,    0,    0,    1,    1,    0,    0,    0,    0,    0,
	    0,    0,   29,    0,   11,   13,    0,    0,    0,   15,   17,    0,
	    0,    0,   19,   21,    0,    0,    0,    0,    0,    1,    1,    0,
	    0,    3,    3,    3,    3,    3,   52,    3,   34,   37,    3,    3,
	   40,   43,    3,    3,   46,   49,    3,    3,    3,    0,    3,    0,
	    0,    3,    0,    7,    7,    7,    7,    7,  100,    7,   82,   85,
	    7,    7,    7,   88,   91,    7,    7,   94,   97,    7,    7,    7,
	    7,    7,   79,   79,    7,    0,    0,    0,    0,    0,    0,   29,
	    0,   11,   13,    0,    0,    0,    0,   15,   17,    0,    0,    0,
	   19,   21,    0,    0,    0,    0,    0,    1,    1,    0,    0,    0,
	    0,    0,    0,    0,   29,    0,   11,   13,    0,    0,   15,   17,
	    0,    0,   19,   21,    0,    0,    0,    0,    0,    1,    1,    0,
	    0,    0,    0,    0,    0,    0,   29,    0,   11,   13,    0,    0,
	   15,   17,    0,    1,    0,   19,   21,    0,    0,    0,    0,    0,
	    0,    1,    0,    1,    0,    0,   23,   23,   23,   23,   23,  148,
	   23,  130,  133,   23,   23,   23,  136,  139,   23,   23,  142,  145,
	   23,   23,   23,   23,   23,  127,  127,   23,    0,   25,   25,   25,
	   25,   25,  169,   25,  151,  154,   25,   25,  157,  160,   25,   25,
	  163,  166,   25,   25,   25,    0,   25,    0,    0,   25,    0,   27,
	   27,   27,   27,   27,  193,   27,  175,  178,   27,   27,   27,  181,
	  184,   27,   27,  187,  190,   27,   27,   27,   27,   27,  172,  172,
	   27,    0,    0
	};
}

private static final short _php_lexer_trans_actions[] = init__php_lexer_trans_actions_0();


private static short[] init__php_lexer_eof_actions_0()
{
	return new short [] {
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    5,    9,    0,    0,   31,    0,    0,
	    3,    7,    0,    0,    0,   23,   25,   27
	};
}

private static final short _php_lexer_eof_actions[] = init__php_lexer_eof_actions_0();


static final int php_lexer_start = 15;
static final int php_lexer_first_final = 15;
static final int php_lexer_error = 0;

static final int php_lexer_en_main = 15;


// line 116 "PhpScore.rl"

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

        
// line 353 "PhpScore.java"
	{
	cs = php_lexer_start;
	}

// line 133 "PhpScore.rl"
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
        
// line 372 "PhpScore.java"
	{
	int _klen;
	int _trans = 0;
	int _acts;
	int _nacts;
	int _keys;
	int _goto_targ = 0;

	_goto: while (true) {
	switch ( _goto_targ ) {
	case 0:
	if ( p == pe ) {
		_goto_targ = 4;
		continue _goto;
	}
	if ( cs == 0 ) {
		_goto_targ = 5;
		continue _goto;
	}
case 1:
	_match: do {
	_keys = _php_lexer_key_offsets[cs];
	_trans = _php_lexer_index_offsets[cs];
	_klen = _php_lexer_single_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + _klen - 1;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + ((_upper-_lower) >> 1);
			if ( data[p] < _php_lexer_trans_keys[_mid] )
				_upper = _mid - 1;
			else if ( data[p] > _php_lexer_trans_keys[_mid] )
				_lower = _mid + 1;
			else {
				_trans += (_mid - _keys);
				break _match;
			}
		}
		_keys += _klen;
		_trans += _klen;
	}

	_klen = _php_lexer_range_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + (_klen<<1) - 2;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + (((_upper-_lower) >> 1) & ~1);
			if ( data[p] < _php_lexer_trans_keys[_mid] )
				_upper = _mid - 2;
			else if ( data[p] > _php_lexer_trans_keys[_mid+1] )
				_lower = _mid + 2;
			else {
				_trans += ((_mid - _keys)>>1);
				break _match;
			}
		}
		_trans += _klen;
	}
	} while (false);

	cs = _php_lexer_trans_targs[_trans];

	if ( _php_lexer_trans_actions[_trans] != 0 ) {
		_acts = _php_lexer_trans_actions[_trans];
		_nacts = (int) _php_lexer_actions[_acts++];
		while ( _nacts-- > 0 )
	{
			switch ( _php_lexer_actions[_acts++] )
			{
	case 0:
// line 23 "PhpScore.rl"
	{ score += 0.2; }
	break;
	case 1:
// line 30 "PhpScore.rl"
	{ ps = p; }
	break;
	case 2:
// line 30 "PhpScore.rl"
	{ score += keyword_score(); }
	break;
	case 3:
// line 32 "PhpScore.rl"
	{ score += 0.2; }
	break;
	case 4:
// line 33 "PhpScore.rl"
	{ score += 1.0; }
	break;
	case 5:
// line 34 "PhpScore.rl"
	{ score += 0.2; }
	break;
	case 6:
// line 35 "PhpScore.rl"
	{ count_par += 1; }
	break;
	case 7:
// line 36 "PhpScore.rl"
	{ if (count_par > 0) { count_par -= 1; } else { black = true; } }
	break;
	case 8:
// line 37 "PhpScore.rl"
	{ count_bracket += 1; }
	break;
	case 9:
// line 38 "PhpScore.rl"
	{ if (count_bracket > 0) { count_bracket -= 1; } else { black = true; } }
	break;
	case 10:
// line 39 "PhpScore.rl"
	{ count_brace += 1; }
	break;
	case 11:
// line 40 "PhpScore.rl"
	{ if (count_brace > 0) { count_brace -= 1; } else { black = true; } }
	break;
	case 12:
// line 41 "PhpScore.rl"
	{ score += 0.1; }
	break;
	case 13:
// line 42 "PhpScore.rl"
	{ score += 0.3; }
	break;
	case 14:
// line 43 "PhpScore.rl"
	{ score += 0.5; }
	break;
	case 15:
// line 46 "PhpScore.rl"
	{ score += 0.1; }
	break;
// line 515 "PhpScore.java"
			}
		}
	}

case 2:
	if ( cs == 0 ) {
		_goto_targ = 5;
		continue _goto;
	}
	if ( ++p != pe ) {
		_goto_targ = 1;
		continue _goto;
	}
case 4:
	if ( p == eof )
	{
	int __acts = _php_lexer_eof_actions[cs];
	int __nacts = (int) _php_lexer_actions[__acts++];
	while ( __nacts-- > 0 ) {
		switch ( _php_lexer_actions[__acts++] ) {
	case 0:
// line 23 "PhpScore.rl"
	{ score += 0.2; }
	break;
	case 2:
// line 30 "PhpScore.rl"
	{ score += keyword_score(); }
	break;
	case 3:
// line 32 "PhpScore.rl"
	{ score += 0.2; }
	break;
	case 4:
// line 33 "PhpScore.rl"
	{ score += 1.0; }
	break;
	case 5:
// line 34 "PhpScore.rl"
	{ score += 0.2; }
	break;
	case 12:
// line 41 "PhpScore.rl"
	{ score += 0.1; }
	break;
	case 13:
// line 42 "PhpScore.rl"
	{ score += 0.3; }
	break;
	case 14:
// line 43 "PhpScore.rl"
	{ score += 0.5; }
	break;
// line 568 "PhpScore.java"
		}
	}
	}

case 5:
	}
	break; }
	}

// line 146 "PhpScore.rl"

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
