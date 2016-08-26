package chaitin.phishing;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import com.aliyun.odps.data.Record;
import com.aliyun.odps.mapred.Mapper;

import chaitin.webshell.decoder.Pair;


public class PhishingMapper implements Mapper {
	
	private static Pattern malice_host;
	private static List<Pair<String, String[]>> malice_keyword;
	private static List<Pair<String, String[]>> malice_title;
	
	static {
		malice_host = Pattern.compile(".*(\\.|\\-)((07073\\.com)|(10010\\.com)|(10086\\.cn)|(10jqka\\.com\\.cn)|(11467\\.com)|(114so\\.cn)|(115\\.com)|(120ask\\.com)|(122\\.gov\\.cn)|(12306\\.cn)|(123cha\\.com)|(126\\.com)|(163\\.com)|(1688\\.com)|(16888\\.com)|(17173\\.com)|(178\\.com)|(17k\\.com)|(17ok\\.com)|(17track\\.net)|(18183\\.com)|(189\\.cn)|(1905\\.com)|(19lou\\.com)|(21cn\\.com)|(2345\\.com)|(23wx\\.com)|(258\\.com)|(25pp\\.com)|(2cto\\.com)|(2mnd56\\.com)|(3158\\.cn)|(360\\.cn)|(360\\.com)|(360doc\\.com)|(360kan\\.com)|(365jia\\.cn)|(36kr\\.com)|(39\\.net)|(3987\\.com)|(39yst\\.com)|(3dmgame\\.com)|(3dwwwgame\\.com)|(4399\\.com)|(51\\.la)|(51auto\\.com)|(51credit\\.com)|(51cto\\.com)|(51job\\.com)|(51sole\\.com)|(51yes\\.com)|(52pojie\\.cn)|(55haitao\\.com)|(56\\.com)|(58\\.com)|(58pic\\.com)|(5dcar\\.com)|(6pm\\.com)|(6vhao\\.com)|(7k7k\\.com)|(91jm\\.com)|(92lux\\.cn)|(95516\\.com)|(99114\\.com)|(a9vg\\.com)|(acfun\\.tv)|(admaimai\\.com)|(adobe\\.com)|(aiweibang\\.com)|(aizhan\\.com)|(alexa\\.cn)|(ali213\\.net)|(alibaba-inc\\.com)|(alibaba\\.com)|(alicdn\\.com)|(aliexpress\\.com)|(alimama\\.com)|(alipay\\.com)|(alitrip\\.com)|(amap\\.com)|(amazon\\.cn)|(amazon\\.co\\.jp)|(amazon\\.co\\.uk)|(amazon\\.com)|(amazon\\.de)|(amazon\\.fr)|(anjuke\\.com)|(appgame\\.com)|(apple\\.com)|(atpanel\\.com)|(autohome\\.com\\.cn)|(avmo\\.pw)|(babyschool\\.com\\.cn)|(babytree\\.com)|(baidu\\.com)|(baiducontent\\.com)|(baike\\.com)|(bankofchina\\.com)|(baomihua\\.com)|(battlenet\\.com\\.cn)|(beva\\.com)|(bigccq\\.cn)|(bilibili\\.com)|(bing\\.com)|(biquge\\.la)|(bitauto\\.com)|(bjrcb\\.com)|(blizzard\\.cn)|(blogspot\\.com)|(boc\\.cn)|(booking\\.com)|(btbbt\\.cc)|(btime\\.com)|(btso\\.pw)|(bttiantang\\.com)|(caijing\\.com\\.cn)|(caixin\\.com)|(cankaoxiaoxi\\.com)|(ccb\\.com)|(ccb\\.com\\.cn)|(ccidnet\\.com)|(cctv\\.com)|(cdstm\\.cn)|(ceconline\\.com)|(cgbchina\\.com\\.cn)|(ch\\.com)|(chekb\\.com)|(chengdu\\.cn)|(china\\.cn)|(china\\.com)|(china\\.com\\.cn)|(chinadaily\\.com\\.cn)|(chinanews\\.com)|(chinaso\\.com)|(chinaunix\\.net)|(chinaz\\.com)|(chiphell\\.com)|(chooseauto\\.com\\.cn)|(chouti\\.com)|(chsi\\.com\\.cn)|(ci123\\.com)|(cisco\\.com)|(cjn\\.cn)|(cmbc\\.com\\.cn)|(cmbchina\\.com)|(cn163\\.net)|(cnbeta\\.com)|(cnblogs\\.com)|(cncn\\.org\\.cn)|(cnhubei\\.com)|(cnki\\.net)|(cnmo\\.com)|(cnnic\\.cn)|(cnr\\.cn)|(cntv\\.cn)|(cnzz\\.com)|(cpta\\.com\\.cn)|(cqnews\\.net)|(cr173\\.com)|(cri\\.cn)|(cs\\.com\\.cn)|(csair\\.com)|(csdn\\.net)|(ctfile\\.com)|(ctrip\\.com)|(cyol\\.com)|(cyzone\\.cn)|(dahe\\.cn)|(dangdang\\.com)|(dbw\\.cn)|(dell\\.com)|(dgtle\\.com)|(dianping\\.com)|(dilidili\\.com)|(dix3\\.com)|(dmzj\\.com)|(doc88\\.com)|(docin\\.com)|(dotamax\\.com)|(douban\\.com)|(doubleclick\\.net)|(douyu\\.com)|(dragonparking\\.com)|(duba\\.com)|(duomai\\.com)|(duowan\\.com)|(dwnews\\.com)|(dxy\\.cn)|(dy2018\\.com)|(dygang\\.com)|(dytt8\\.net)|(ea3w\\.com)|(eastday\\.com)|(eastmoney\\.com)|(ebay\\.com)|(ebrun\\.com)|(ecitic\\.com)|(ed2000\\.com)|(eelly\\.com)|(egou\\.com)|(ems\\.com\\.cn)|(enet\\.com\\.cn)|(engadget\\.com)|(eol\\.cn)|(epwk\\.com)|(eqxiu\\.com)|(etao\\.com)|(facebook\\.com)|(familydoctor\\.com\\.cn)|(fang\\.com)|(fanli\\.com)|(feng\\.com)|(fengniao\\.com)|(firefoxchina\\.cn)|(flyertea\\.com)|(fobshanghai\\.com)|(focus\\.cn)|(force\\.com)|(ftchinese\\.com)|(fudan\\.edu\\.cn)|(fuliba\\.net)|(g-fox\\.cn)|(g4d7\\.com)|(gamersky\\.com)|(ganji\\.com)|(gao7\\.com)|(gfan\\.com)|(gh0089\\.com)|(github\\.com)|(github\\.io)|(globaltimes\\.cn)|(gmw\\.cn)|(go\\.com)|(go108\\.com\\.cn)|(godaddy\\.com)|(goldcarpet\\.cn)|(gome\\.com\\.cn)|(gongchang\\.com)|(google\\.cn)|(google\\.co\\.jp)|(google\\.com)|(google\\.com\\.hk)|(google\\.com\\.sg)|(google\\.com\\.tw)|(gou\\.com)|(guancha\\.cn)|(gucheng\\.com)|(guokr\\.com)|(gusuwang\\.com)|(haitao\\.com)|(hao123\\.com)|(haosou\\.com)|(hc360\\.com)|(hexun\\.com)|(hjenglish\\.com)|(house365\\.com)|(huaban\\.com)|(huanqiu\\.com)|(huawei\\.com)|(huihui\\.cn)|(huim\\.com)|(hujiang\\.com)|(huomaotv\\.cn)|(hupu\\.com)|(huya\\.com)|(icbc\\.com\\.cn)|(iciba\\.com)|(icloud\\.com)|(icolor\\.com\\.cn)|(iconfont\\.cn)|(ifanr\\.com)|(ifeng\\.com)|(ih5\\.cn)|(iiyi\\.com)|(ikea\\.com)|(imdb\\.com)|(instagram\\.com)|(iqiyi\\.com)|(iteye\\.com)|(ithome\\.com)|(jandan\\.net)|(jb51\\.net)|(jd\\.com)|(jd\\.hk)|(jiameng\\.com)|(jianshu\\.com)|(jiayuan\\.com)|(jiemian\\.com)|(jiwu\\.com)|(jiyoujia\\.com)|(jjwxc\\.net)|(jmw\\.com\\.cn)|(joyme\\.com)|(jqw\\.com)|(jrj\\.com\\.cn)|(jumei\\.com)|(juooo\\.com)|(jxnews\\.com\\.cn)|(k618\\.cn)|(kafan\\.cn)|(kaixin001\\.com)|(kaola\\.com)|(kdnet\\.net)|(kdslife\\.com)|(ku6\\.com)|(kuaidi100\\.com)|(kugou\\.com)|(kuwo\\.cn)|(lagou\\.com)|(lashou\\.com)|(le\\.com)|(lenovo\\.com\\.cn)|(lesports\\.com)|(lianjia\\.com)|(liansuo\\.com)|(liepin\\.com)|(linkedin\\.com)|(linktech\\.cn)|(live\\.com)|(lofter\\.com)|(loldytt\\.com)|(longzhu\\.com)|(mafengwo\\.cn)|(makepolo\\.com)|(mama\\.cn)|(meishichina\\.com)|(meituan\\.com)|(meizu\\.com)|(mgtv\\.com)|(mi\\.com)|(miaopai\\.com)|(microsoft\\.com)|(microsoftonline\\.com)|(miercn\\.com)|(miui\\.com)|(mozilla\\.org)|(mp4ba\\.com)|(msn\\.com)|(mtime\\.com)|(muchong\\.com)|(mydigit\\.cn)|(mydrivers\\.com)|(netcoc\\.com)|(netease\\.com)|(newsmth\\.net)|(nga\\.cn)|(ngacn\\.cc)|(nih\\.gov)|(nipic\\.com)|(niuche\\.com)|(nuomi\\.com)|(oa\\.com)|(oeeee\\.com)|(office\\.com)|(onlinedown\\.net)|(ooopic\\.com)|(oracle\\.com)|(oschina\\.net)|(p5w\\.net)|(panda\\.tv)|(paypal\\.com)|(pc6\\.com)|(pcauto\\.com\\.cn)|(pcbaby\\.com\\.cn)|(pcbeta\\.com)|(pcgames\\.com\\.cn)|(pchome\\.net)|(pconline\\.com\\.cn)|(people\\.com\\.cn)|(pingan\\.com)|(pinterest\\.com)|(pixiv\\.net)|(pps\\.tv)|(pptv\\.com)|(qianzhan\\.com)|(qidian\\.com)|(qingdaonews\\.com)|(qinqinbaby\\.com)|(qq\\.com)|(quanjing\\.com)|(quanmin\\.tv)|(qunar\\.com)|(quora\\.com)|(qyer\\.com)|(reddit\\.com)|(rednet\\.cn)|(renren\\.com)|(runoob\\.com)|(saic\\.gov\\.cn)|(salesforce\\.com)|(sanguosha\\.com)|(saraba1st\\.com)|(scol\\.com\\.cn)|(sdo\\.com)|(segmentfault\\.com)|(sf-express\\.com)|(sina\\.cn)|(sina\\.com)|(sina\\.com\\.cn)|(sinaimg\\.cn)|(skycn\\.com)|(smzdm\\.com)|(so\\.com)|(sobaidupan\\.com)|(sogou\\.com)|(sohu\\.com)|(soku\\.com)|(sonhoo\\.com)|(soso\\.com)|(sourceforge\\.net)|(southcn\\.com)|(spdb\\.com\\.cn)|(sq\\.cn)|(stackexchange\\.com)|(stackoverflow\\.com)|(steamcommunity\\.com)|(steampowered\\.com)|(stockstar\\.com)|(suning\\.com)|(sznews\\.com)|(t\\.co)|(t66y\\.com)|(takungpao\\.com)|(taobao\\.com)|(taoche\\.com)|(techweb\\.com\\.cn)|(tgbus\\.com)|(tgfcer\\.com)|(thepaper\\.cn)|(tianya\\.cn)|(tibet\\.cn)|(tmall\\.com)|(tmall\\.hk)|(to8to\\.com)|(toutiao\\.com)|(tower\\.im)|(ttmeiju\\.com)|(tudou\\.com)|(tuicool\\.com)|(tumblr\\.com)|(tuniu\\.com)|(twitter\\.com)|(u17\\.com)|(uc\\.cn)|(umeng\\.com)|(uuu9\\.com)|(v1\\.cn)|(v2ex\\.com)|(vancl\\.com)|(verycd\\.com)|(viidii\\.info)|(vip\\.com)|(vmall\\.com)|(voc\\.com\\.cn)|(w3school\\.com\\.cn)|(wanfangdata\\.com\\.cn)|(wangtu\\.com)|(weather\\.com\\.cn)|(weibo\\.cn)|(weibo\\.com)|(weixin\\.com)|(weiyun\\.com)|(wenkang\\.cn)|(wikipedia\\.org)|(winshang\\.com)|(wish\\.com)|(wordpress\\.com)|(workercn\\.cn)|(worktile\\.com)|(wtoip\\.com)|(wtoutiao\\.com)|(xcar\\.com\\.cn)|(xdf\\.cn)|(xiami\\.com)|(xiamp4\\.com)|(xiaomi\\.cn)|(xiaomi\\.com)|(ximalaya\\.com)|(xinhuanet\\.com)|(xinjunshi\\.com)|(xitek\\.com)|(xiu\\.com)|(xjtour\\.com)|(xueqiu\\.com)|(xunlei\\.com)|(xvideos\\.com)|(xywy\\.com)|(yahoo\\.co\\.jp)|(yahoo\\.com)|(yaolan\\.com)|(ycwb\\.com)|(yesky\\.com)|(yhd\\.com)|(yinxiang\\.com)|(yinyuetai\\.com)|(yiqifa\\.com)|(yixun\\.com)|(yjbys\\.com)|(ynepb\\.gov\\.cn)|(ynet\\.com)|(youboy\\.com)|(youdao\\.com)|(yougou\\.com)|(youku\\.com)|(youth\\.cn)|(youtube\\.com)|(yunpan\\.cn)|(yxdown\\.com)|(yy\\.com)|(zaobao\\.com)|(zbj\\.com)|(zcool\\.com\\.cn)|(zealer\\.com)|(zhanqi\\.tv)|(zhaopin\\.com)|(zhibo8\\.cc)|(zhihu\\.com)|(zimuzu\\.tv)|(zjol\\.com\\.cn)|(znds\\.com)|(zol\\.com\\.cn)|(zuanke8\\.com)|(zybang\\.com))(\\.|\\-)(?!cn).*", Pattern.CASE_INSENSITIVE);
		malice_keyword = new LinkedList<Pair<String, String[]>>();
		malice_keyword.add(new Pair<String, String[]>("欢迎光临京东商城", new String[]{"jd.com", "360buy.com"}));
		malice_title = new LinkedList<Pair<String, String[]>>();
		malice_title.add(new Pair<String, String[]>("中国移动官方网站", new String[]{"10086.cn"}));
		malice_title.add(new Pair<String, String[]>("中国联通网上营业厅", new String[]{"10010.com"}));
		malice_title.add(new Pair<String, String[]>("京东(JD.COM)-", new String[]{"jd.com", "360buy.com"}));
		malice_title.add(new Pair<String, String[]>("淘宝网 - ", new String[]{"alibaba.com", "alipay.com", "tmall.com", "taobao.com"}));
		malice_title.add(new Pair<String, String[]>("登录 - 支付宝", new String[]{"alibaba.com", "alipay.com", "tmall.com", "taobao.com"}));
		//malice_title.add(new Pair<String, String[]>("天猫", new String[]{"alibaba.com", "alipay.com", "tmall.com", "taobao.com"}));
		malice_title.add(new Pair<String, String[]>("QQ空间-", new String[]{"qq.com"}));
		malice_title.add(new Pair<String, String[]>("中国平安官网", new String[]{"pingan.com"}));
		malice_title.add(new Pair<String, String[]>("浦发银行", new String[]{"spdb.com.cn"}));
		malice_title.add(new Pair<String, String[]>("中国工商银行", new String[]{"icbc.com.cn"}));
		malice_title.add(new Pair<String, String[]>("广发银行银行", new String[]{"cgbchina.com.cn"}));
		malice_title.add(new Pair<String, String[]>("中信银行", new String[]{"ecitic.com"}));
		malice_title.add(new Pair<String, String[]>("民生银行", new String[]{"cmbc.com.cn"}));
		malice_title.add(new Pair<String, String[]>("建设银行", new String[]{"ccb.com"}));
		malice_title.add(new Pair<String, String[]>("中国银行", new String[]{"bankofchina.com"}));
		malice_title.add(new Pair<String, String[]>("-综合运营商网上营业厅-官方认证、正品低价、品质保障、新品首发、放心购物、轻松服务", new String[]{"189.cn"}));
		malice_title.add(new Pair<String, String[]>("优购时尚商城-", new String[]{"yougou.com"}));
		malice_title.add(new Pair<String, String[]>("易迅网-", new String[]{"yixun.com"}));
		malice_title.add(new Pair<String, String[]>("国美在线(GOME)-", new String[]{"gome.com.cn"}));
		malice_title.add(new Pair<String, String[]>("【58同城 58.com】", new String[]{"58.com"}));
		malice_title.add(new Pair<String, String[]>("苏宁易购(Suning) -", new String[]{"suning.com"}));
		malice_title.add(new Pair<String, String[]>("【拉手网】", new String[]{"lashou.com"}));
		malice_title.add(new Pair<String, String[]>("当当—网上购物中心", new String[]{"dangdang.com"}));
		malice_title.add(new Pair<String, String[]>("聚美优品 -", new String[]{"jumei.com"}));
		malice_title.add(new Pair<String, String[]>("微博-随时随地发现新鲜事", new String[]{"weibo.com"}));
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
			if (has_malice_keyword(url.getHost(), html)) {
				return true;
			}
			if (has_malice_title(url.getHost(), html)) {
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

    private static boolean has_malice_keyword(String host, String html) {
    	for (Pair<String, String[]> p: malice_keyword) {
    		if (html.indexOf(p.first) >= 0) {
    			Boolean b = true;
    			for (String h: p.second) {
    				if (host.endsWith(h)) {
    					b = false;
    				}
    			}
    			return b;
    		}
    	}
    	return false;
    }
    
    private static boolean has_malice_title(String host, String html) {
    	String title = get_title(html);
    	for (Pair<String, String[]> p: malice_title) {
    		if (title.indexOf(p.first) >= 0) {
    			Boolean b = true;
    			for (String h: p.second) {
    				if (host.endsWith(h)) {
    					b = false;
    				}
    			}
    			return b;
    		}
    	}
    	return false;
    }
    
    private static String get_title(String html) {
    	int s = html.indexOf("<title>");
    	if (s == -1) {
    		return "";
    	}
    	int e = html.indexOf("</title>", s);
    	if (e == -1) {
    		e = html.length() - 1;
    	}
    	return html.substring(s, e);
    }
    
   	public static void main(String[] args) {
   		System.out.println(is_malice_host("www.qq.com"));
   		System.out.println(is_malice_host("www.qq.com.cn"));
   		System.out.println(is_malice_host("www.qq.com-sp.cn"));
   		System.out.println(is_malice_host("www.qq.com.monster.com"));
   		System.out.println(is_malice_host("a.qq.com.monster.com"));
   		System.out.println(is_malice_host("b.QQ.com.monster.com"));
   		System.out.println(is_malice_host("b.iCbC.com.cn.aaaaa.com"));
   		System.out.println();
   		System.out.println(has_malice_keyword("www.jd.com", "<html>欢迎光临京东商城</html>"));
   		System.out.println(has_malice_keyword("www.jd.com.test.com", "<html>欢迎光临京东商城</html>"));
   		System.out.println(has_malice_keyword("www.360buy.com", "<html>欢迎光临京东商城</html>"));
   		System.out.println(has_malice_keyword("www.baidu.com", "<html>欢迎光临京东商城</html>"));
   		System.out.println(has_malice_title("www.baidu.com", "<title>欢迎光临京东商城</title>"));
   	}
}
