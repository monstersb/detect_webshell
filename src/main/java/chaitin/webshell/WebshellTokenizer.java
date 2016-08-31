package chaitin.webshell;

import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class WebshellTokenizer {
	
	// no _
	private static final String separators = "[`~!@#$%^&*()+=\\-{}\\[\\]|\\:;\"<>',.?/\\\\ \n\r\t]";
	
	private static final HashMap<String, Integer> tokenScore = new HashMap<String, Integer>() {
		private static final long serialVersionUID = 5401942617951940220L;
	{ 
		put("allowstaticmethodaccess", 1); 
		put("array", 1); 
		put("array_map", 2); 
		put("base64_decode", 2); 
		put("catch", 1); 
		put("chr", 1); 
		put("create_function", 1); 
		put("display_errors", 2); 
		put("echo", 1); 
		put("encoding", 2); 
		put("eval", 2); 
		put("exception", 2); 
		put("execute", 1); 
		put("exit", 1); 
		put("frombase64string", 2); 
		put("getencoding", 2); 
		put("getinputstream", 2); 
		put("getrequest", 2); 
		put("getwriter", 2); 
		put("gzinflate", 2); 
		put("ini_set", 2); 
		put("isnumeri", 2); 
		put("md5", 1); 
		put("phpinfo", 2); 
		put("preg_replace", 2); 
		put("println", 2); 
		put("response", 1); 
		put("servletactioncontext", 1); 
		put("streamconnector", 1); 
		put("system", 1); 
		put("write", 1); 
		put("methodaccessor", 1); 
		put("_cookie", 1); 
		put("_get", 1);
		put("_post", 1); 
		put("_request", 1); 
		put("_server", 1); 
	}};
	
	private static final HashMap<String, Integer> dangerousTokenScore = new HashMap<String, Integer>() {
		private static final long serialVersionUID = 4464369449273331205L;
	{ 
		put("allowstaticmethodaccess", 1); 
		put("base64_decode", 2); 
		put("create_function", 1); 
		put("display_errors", 2); 
		put("eval", 2); 
		put("frombase64string", 2); 
		put("getencoding", 2); 
		put("getinputstream", 2); 
		put("getrequest", 2); 
		put("getwriter", 2); 
		put("gzinflate", 2); 
		put("ini_set", 2); 
		put("isnumeri", 2); 
		put("preg_replace", 2); 
		put("response", 1); 
		put("servletactioncontext", 1); 
		put("streamconnector", 1); 
		//put("try", 1); 
		put("methodaccessor", 1); 
		put("_cookie", 2); 
		put("_get", 2);
		put("_post", 2); 
		put("_request", 2); 
		put("_server", 2); 
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
		
		// Next is merge score.
		int mergeScore = 0;
		String mergeString = String.join("", tokens);
		
		Iterator<Entry<String, Integer>> iter = dangerousTokenScore.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String)entry.getKey();
			int val = (Integer)entry.getValue();
			if (mergeString.indexOf(key) >= 0) {
				mergeScore += val;
			}
		}
		return Math.max(score, mergeScore);
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
		String s2 = "wysiwyg=1&subject=2014-3-12-783070+new+balance+sneakers+e.mq.txf.jsp&message=Now+that+the+wind+fairy+emperor+say%2C+equal+opportunity+for+everyone+to.+As+long+as+you+have+fairy+crystal%2C%5Burl%3Dhttp%3A%2F%2Fnewbalancesneakers1.snack.ws%2F%5Dnew+balance+sneakers%5B%2Furl%5D%2C+you+can+switch+to+the+best+training+resources%2C+or+top+fairy.+For+a+time+the+square+the+atmosphere+again+warm+up%2C%5Burl%3Dhttp%3A%2F%2Flouisvuittoniphone5case.snack.ws%2F%5DLouis+vuitton+iPhone+5+Case%5B%2Furl%5D%2C+many+have+a+large+number+of+fairy+crystal+of+a+person+already+in+the+itch+for+a+try%2C%5Burl%3Dhttp%3A%2F%2Fgucciipadminicase.snack.ws%2F%5DGucci+iPad+Mini+Case%5B%2Furl%5D%2C+some+of+them+even+start+together%2C+ready+to+be+fairy+crystal+together+to+buy+top+class+fairy.+That+the+wind+fairy+emperor+in+response+to+the+following+is+nodded+with+satisfaction%2C+and+continued%3A+%22please+one+thousand+one+hundred+to+participate+in+the+small+area+of+the+elite+stage......%22+With+that+wind+fairy+emperor+voice+down%2C+one+thousand+one+hundred+have+the+jade+big+Luo+Xian+went+on+stage.+Although+it+is+one+thousand+one+hundred+people%2C+but+in+the+huge+platform%2C+still+appear+some+empty.+The+thistle+Hun+stood+on+the+platform+of+God+consciousness+and+sweep%2C+she+soon+went+up%2C+she+did+not+see+brother+leaves.+Visible+brother+not+jade%2C+should+leave+Hong+Yuxian+city.+Before+she+was+to+protect+his%2C+just+know+ye+silent+leave+Hong+Yuxian+city+is+already+a+few+days.+%22Now+please+Dragon+River+emperor+said+into+the+small+area+rule%2C+at+the+same+time+about+what+people+can+not+enter+the+small+area.%22+That+the+wind+fairy+emperor+finished%2C+immediately+stand+aside.+As+one+of+the+Great+Dragon+River+four%2C%5Burl%3Dhttp%3A%2F%2Fsnk.to%2FmcB8%5Dnew+balance+tilbud%5B%2Furl%5D%2C+looks+very+ordinary%2C+or+even+a+lost+in+the+crowd+which+are+not+to+be+found+in+middle-aged+men.+He+walked+out%2C+smiled%2C+looked+very+nice.+But+here+all+know%2C+the+dragon+river+is+one+of+the+four+emperor%2C+he+be+a+slap+in+the+face+can+crush+dozens+of+sin+city%2C+and+even+a+domain+could+not+help+his+toss.+The+great+dragon+river+walk+out+just+smiled%2C+square+it+seems+everyone+felt+the+gentle+temperament+of+the+great+dragon+river.+%22Into+the+small+area+is+not+easy%2C+we+need+ten+fairy+emperor+also+open+void+gap%2C+it+can+barely+go%2C%5Burl%3Dhttp%3A%2F%2Fsnk.to%2FmGgY%5DGucci+iPad+Mini+Case%5B%2Furl%5D%2C+so+limited.+To+be+fair%2C+there+are+people+carrying+space+world%2C+please+stand+up%2C+otherwise+it+will+confiscate+directly+lose+access+to+qualified+jade.%22+Dragon+River%2C+words+like+hammers+generally+play+in+some+carry+small+world%2C+or+carry+the+rest+space+to+cheat+people.+Although+the+Dragon+River+emperor+said+very+tactful%2C%5Burl%3Dhttp%3A%2F%2Fnewbalancetilbud.snack.ws%2F%5Dnew+balance+tilbud%5B%2Furl%5D%2C+but+everyone+knows+the+meaning+of+dragon+river.+%28second+also+sent%2C+thank+gold+teeth+2013%2C+I+1230%2C+skdavid%2C+dust+tear+a+few+friends+continuous+million+dollars+ticket%21%29+%28to+be+continued...+The+first+seven+eight+four+chapters%29+into+small+areas+of+full+text+updates%2C+TXT+download%2C+as+in+the+novel+the+great+Knight+http%3A%2F%2Fwww.xs74.com%2F+dragon+river+in+table+one+thousand+one+hundred+big+Luo+Xian+swept+past%2C+then+calmly+said%3A+%22now+please+carry+space+world+people+hand+over+world+space+of+their+own%2C%5Burl%3Dhttp%3A%2F%2Fnewbalance576sko.snack.ws%2F%5Dnew+balance+576+Sko%5B%2Furl%5D%2C+also+line+up+through+the+artifact+under+the+door......%22+Unequal+Dragon+River+emperor+will+finish%2C+seven+or+eight+big+Luo+Xian+has+active+flying+down%2C%5Burl%3Dhttp%3A%2F%2Fsnk.to%2FmE0p%5DBurberry+iPhone+5s+Case%5B%2Furl%5D%2C+will+own+small+world+to+his+martial+art+in+people+or+acquaintance.+Love+Xian+Chun+Qu%0D%0A++%0D%0A+++%5Burl%3Dhttp%3A%2F%2Fwww.988good.com%2Fbbs%2Fforum.php%3Fmod%3Dviewthread%26tid%3D288017%26fromuid%3D52970%5Dhttp%3A%2F%2Fwww.988good.com%2Fbbs%2Fforum.php%3Fmod%3Dviewthread%26tid%3D288017%26fromuid%3D52970%5B%2Furl%5D%0D%0A++%0D%0A+++%5Burl%3Dhttp%3A%2F%2Fwww.fadianzhan.com%2Fbbs%2Fhome.php%3Fmod%3Dspace%26uid%3D22628%26do%3Dblog%26quickforward%3D1%26id%3D245050%5Dhttp%3A%2F%2Fwww.fadianzhan.com%2Fbbs%2Fhome.php%3Fmod%3Dspace%26uid%3D22628%26do%3Dblog%26quickforward%3D1%26id%3D245050%5B%2Furl%5D%0D%0A++%0D%0A+++%5Burl%3Dhttp%3A%2F%2Fwww.hongniangxiehui.com%2Fforum.php%3Fmod%3Dviewthread%26tid%3D180733%5Dhttp%3A%2F%2Fwww.hongniangxiehui.com%2Fforum.php%3Fmod%3Dviewthread%26tid%3D180733%5B%2Furl%5D%0D%0A++%0D%0A+++%5Burl%3Dhttp%3A%2F%2Fwww.cuplf.com%2Fthread-14818-1-1.html%5Dhttp%3A%2F%2Fwww.cuplf.com%2Fthread-14818-1-1.html%5B%2Furl%5D%0D%0A++%0D%0A+++%5Burl%3Dhttp%3A%2F%2Fwww.xiaoxuexiao.com%2Fforum.php%3Fmod%3Dviewthread%26tid%3D1749007%26fromuid%3D127389%5Dhttp%3A%2F%2Fwww.xiaoxuexiao.com%2Fforum.php%3Fmod%3Dviewthread%26tid%3D1749007%26fromuid%3D127389%5B%2Furl%5D%0D%0A++%0D%0A+++%5Burl%3Dhttp%3A%2F%2Figirl.chinatimes.com%2Fforum.php%3Fmod%3Dviewthread%26tid%3D61649%26extra%3D%5Dhttp%3A%2F%2Figirl.chinatimes.com%2Fforum.php%3Fmod%3Dviewthread%26tid%3D61649%26extra%3D%5B%2Furl%5D%0D%0A++%0D%0A+++%5Burl%3Dhttp%3A%2F%2Fbbxyky.tk%2Fthread-154997-1-1.html%5Dhttp%3A%2F%2Fbbxyky.tk%2Fthread-154997-1-1.html%5B%2Furl%5D%0D%0A++%0D%0A+++%5Burl%3Dhttp%3A%2F%2Fmoshanghua.66rt.com%2Fviewthread.php%3Ftid%3D10070%26extra%3D%5Dhttp%3A%2F%2Fmoshanghua.66rt.com%2Fviewthread.php%3Ftid%3D10070%26extra%3D%5B%2Furl%5D%0D%0A++%0D%0A+++%5Burl%3Dhttp%3A%2F%2Fn.800tuan.com%2Fbbs%2Fforum.php%3Fmod%3Dviewthread%26tid%3D76418%5Dhttp%3A%2F%2Fn.800tuan.com%2Fbbs%2Fforum.php%3Fmod%3Dviewthread%26tid%3D76418%5B%2Furl%5D%0D%0A++%0D%0A+++%5Burl%3Dhttp%3A%2F%2Fbbs1.imotor.com%2Fviewthread.php%3Ftid%3D276%26extra%3D%5Dhttp%3A%2F%2Fbbs1.imotor.com%2Fviewthread.php%3Ftid%3D276%26extra%3D%5B%2Furl%5D%0D%0A++%0D%0A+++%5Burl%3Dhttp%3A%2F%2Fforums.webcrow.jp%2Fforum.php%3Fmod%3Dviewthread%26tid%3D2120301%5Dhttp%3A%2F%2Fforums.webcrow.jp%2Fforum.php%3Fmod%3Dviewthread%26tid%3D2120301%5B%2Furl%5D%0D%0A++%0D%0A+++%5Burl%3Dhttp%3A%2F%2Fsuofeiya520.com%2Fbbs%2Fforum.php%3Fmod%3Dviewthread%26tid%3D1010284%5Dhttp%3A%2F%2Fsuofeiya520.com%2Fbbs%2Fforum.php%3Fmod%3Dviewthread%26tid%3D1010284%5B%2Furl%5D&save=&formhash=f50c6624&sortid=1&uploadalbum=-2&allownoticeauthor=1&addfeed=1&usesig=1&newalbum=%C7%EB%CA%E4%C8%EB%CF%E0%B2%E1%C3%FB%B3%C6&posttime=1394591880";
		String[] r = s2.split(separators);
		for (int i = 0; i< r.length; i++) {
			if (tokenScore.containsKey(r[i]) && (Integer)tokenScore.get(r[i]) > 0) {
				System.out.println("[" + r[i] + "]");
			}
		}
		
		System.out.println(1 + (Integer)tokenScore.get("eval"));
		try {
			System.out.println(new String(Base64.getDecoder().decode("NzU3MjIyO0Bpbmlfc2V0KCJkaXNwbGF5X2Vycm9ycyIsIjAiKTtAc2V0X3RpbWVfbGltaXQoMCk7QHNldF9tYWdpY19xdW90ZXNfcnVudGltZSgwKTtlY2hvKCItPnwiKTs7ZWNobyBAZndyaXRlKGZvcGVuKGJhc2U2NF9kZWNvZGUoJF9QT1NUWyJ6MSJdKSwidyIpLGJhc2fjkdsalfjas8*&(*2NF9kZWNvZGUoJF9QT1NUWyJ6MiJdKSk/IjEiOiIwIjtlY2hvKCJ8PC0iKTs7ZGllKCk7")));
		} catch (Exception e) {
			System.out.println("error");
		}
		
		System.out.println("AbcDEF".toLowerCase());
		String ts = "";
		System.out.println(webShellScore(ts));
		
		String ss[] = new String[]{"abc", "123", "*(*()"};
		System.out.println(String.join("", ss));
		
		Iterator iter = tokenScore.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String)entry.getKey();
			int val = (Integer)entry.getValue();
			System.out.println(key + " " + val);
		}
		
		ts = "array_map(\"ass\".\"ert\",array(\"ev\".\"Al(\\\"\\\\\\$xx%3D\\\\\\\"Ba\".\"SE6\".\"4_dEc\"";
		System.out.println(scoreTokens(ts));
	}
}
