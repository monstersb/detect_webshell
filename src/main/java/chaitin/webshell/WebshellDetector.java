package chaitin.webshell;

import java.util.List;

import chaitin.webshell.decoder.Pair;
import chaitin.webshell.decoder.QueryString;

public class WebshellDetector {
	
	
	public static boolean isWebshell(String input) {
		
		List<Pair> plist = QueryString.query_string(input.getBytes());
		for (Pair p : plist) {
			if ("z0".equals(new String(p.first))) {
				// return true;
			}
			
			if (WebshellScorer.phpScore(new String(p.second)) > 1e-9) {
				return true;
			}
		}
		
		return false;
	}

	public static void main(String[] args) {
		System.out.println(isWebshell("echo $a;"));
	}

}
