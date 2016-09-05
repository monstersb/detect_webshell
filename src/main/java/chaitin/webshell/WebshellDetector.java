package chaitin.webshell;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chaitin.utils.Base64;
import chaitin.utils.Pair;
import chaitin.utils.QueryString;
import chaitin.utils.Unquote;
import chaitin.webshell.parser.AspScore;
import chaitin.webshell.parser.PhpScore;

public class WebshellDetector {

	static double thres_hold = 2.0;
	
    static Map<String, Double> sensitive_key = new HashMap<String, Double>() {
		private static final long serialVersionUID = 97314362015453784L;

	{
        put("z", 1.0);
        put("z0", 1.5);
        put("z1", 1.0);
        put("z2", 1.0);
        put("caidao", 1.8);
        put("mb", 0.8);
        put("hk715", 1.2);
        put("xise", 1.5);
        put("diaosi", 1.0);
    }};

	
    public static double scorePhp_one(byte[] payload) {
    	double score = new PhpScore(payload).score();
    	byte[] payload_base64 = Base64.decode_base64(payload);
    	double score_base64_decoded = new PhpScore(payload_base64).score();
    	if (score_base64_decoded > 0.8) {
    		score_base64_decoded += 0.3;
    	}
    	return score > score_base64_decoded ? score : score_base64_decoded;
    }
	
    public static double scorePhp(byte[] payload) {
    	String s = new String(payload);
    	double score = scorePhp_one(payload);
    	/*if (s.indexOf('"') + 1 < payload.length) {
        	double tscore = scorePhp_one(s.substring(s.indexOf('"') + 1).getBytes());
        	score = score > tscore ? score : tscore;
    	}
    	if (s.indexOf('\'') + 1 < payload.length) {
        	double tscore = scorePhp_one(s.substring(s.indexOf('\'') + 1).getBytes());
        	score = score > tscore ? score : tscore;
    	}*/
    	return score;
    }
    
    public static double scoreAsp(byte[] payload) {
    	double score = new AspScore(payload).score();
    	byte[] payload_base64 = Base64.decode_base64(payload);
    	byte[] payload_without_op = AspScore.filter_strop(payload);
    	double score_without_op = new AspScore(payload_without_op).score();
    	double score_base64_decoded = new AspScore(payload_base64).score();
    	if (score_base64_decoded > 0.8) {
    		score_base64_decoded += 0.3;
    	}
    	score = score > score_base64_decoded ? score : score_base64_decoded;
    	return score;
    }

    public static double score_key(byte[] payload) {
    	String s = new String(payload).toLowerCase();
        if (sensitive_key.containsKey(s)) {
            return sensitive_key.get(s);
        }
        double score = 0.0;
        for (char c: s.toCharArray()) {
        	if (!Character.isLetter(c) 
        			&& !Character.isDigit(c)
        			&& c != '_'
        			&& c != '$') {
        		score -= 0.5;
        	}
        }
        return score;
    }
    
    public static double score(byte[] payload) {
    	//return WebshellTokenizer.scoreTokens(new String(payload));
    	double score_php = scorePhp(payload);
    	double score_asp = scoreAsp(payload);
    	return score_php > score_asp ? score_php : score_asp;
    }
    
	public static boolean isWebshell(String uri, String data) {
		
    	List<Pair<byte[], byte[]>> plist = QueryString.query_string(data.getBytes());
    	
    	for (Pair<byte[], byte[]> p : plist) {
    		byte[] key = Unquote.unquote(p.first);	    		
    		byte[] value = Unquote.unquote(p.second);
    		
    		if (score(key) >= thres_hold) {
    			return true;
    		}
    		if (score_key(key) + score(value) >= thres_hold) {
    			return true;
    		}
    	}
    	/*
    	uri = uri.substring(uri.indexOf('?') + 1);
    	
    	if (uri.length() > 1) {
	    	plist = QueryString.query_string(uri.getBytes());
	    	for (Pair<byte[], byte[]> p : plist) {
	    		
	    		byte[] key = Unquote.unquote(p.first);	    		
	    		byte[] value = Unquote.unquote(p.second);
	    		
	    		if (score(key) >= thres_hold) {
	    			return true;
	    		}
	    		if (score(value) >= thres_hold) {
	    			return true;
	    		}
	    	}
    	}*/
		return false;
	}

	public static void main(String[] args) throws Exception {
    	// List<Pair<byte[], byte[]>> plist = QueryString.query_string("/44b676ed1a4a6ea7ba0918cf05093f1d/f9a1e3cd54ace2b54024e1b21a7637ab?_timestamp_=rc_time_grit_hour_one".getBytes());
    	
    	//String s = "ysh=execute(\"response.clear:response.write(\"\"jinlaile\"\"):response.end\")";
    	//System.out.println(isWebshell("", s) == true);
    	
    	//s = "xiaoliang=Execute(\"Execute(\"\"On+Error+Resume+Next:Function+bd%28byVal+s%29%3AFor+i%3D1+To+Len%28s%29+Step+2%3Ac%3DMid%28s%2Ci%2C2%29%3AIf+IsNumeric%28Mid%28s%2Ci%2C1%29%29+Then%3AExecute%28%22%22%22%22bd%3Dbd%26chr%28%26H%22%22%22%22%26c%26%22%22%22%22%29%22%22%22%22%29%3AElse%3AExecute%28%22%22%22%22bd%3Dbd%26chr%28%26H%22%22%22%22%26c%26Mid%28s%2Ci%2B2%2C2%29%26%22%22%22%22%29%22%22%22%22%29%3Ai%3Di%2B2%3AEnd+If%22%22%26chr%2810%29%26%22%22Next%3AEnd+Function:Response.Write(\"\"\"\"#onewordbackdoor->|\"\"\"\"):Execute(\"\"\"\"On+Error+Resume+Next:\"\"\"\"%26bd(\"\"\"\"44696D20533A533D5365727665722E4D61707061746828222E2229266368722839293A53455420433D4372656174654F626A6563742822536372697074696E672E46696C6553797374656D4F626A656374";
    	//System.out.println(isWebshell("", s) == true);
    	
    	//s = "sd=Execute++++++++++++++++++++++++++++++(\"++++++++++++++++++++++++++++++Execute++++++++++++++++++++++++++++++(\"\"++++++++++:Function+bd%28byVal+s%29%3AFor+i%3D1+To+Len%28s%29+Step+2%3Ac%3DMid%28s%2Ci%2C2%29%3AIf+IsNumeric%28Mid%28s%2Ci%2C1%29%29+Then%3AExecute%28%22%22%22%22bd%3Dbd%26chr%28%26H%22%22%22%22%26c%26%22%22%22%22%29%22%22%22%22%29%3AElse%3AExecute%28%22%22%22%22bd%3Dbd%26chr%28%26H%22%22%22%22%26c%26Mid%28s%2Ci%2B2%2C2%29%26%22%22%22%22%29%22%22%22%22%29%3Ai%3Di%2B2%3AEnd+If%22%22%26chr%2810%29%26%22%22Next%3AEnd+Function:Response.Write(\"\"\"\"->|\"\"\"\"):++++++++";
    	//System.out.println(isWebshell("", s) == true);
    	
    	//s = "cmd=%40eval%2F%2A%CE%D2%C8%A5%C4%E3%C2%EE%C1%CB%B8%F4%B1%DA%2A%2F%01%28%24%7B%27%5FP%27.%27OST%27%7D%5Bz9%5D%2F%2A%CE%D2%C8%A5%C4%E3%C2%EE%C1%CB%B8%F4%B1%DA%2A%2F%01%28%24%7B%27%5FPOS%27.%27T%27%7D%5Bz0%5D%29%29%3B&z0=Nzc0MTEwO0Bpbmlfc2V0KCJkaXNwbGF5X2Vycm9ycyIsIjAiKTtAc2V0X3RpbWVfbGltaXQoMCk7QHNldF9tYWdpY19xdW90ZXNfcnVudGltZSgwKTtlY2hvKCItPnwiKTs7ZnVuY3Rpb24gc2V0X3dyaXRlYWJsZSgkZmlsZV9uYW1lKXtpZihAY2htb2QoJGZpbGVfbmFtZSxiYXNlX2NvbnZlcnQoYmFzZTY0X2RlY29kZSgkX1BPU1RbIngyIl0pLDgsMTApKSl7ZWNobyAiMSI7fWVsc2V7ZWNobyAiLTEiO319c2V0X3dyaXRlYWJsZShiYXNlNjRfZGVjb2RlKCRfUE9TVFsieDEiXSkpO2VjaG8oInw8LSIpOztkaWUoKTs%3D&x1=RDovd2Vic2l0ZXMveGluc2p6LmNvbS9wdWJsaWNfaHRtbC9uZXdzLzA0MDM0MjY0Lmh0bWw%3D&x2=MDY2Ng%3D%3D&z9=BaSE64%5FdEcOdE";
    	//System.out.println(isWebshell("", s) == true);
    	
		//String s = "z0=NTYwNjQ4O0Bpbmlfc2V0KCJkaXNwbGF5X2Vycm9ycyIsIjAiKTtAc2V0X3RpbWVfbGltaXQoMCk7QHNldF9tYWdpY19xdW90ZXNfcnVudGltZSgwKTtlY2hvKCItPnwiKTs7ZWNobyBAZndyaXRlKGZvcGVuKGJhc2U2NF9kZWNvZGUoJF9QT1NUWyJ6MSJdKSwidyIpLGJhc2U2NF9kZWNvZGUoJF9QT1NUWyJ6MiJdKSk%";
		//System.out.println(isWebshell("", s) == true);
		/*
        BufferedReader br = new BufferedReader(new InputStreamReader(
        new FileInputStream("/tmp/z0.post_data")));
        int c = 0;
        for (String line = br.readLine(); line != null; line = br.readLine()) {
        	if (!isWebshell("", line)) {
            	System.out.println(line);
            	c += 1;
            	if (c > 20) {
            		break;
            	}
        	}
        }
        br.close();*/
	}

}
