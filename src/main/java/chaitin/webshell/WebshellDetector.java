package chaitin.webshell;

import java.util.List;

import chaitin.webshell.decoder.Pair;
import chaitin.webshell.decoder.QueryString;

public class WebshellDetector {
	
    public static boolean isSensitiveKey(byte[] key) {
    	
		String k = new String(key);
		
		return  ("z0".equals(k));// || "cd".equals(k) || "hk715".equals(k) || "mb".equals(k));
	}
	
    public static boolean isWebShellCode(byte[] value) {
    	return WebshellScorer.phpScore(new String(value)) > 1e-9;
	}
    
	public static boolean isWebshell(String uri, String data) {
		
    	List<Pair<byte[], byte[]>> plist = QueryString.query_string(data.getBytes());
    	
    	for (Pair<byte[], byte[]> p : plist) {
    		if (isSensitiveKey(p.first) || isWebShellCode(p.second)) {
    			return true;
    		}
    		if (WebshellTokenizer.webShellScore(new String(p.second)) >= 3) {
    			return true;
    		}
    	}
    	
    	
		return false;
	}

	public static void main(String[] args) {
		System.out.println(isWebShellCode("echo $a;".getBytes()));
    	List<Pair<byte[], byte[]>> plist = QueryString.query_string("/44b676ed1a4a6ea7ba0918cf05093f1d/f9a1e3cd54ace2b54024e1b21a7637ab?_timestamp_=rc_time_grit_hour_one".getBytes());
    	
    	for (Pair<byte[], byte[]> p : plist) {
    		System.out.println(new String(p.first));
    		System.out.println(new String(p.second));
    	}
	}

}
