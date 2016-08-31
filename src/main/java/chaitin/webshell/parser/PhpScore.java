
// line 1 "PhpScore.rl"
// -*- coding: utf-8 -*-

// line 47 "PhpScore.rl"


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
        put("array_slice", 1.5);
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
	   12,    1,   13,    1,   14,    2,    0,    4,    2,    2,    5,    2,
	    2,    6,    2,    2,    7,    2,    2,    8,    2,    2,    9,    2,
	    2,   10,    2,    2,   14,    2,    3,    1,    2,    3,    5,    2,
	    3,    6,    2,    3,    7,    2,    3,    8,    2,    3,    9,    2,
	    3,   10,    2,    3,   14,    2,    4,    1,    2,    4,    5,    2,
	    4,    6,    2,    4,    7,    2,    4,    8,    2,    4,    9,    2,
	    4,   10,    2,    4,   14,    2,   11,    1,    2,   11,    5,    2,
	   11,    6,    2,   11,    7,    2,   11,    8,    2,   11,    9,    2,
	   11,   10,    2,   11,   14,    2,   12,    5,    2,   12,    6,    2,
	   12,    7,    2,   12,    8,    2,   12,    9,    2,   12,   10,    2,
	   12,   14,    2,   13,    1,    2,   13,    5,    2,   13,    6,    2,
	   13,    7,    2,   13,    8,    2,   13,    9,    2,   13,   10,    2,
	   13,   14,    3,    0,    4,    1,    3,    0,    4,    5,    3,    0,
	    4,    6,    3,    0,    4,    7,    3,    0,    4,    8,    3,    0,
	    4,    9,    3,    0,    4,   10,    3,    0,    4,   14
	};
}

private static final byte _php_lexer_actions[] = init__php_lexer_actions_0();


private static short[] init__php_lexer_key_offsets_0()
{
	return new short [] {
	    0,    0,    2,    4,    6,    6,    7,    8,   10,   12,   18,   23,
	   31,   31,   65,  100,  134,  168,  202,  238,  272,  310,  345,  378,
	  411,  447,  484,  518,  551
	};
}

private static final short _php_lexer_key_offsets[] = init__php_lexer_key_offsets_0();


private static char[] init__php_lexer_trans_keys_0()
{
	return new char [] {
	   34,   92,   10,   13,   39,   92,   42,   47,   43,   45,   48,   57,
	   48,   57,   65,   70,   97,  102,   95,   65,   90,   97,  122,   95,
	  125,   48,   57,   65,   90,   97,  122,    1,   13,   33,   34,   35,
	   36,   39,   40,   41,   44,   47,   48,   91,   93,   94,   95,  123,
	  125,    9,   10,   32,   38,   42,   46,   49,   57,   58,   64,   65,
	   90,   97,  122,  124,  126,    1,   13,   33,   34,   35,   36,   39,
	   40,   41,   44,   47,   48,   61,   91,   93,   94,   95,  123,  125,
	    9,   10,   32,   38,   42,   46,   49,   57,   58,   64,   65,   90,
	   97,  122,  124,  126,    1,   13,   33,   34,   35,   36,   39,   40,
	   41,   44,   47,   48,   91,   93,   94,   95,  123,  125,    9,   10,
	   32,   38,   42,   46,   49,   57,   58,   64,   65,   90,   97,  122,
	  124,  126,    1,   13,   33,   34,   35,   36,   39,   40,   41,   44,
	   47,   48,   91,   93,   94,   95,  123,  125,    9,   10,   32,   38,
	   42,   46,   49,   57,   58,   64,   65,   90,   97,  122,  124,  126,
	    1,   13,   33,   34,   35,   36,   39,   40,   41,   44,   47,   48,
	   91,   93,   94,   95,  123,  125,    9,   10,   32,   38,   42,   46,
	   49,   57,   58,   64,   65,   90,   97,  122,  124,  126,    1,   13,
	   33,   34,   35,   36,   39,   40,   41,   42,   43,   47,   48,   61,
	   91,   93,   94,   95,  123,  125,    9,   10,   32,   44,   45,   46,
	   49,   57,   58,   64,   65,   90,   97,  122,  124,  126,    1,   13,
	   33,   34,   35,   36,   39,   40,   41,   44,   47,   48,   91,   93,
	   94,   95,  123,  125,    9,   10,   32,   38,   42,   46,   49,   57,
	   58,   64,   65,   90,   97,  122,  124,  126,    1,   13,   33,   34,
	   35,   36,   39,   40,   41,   44,   46,   47,   69,   88,   91,   93,
	   94,   95,  101,  120,  123,  125,    9,   10,   32,   38,   42,   45,
	   48,   57,   58,   64,   65,   90,   97,  122,  124,  126,    1,   13,
	   33,   34,   35,   36,   39,   40,   41,   44,   47,   69,   91,   93,
	   94,   95,  101,  123,  125,    9,   10,   32,   38,   42,   46,   48,
	   57,   58,   64,   65,   90,   97,  122,  124,  126,    1,   13,   33,
	   34,   35,   36,   39,   40,   41,   44,   47,   91,   93,   94,   95,
	  123,  125,    9,   10,   32,   38,   42,   46,   48,   57,   58,   64,
	   65,   90,   97,  122,  124,  126,    1,   13,   33,   34,   35,   36,
	   39,   40,   41,   44,   47,   91,   93,   94,   95,  123,  125,    9,
	   10,   32,   38,   42,   46,   48,   57,   58,   64,   65,   90,   97,
	  122,  124,  126,    1,   13,   33,   34,   35,   36,   39,   40,   41,
	   44,   46,   47,   69,   91,   93,   94,   95,  101,  123,  125,    9,
	   10,   32,   38,   42,   45,   48,   57,   58,   64,   65,   90,   97,
	  122,  124,  126,    1,   13,   33,   34,   35,   36,   39,   40,   41,
	   44,   47,   91,   93,   94,   95,  123,  125,    9,   10,   32,   38,
	   42,   46,   48,   57,   58,   64,   65,   70,   71,   90,   97,  102,
	  103,  122,  124,  126,    1,   13,   33,   34,   35,   36,   39,   40,
	   41,   44,   47,   48,   91,   93,   94,   95,  123,  125,    9,   10,
	   32,   38,   42,   46,   49,   57,   58,   64,   65,   90,   97,  122,
	  124,  126,    1,   13,   33,   34,   35,   36,   39,   40,   41,   44,
	   47,   91,   93,   94,   95,  123,  125,    9,   10,   32,   38,   42,
	   46,   48,   57,   58,   64,   65,   90,   97,  122,  124,  126,    1,
	   13,   33,   34,   35,   36,   39,   40,   41,   44,   47,   48,   91,
	   93,   94,   95,  123,  125,    9,   10,   32,   38,   42,   46,   49,
	   57,   58,   64,   65,   90,   97,  122,  124,  126,    0
	};
}

private static final char _php_lexer_trans_keys[] = init__php_lexer_trans_keys_0();


private static byte[] init__php_lexer_single_lengths_0()
{
	return new byte [] {
	    0,    2,    2,    2,    0,    1,    1,    2,    0,    0,    1,    2,
	    0,   18,   19,   18,   18,   18,   20,   18,   22,   19,   17,   17,
	   20,   17,   18,   17,   18
	};
}

private static final byte _php_lexer_single_lengths[] = init__php_lexer_single_lengths_0();


private static byte[] init__php_lexer_range_lengths_0()
{
	return new byte [] {
	    0,    0,    0,    0,    0,    0,    0,    0,    1,    3,    2,    3,
	    0,    8,    8,    8,    8,    8,    8,    8,    8,    8,    8,    8,
	    8,   10,    8,    8,    8
	};
}

private static final byte _php_lexer_range_lengths[] = init__php_lexer_range_lengths_0();


private static short[] init__php_lexer_index_offsets_0()
{
	return new short [] {
	    0,    0,    3,    6,    9,   10,   12,   14,   17,   19,   23,   27,
	   33,   34,   61,   89,  116,  143,  170,  199,  226,  257,  285,  311,
	  337,  366,  394,  421,  447
	};
}

private static final short _php_lexer_index_offsets[] = init__php_lexer_index_offsets_0();


private static short[] init__php_lexer_indicies_0()
{
	return new short [] {
	    1,    2,    0,    4,    4,    3,    1,    6,    5,    5,    8,    7,
	    9,    7,   10,   10,   11,   12,   11,   13,   13,   13,   11,   14,
	   14,   14,   11,   14,   15,   14,   14,   14,   11,    0,   16,   16,
	   17,    0,    3,   18,    5,   19,   20,   16,   21,   22,   25,   26,
	   16,   24,   27,   28,   16,   16,   17,   23,   16,   24,   24,   16,
	   11,   16,   16,   17,    0,    3,   18,    5,   19,   20,   16,   21,
	   22,   29,   25,   26,   16,   24,   27,   28,   16,   16,   17,   23,
	   16,   24,   24,   16,   11,   30,   30,   31,   32,   33,   34,   35,
	   36,   37,   30,   38,   39,   42,   43,   30,   41,   44,   45,   30,
	   30,   31,   40,   30,   41,   41,   30,   11,   46,   46,   47,   48,
	   49,   50,   51,   52,   53,   46,   54,   55,   58,   59,   46,   57,
	   60,   61,   46,   46,   47,   56,   46,   57,   57,   46,   11,   16,
	   16,   17,    0,    3,   18,    5,   19,   20,   16,   21,   22,   25,
	   26,   16,   62,   63,   28,   16,   16,   17,   23,   16,   62,   62,
	   16,   11,   16,   16,   17,    0,    3,   18,    5,   19,   20,    7,
	   17,    3,   22,   29,   25,   26,   16,   24,   27,   28,   16,   16,
	   17,   23,   16,   24,   24,   16,   11,   64,   64,   65,   66,   67,
	   68,   69,   70,   71,   64,   72,   73,   76,   77,   64,   75,   78,
	   79,   64,   64,   65,   74,   64,   75,   75,   64,   11,   16,   16,
	   17,    0,    3,   18,    5,   19,   20,   16,   80,   21,   81,   82,
	   25,   26,   16,   24,   81,   82,   27,   28,   16,   16,   17,   23,
	   16,   24,   24,   16,   11,   16,   16,   17,    0,    3,   18,    5,
	   19,   20,   16,   21,   81,   25,   26,   16,   24,   81,   27,   28,
	   16,   16,   17,   80,   16,   24,   24,   16,   11,   83,   83,   84,
	   85,   86,   87,   88,   89,   90,   83,   91,   93,   94,   83,   92,
	   95,   96,   83,   83,   84,   92,   83,   92,   92,   83,   11,   16,
	   16,   17,    0,    3,   18,    5,   19,   20,   16,   21,   25,   26,
	   16,   24,   27,   28,   16,   16,   17,   12,   16,   24,   24,   16,
	   11,   16,   16,   17,    0,    3,   18,    5,   19,   20,   16,   80,
	   21,   81,   25,   26,   16,   24,   81,   27,   28,   16,   16,   17,
	   23,   16,   24,   24,   16,   11,   16,   16,   17,    0,    3,   18,
	    5,   19,   20,   16,   21,   25,   26,   16,   24,   27,   28,   16,
	   16,   17,   13,   16,   13,   24,   13,   24,   16,   11,   97,   97,
	   98,   99,  100,  101,  102,  103,  104,   97,  105,  106,  109,  110,
	   97,  108,  111,  112,   97,   97,   98,  107,   97,  108,  108,   97,
	   11,  113,  113,  114,  115,  116,  117,  118,  119,  120,  113,  121,
	  122,  123,  113,   62,  124,  125,  113,  113,  114,   62,  113,   62,
	   62,  113,   11,  126,  126,  127,  128,  129,  130,  131,  132,  133,
	  126,  134,  135,  138,  139,  126,  137,  140,  141,  126,  126,  127,
	  136,  126,  137,  137,  126,   11,    0
	};
}

private static final short _php_lexer_indicies[] = init__php_lexer_indicies_0();


private static byte[] init__php_lexer_trans_targs_0()
{
	return new byte [] {
	    1,   15,   12,    2,   16,    3,    4,    5,    6,   19,    8,    0,
	   23,   25,   11,   28,   13,   14,   17,   13,   13,   18,   20,   24,
	   22,   13,   13,   13,   13,   26,   13,   14,    1,    2,   17,    3,
	   13,   13,   18,   20,   24,   22,   13,   13,   13,   13,   13,   14,
	    1,    2,   17,    3,   13,   13,   18,   20,   24,   22,   13,   13,
	   13,   13,   27,   10,   13,   14,    1,    2,   17,    3,   13,   13,
	   18,   20,   24,   22,   13,   13,   13,   13,   21,    7,    9,   13,
	   14,    1,    2,   17,    3,   13,   13,   18,   22,   13,   13,   13,
	   13,   13,   14,    1,    2,   17,    3,   13,   13,   18,   20,   24,
	   22,   13,   13,   13,   13,   13,   14,    1,    2,   17,    3,   13,
	   13,   18,   13,   13,   13,   13,   13,   14,    1,    2,   17,    3,
	   13,   13,   18,   20,   24,   22,   13,   13,   13,   13
	};
}

private static final byte _php_lexer_trans_targs[] = init__php_lexer_trans_targs_0();


private static short[] init__php_lexer_trans_actions_0()
{
	return new short [] {
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,   27,    9,   11,    0,    0,    0,
	    1,   13,   15,   17,   19,    0,    5,    5,    5,    5,   74,    5,
	   56,   59,    5,    5,    5,   53,   62,   65,   68,   71,    7,    7,
	    7,    7,   98,    7,   80,   83,    7,    7,    7,   77,   86,   89,
	   92,   95,    0,    0,   29,   29,   29,   29,  198,   29,  174,  178,
	   29,   29,   29,  170,  182,  186,  190,  194,    0,    0,    0,    3,
	    3,    3,    3,   50,    3,   32,   35,    3,    0,   38,   41,   44,
	   47,   21,   21,   21,   21,  122,   21,  104,  107,   21,   21,   21,
	  101,  110,  113,  116,  119,   23,   23,   23,   23,  143,   23,  125,
	  128,   23,  131,  134,  137,  140,   25,   25,   25,   25,  167,   25,
	  149,  152,   25,   25,   25,  146,  155,  158,  161,  164
	};
}

private static final short _php_lexer_trans_actions[] = init__php_lexer_trans_actions_0();


private static short[] init__php_lexer_eof_actions_0()
{
	return new short [] {
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    5,    7,    0,    0,   29,    0,    0,    3,    0,
	    0,    0,   21,   23,   25
	};
}

private static final short _php_lexer_eof_actions[] = init__php_lexer_eof_actions_0();


static final int php_lexer_start = 13;
static final int php_lexer_first_final = 13;
static final int php_lexer_error = 0;

static final int php_lexer_en_main = 13;


// line 114 "PhpScore.rl"

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

        
// line 335 "PhpScore.java"
	{
	cs = php_lexer_start;
	}

// line 131 "PhpScore.rl"
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
        
// line 354 "PhpScore.java"
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

	_trans = _php_lexer_indicies[_trans];
	cs = _php_lexer_trans_targs[_trans];

	if ( _php_lexer_trans_actions[_trans] != 0 ) {
		_acts = _php_lexer_trans_actions[_trans];
		_nacts = (int) _php_lexer_actions[_acts++];
		while ( _nacts-- > 0 )
	{
			switch ( _php_lexer_actions[_acts++] )
			{
	case 0:
// line 22 "PhpScore.rl"
	{ score += 0.2; }
	break;
	case 1:
// line 29 "PhpScore.rl"
	{ ps = p; }
	break;
	case 2:
// line 29 "PhpScore.rl"
	{ score += keyword_score(); }
	break;
	case 3:
// line 31 "PhpScore.rl"
	{ score += 0.2; }
	break;
	case 4:
// line 32 "PhpScore.rl"
	{ score += 0.2; }
	break;
	case 5:
// line 33 "PhpScore.rl"
	{ count_par += 1; }
	break;
	case 6:
// line 34 "PhpScore.rl"
	{ if (count_par > 0) { count_par -= 1; } else { black = true; } }
	break;
	case 7:
// line 35 "PhpScore.rl"
	{ count_bracket += 1; }
	break;
	case 8:
// line 36 "PhpScore.rl"
	{ if (count_bracket > 0) { count_bracket -= 1; } else { black = true; } }
	break;
	case 9:
// line 37 "PhpScore.rl"
	{ count_brace += 1; }
	break;
	case 10:
// line 38 "PhpScore.rl"
	{ if (count_brace > 0) { count_brace -= 1; } else { black = true; } }
	break;
	case 11:
// line 39 "PhpScore.rl"
	{ score += 0.1; }
	break;
	case 12:
// line 40 "PhpScore.rl"
	{ score += 0.3; }
	break;
	case 13:
// line 41 "PhpScore.rl"
	{ score += 0.5; }
	break;
	case 14:
// line 44 "PhpScore.rl"
	{ score += 0.1; }
	break;
// line 494 "PhpScore.java"
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
// line 22 "PhpScore.rl"
	{ score += 0.2; }
	break;
	case 2:
// line 29 "PhpScore.rl"
	{ score += keyword_score(); }
	break;
	case 3:
// line 31 "PhpScore.rl"
	{ score += 0.2; }
	break;
	case 4:
// line 32 "PhpScore.rl"
	{ score += 0.2; }
	break;
	case 11:
// line 39 "PhpScore.rl"
	{ score += 0.1; }
	break;
	case 12:
// line 40 "PhpScore.rl"
	{ score += 0.3; }
	break;
	case 13:
// line 41 "PhpScore.rl"
	{ score += 0.5; }
	break;
// line 543 "PhpScore.java"
		}
	}
	}

case 5:
	}
	break; }
	}

// line 144 "PhpScore.rl"

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
