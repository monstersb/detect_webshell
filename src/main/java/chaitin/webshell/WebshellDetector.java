package chaitin.webshell;

import java.util.List;

import chaitin.webshell.decoder.Pair;
import chaitin.webshell.decoder.QueryString;
import chaitin.webshell.decoder.Unquote;

public class WebshellDetector {
	
    public static boolean isSensitiveKey(byte[] key) {
    	
		String k = new String(key);
		
		return ("z0".equals(k) || "hk715".equals(k));
	}
	
    public static int scoreSensitiveKey(byte[] key) {
    	
		String k = new String(key);
		
		if ("z0".equals(k) || "hk715".equals(k)) {
			return 2;
		}
		return 0;
	}
    
	public static boolean isWebshell(String uri, String data) {
		
    	List<Pair<byte[], byte[]>> plist = QueryString.query_string(data.getBytes());
    	
    	for (Pair<byte[], byte[]> p : plist) {
    		// if (isSensitiveKey(Unquote.unquote(p.first))) {
    		//	return true;
    		//}
    		
    		// System.out.println(new String(Unquote.unquote(p.second)));
    		if (WebshellTokenizer.webShellScore(new String(Unquote.unquote(p.second))) + scoreSensitiveKey(p.first) >= 3) {
    			return true;
    		}
    	}
    	
    	uri = uri.substring(uri.indexOf('?') + 1);
    	
    	if (uri.length() > 1) {
	    	plist = QueryString.query_string(uri.getBytes());
	    	for (Pair<byte[], byte[]> p : plist) {
	    		
	    		byte[] byte_key = Unquote.unquote(p.first);
	    		String key = new String(byte_key);
	    		String value = new String(Unquote.unquote(p.second));
	    		
	    		if (isSensitiveKey(byte_key)) {
	    			return true;
	    		}
	    		if (WebshellTokenizer.webShellScore(key) >= 3) {
	    			return true;
	    		}
	    		if (WebshellTokenizer.webShellScore(value) >= 3) {
	    			return true;
	    		}
	    	}
    	}
		return false;
	}

	public static void main(String[] args) {
    	// List<Pair<byte[], byte[]>> plist = QueryString.query_string("/44b676ed1a4a6ea7ba0918cf05093f1d/f9a1e3cd54ace2b54024e1b21a7637ab?_timestamp_=rc_time_grit_hour_one".getBytes());
    	
    	String s = "ysh=execute(\"response.clear:response.write(\"\"jinlaile\"\"):response.end\")";
    	System.out.println(isWebshell("", s) == true);
    	
    	s = "xiaoliang=Execute(\"Execute(\"\"On+Error+Resume+Next:Function+bd%28byVal+s%29%3AFor+i%3D1+To+Len%28s%29+Step+2%3Ac%3DMid%28s%2Ci%2C2%29%3AIf+IsNumeric%28Mid%28s%2Ci%2C1%29%29+Then%3AExecute%28%22%22%22%22bd%3Dbd%26chr%28%26H%22%22%22%22%26c%26%22%22%22%22%29%22%22%22%22%29%3AElse%3AExecute%28%22%22%22%22bd%3Dbd%26chr%28%26H%22%22%22%22%26c%26Mid%28s%2Ci%2B2%2C2%29%26%22%22%22%22%29%22%22%22%22%29%3Ai%3Di%2B2%3AEnd+If%22%22%26chr%2810%29%26%22%22Next%3AEnd+Function:Response.Write(\"\"\"\"#onewordbackdoor->|\"\"\"\"):Execute(\"\"\"\"On+Error+Resume+Next:\"\"\"\"%26bd(\"\"\"\"44696D20533A533D5365727665722E4D61707061746828222E2229266368722839293A53455420433D4372656174654F626A6563742822536372697074696E672E46696C6553797374656D4F626A656374";
    	System.out.println(isWebshell("", s) == true);
    	
    	s = "sd=Execute++++++++++++++++++++++++++++++(\"++++++++++++++++++++++++++++++Execute++++++++++++++++++++++++++++++(\"\"++++++++++:Function+bd%28byVal+s%29%3AFor+i%3D1+To+Len%28s%29+Step+2%3Ac%3DMid%28s%2Ci%2C2%29%3AIf+IsNumeric%28Mid%28s%2Ci%2C1%29%29+Then%3AExecute%28%22%22%22%22bd%3Dbd%26chr%28%26H%22%22%22%22%26c%26%22%22%22%22%29%22%22%22%22%29%3AElse%3AExecute%28%22%22%22%22bd%3Dbd%26chr%28%26H%22%22%22%22%26c%26Mid%28s%2Ci%2B2%2C2%29%26%22%22%22%22%29%22%22%22%22%29%3Ai%3Di%2B2%3AEnd+If%22%22%26chr%2810%29%26%22%22Next%3AEnd+Function:Response.Write(\"\"\"\"->|\"\"\"\"):++++++++";
    	System.out.println(isWebshell("", s) == true);
    	
    	s = "cmd=%40eval%2F%2A%CE%D2%C8%A5%C4%E3%C2%EE%C1%CB%B8%F4%B1%DA%2A%2F%01%28%24%7B%27%5FP%27.%27OST%27%7D%5Bz9%5D%2F%2A%CE%D2%C8%A5%C4%E3%C2%EE%C1%CB%B8%F4%B1%DA%2A%2F%01%28%24%7B%27%5FPOS%27.%27T%27%7D%5Bz0%5D%29%29%3B&z0=Nzc0MTEwO0Bpbmlfc2V0KCJkaXNwbGF5X2Vycm9ycyIsIjAiKTtAc2V0X3RpbWVfbGltaXQoMCk7QHNldF9tYWdpY19xdW90ZXNfcnVudGltZSgwKTtlY2hvKCItPnwiKTs7ZnVuY3Rpb24gc2V0X3dyaXRlYWJsZSgkZmlsZV9uYW1lKXtpZihAY2htb2QoJGZpbGVfbmFtZSxiYXNlX2NvbnZlcnQoYmFzZTY0X2RlY29kZSgkX1BPU1RbIngyIl0pLDgsMTApKSl7ZWNobyAiMSI7fWVsc2V7ZWNobyAiLTEiO319c2V0X3dyaXRlYWJsZShiYXNlNjRfZGVjb2RlKCRfUE9TVFsieDEiXSkpO2VjaG8oInw8LSIpOztkaWUoKTs%3D&x1=RDovd2Vic2l0ZXMveGluc2p6LmNvbS9wdWJsaWNfaHRtbC9uZXdzLzA0MDM0MjY0Lmh0bWw%3D&x2=MDY2Ng%3D%3D&z9=BaSE64%5FdEcOdE";
    	System.out.println(isWebshell("", s) == true);
    	
	}

}
