package chaitin.webshell;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import chaitin.decoder.Base64;
import chaitin.decoder.Pair;
import chaitin.decoder.QueryString;
import chaitin.decoder.Unquote;
import chaitin.webshell.parser.PhpScore;

public class WebshellDetector {
	
    public static boolean isSensitiveKey(byte[] key) {
		return (Arrays.equals("z0".getBytes(), key) || Arrays.equals("hk715".getBytes(), key));
	}
    
    public static double score(byte[] payload) {
    	double score = new PhpScore(payload).score();
    	byte[] payload_base64 = Base64.decode_base64(payload);
    	//System.out.println(new String(payload));
    	//System.out.println(new String(payload_base64));
    	double score_base64_decoded = new PhpScore(payload_base64).score();
    	return score > score_base64_decoded ? score : score_base64_decoded;
    }
    
	public static boolean isWebshell(String uri, String data) {
		
    	List<Pair<byte[], byte[]>> plist = QueryString.query_string(data.getBytes());
    	
    	for (Pair<byte[], byte[]> p : plist) {
    		byte[] key = Unquote.unquote(p.first);	    		
    		byte[] value = Unquote.unquote(p.second);
    		
    		if (isSensitiveKey(key) && value.length > 10) {
    		  return true;
    		}
    		if (score(key) >= 1.5) {
    			return true;
    		}
    		if (score(value) >= 1.5) {
    			return true;
    		}
    	}
    	
    	uri = uri.substring(uri.indexOf('?') + 1);
    	
    	if (uri.length() > 1) {
	    	plist = QueryString.query_string(uri.getBytes());
	    	for (Pair<byte[], byte[]> p : plist) {
	    		
	    		byte[] key = Unquote.unquote(p.first);	    		
	    		byte[] value = Unquote.unquote(p.second);
	    		
	    		if (score(key) >= 1.5) {
	    			return true;
	    		}
	    		if (score(value) >= 1.5) {
	    			return true;
	    		}
	    	}
    	}
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
        br.close();
	}

}
