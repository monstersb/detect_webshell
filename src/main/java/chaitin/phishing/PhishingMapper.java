package chaitin.phishing;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

import com.aliyun.odps.data.Record;
import com.aliyun.odps.mapred.Mapper;


public class PhishingMapper implements Mapper {
	
	private static Pattern malice_host;
	
	static {
		malice_host = Pattern.compile(".*\\.((qq\\.com)|(pingan\\.com)|(spdb\\.com\\.cn)|(icbc\\.com\\.cn)|(cgbchina\\.com\\.cn)|(ecitic\\.com)|(cmbc\\.com\\.cn)|(ccb\\.com)|(bankofchina\\.com)|(bjrcb\\.com)|(cmbchina\\.com)|(189\\.cn)|(10010\\.com)|(yougou\\.com)|(yixun\\.com)|(gome\\.com\\.cn)|(weixin\\.com)|(haitao\\.com)|(gou\\.com)|(58\\.com)|(suning\\.com)|(lashou\\.com)|(dangdang\\.com)|(jumei\\.com)|(amazon\\.cn)|(tmall\\.com)|(taobao\\.com)|(baidu\\.com)|(vip\\.com)|(yhd\\.com)|(jd\\.com)|(10086\\.cn))\\..*", Pattern.CASE_INSENSITIVE);
	}
	
    public void setup(TaskContext context) throws IOException {
    }

    public void map(long recordNum, Record record, TaskContext context) throws IOException {
        String url = (String) record.get(0);
        String html = (String) record.get(1);
        if (is_phishing(url, html)) {
        	Record result = context.createOutputRecord();
        	result.set("url", url);
            context.write(result);
        }
    }

    public void cleanup(TaskContext context) throws IOException {

    }
    
    private static boolean is_phishing(String surl, String html) {
		try {
			URL url = new URL(surl);
	    	if (is_malice_host(url.getHost())) {
	    		return true;
	    	}
		} catch (MalformedURLException e) {
			//e.printStackTrace();
		}
		return false;
    }
    
    private static boolean is_malice_host(String host) {
    	return malice_host.matcher(host).matches();
    }
    
    
   	public static void main(String[] args) {
   		System.out.println(is_malice_host("www.qq.com"));
   		System.out.println(is_malice_host("www.qq.com.monster.com"));
   		System.out.println(is_malice_host("a.qq.com.monster.com"));
   		System.out.println(is_malice_host("b.QQ.com.monster.com"));
   		System.out.println(is_malice_host("b.iCbC.com.cn.aaaaa.com"));
   	}
}
