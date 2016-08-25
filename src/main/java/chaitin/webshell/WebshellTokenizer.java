package chaitin.webshell;

import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class WebshellTokenizer {
	
	// no _
	private static final String separators = "[`~!@#$%^&*()+=\\-{}\\[\\]|\\:;\"<>',.?/\\\\ \n\r\t]";
	
	private static final HashMap tokenScore = new HashMap() {{ 
		put("array", 2); 
		put("array_map", 2); 
		put("base64_decode", 2); 
		put("catch", 1); 
		put("classLoader", 3); 
		put("display_errors", 2); 
		put("echo", 2); 
		put("encoding", 2); 
		put("eval", 3); 
		put("exception", 2); 
		put("execute", 2); 
		put("frombase64string", 2); 
		put("getencoding", 2); 
		put("ini_set", 2); 
		put("md5", 1); 
		put("phpinfo", 3); 
		put("println", 2); 
		put("response", 2); 
		put("set_ti", 2); 
		put("system", 2); 
		put("try", 1); 
		put("write", 1); 
		put("_post", 1); 
	}};
	
	public static int scoreTokens(String input) {
		
		HashSet<String> used = new HashSet<String>();
		used.clear();
		
		int score = 0;
		
		String[] tokens = input.split(separators);
		
		for (int i = 0; i < tokens.length; i++) {
			tokens[i] = tokens[i].toLowerCase();
			if (!used.contains(tokens[i]) && tokenScore.containsKey(tokens[i])) {
				score += (Integer)tokenScore.get(tokens[i]);
				used.add(tokens[i]);
			}
		}
		return score;
	}
	
	public static int webShellScore(String input) {
		String decodedInput = "";
		try {
			decodedInput = new String(Base64.getDecoder().decode(input));
		} catch (Exception e) {
			decodedInput = "";
		}
		return Math.max(scoreTokens(input), scoreTokens(decodedInput)); 
	}
	
	public static void main(String[] args) {
		
		String s = "2`3~4$1!5@6#7$8%9^8&7*6(5)4+2=1-2{3}4[5]6|7:8;9\"8<7>6'5,4.3?2/1\\0 1\n2\r3\t4";
		String s1 = "mb=Response.Write(\"------>|\");var err:Exception;try{+++++++++++++++++++++++++eval++++++++++++++++++++(System.Text.Encoding.GetEncoding(936).GetString(System.Convert.FromBase64String(\"UmVzcG9uc2UuV3JpdGUoImhhb3JlbmdlLmNvbVFRMzE3Mjc1NzM4Iik7\")),\"unsafe\");}catch(err){Response.Write(\"ERROR:// \"+err.essage);}Response.Write(\"|<----\");Response.End();";
		String[] r = s1.split(separators);
		for (int i = 0; i< r.length; i++) {
			// System.out.println("[" + r[i] + "]");
		}
		
		System.out.println(1 + (Integer)tokenScore.get("eval"));
		try {
			System.out.println(new String(Base64.getDecoder().decode("NzU3MjIyO0Bpbmlfc2V0KCJkaXNwbGF5X2Vycm9ycyIsIjAiKTtAc2V0X3RpbWVfbGltaXQoMCk7QHNldF9tYWdpY19xdW90ZXNfcnVudGltZSgwKTtlY2hvKCItPnwiKTs7ZWNobyBAZndyaXRlKGZvcGVuKGJhc2U2NF9kZWNvZGUoJF9QT1NUWyJ6MSJdKSwidyIpLGJhc2fjkdsalfjas8*&(*2NF9kZWNvZGUoJF9QT1NUWyJ6MiJdKSk/IjEiOiIwIjtlY2hvKCJ8PC0iKTs7ZGllKCk7")));
		} catch (Exception e) {
			System.out.println("error");
		}
		
		System.out.println("AbcDEF".toLowerCase());
		System.out.println(webShellScore("hk715=Execute++++++++++++++++++++++++++++++(\"++++++++++++++++++++++++++++++Execute++++++++++++++++++++++++++++++(\"\"++++++++++:Function+bd(byVal+s):For+i=1+To+Len(s)+Step+2:c=Mid(s,i,2):If+IsNumeric(Mid(s,i,1))+Then:Execute(\"\"\"\"bd=bd&chr(&H\"\"\"\"&c&\"\"\"\")\"\"\"\"):Else:Execute(\"\"\"\"bd=bd&chr(&H\"\"\"\"&c&Mid(s,i+2,2)&\"\"\"\")\"\"\"\"):i=i+2:End+If\"\"&chr(10)&\"\"Next:End+Function:Response.Write(\"\"\"\"->|\"\"\"\"):+++++++++++++++++"));

	}
}
