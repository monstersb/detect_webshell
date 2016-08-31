package chaitin.phishing;

import java.util.LinkedList;
import java.util.List;

import chaitin.decoder.Pair;

public class ScoreTitle {
	static List<Pair<String, Double>> sensitive_word;
	
	static {
		sensitive_word = new LinkedList<Pair<String, Double>>();
		sensitive_word.add(new Pair<String, Double>("中国平安官网-中国平安保险（集团）股份有限公司-保险，银行，投资", 8.0));
		sensitive_word.add(new Pair<String, Double>("淘宝网 - 淘!我喜欢", 8.0));
		sensitive_word.add(new Pair<String, Double>("京东商城-京东商城官方网站!", 8.0));
		sensitive_word.add(new Pair<String, Double>("京东-欢迎登录", 8.0));
		sensitive_word.add(new Pair<String, Double>("京东(JD.COM)-综合网购首选-正品低价、品质保障、配送及时、轻松购物！", 8.0));
		sensitive_word.add(new Pair<String, Double>("中国移动官方网站", 8.0));
		sensitive_word.add(new Pair<String, Double>("中国联通网上营业厅", 8.0));
		sensitive_word.add(new Pair<String, Double>("苏宁易购(Suning) -综合网上购物商城,正品行货,全国联保,货到付款！", 8.0));
		sensitive_word.add(new Pair<String, Double>("用户登录 - 苏宁易购", 8.0));
		sensitive_word.add(new Pair<String, Double>("上天猫，就够了", 8.0));
		sensitive_word.add(new Pair<String, Double>("网上超市1号店，省力省钱省时间", 8.0));
		sensitive_word.add(new Pair<String, Double>("1号店登录", 8.0));
		sensitive_word.add(new Pair<String, Double>("亚马逊-网上购物商城：要网购, 就来Z.cn!", 8.0));
		sensitive_word.add(new Pair<String, Double>("Amazon &#30331;&#24405;", 8.0));
		sensitive_word.add(new Pair<String, Double>("唯品会（原Vipshop.com）特卖会：一家专门做特卖的网站_确保正品_确保低价_货到付款", 8.0));
		sensitive_word.add(new Pair<String, Double>("唯品会网站登录", 8.0));
		sensitive_word.add(new Pair<String, Double>("美丽说—白领的全球导购", 8.0));
		sensitive_word.add(new Pair<String, Double>("登录 － 美丽说", 8.0));
		sensitive_word.add(new Pair<String, Double>("易迅网-专业的电脑、数码家电、手机、汽车用品、鞋服百货网上数码大卖场 - 易迅网", 8.0));
		sensitive_word.add(new Pair<String, Double>("国美在线(GOME)-综合网购商城，正品低价、品质保障、快速送达、安心服务！", 8.0));
		sensitive_word.add(new Pair<String, Double>("用户登录-国美在线", 8.0));
		sensitive_word.add(new Pair<String, Double>("聚美优品 - 【极速免税店 品牌防伪码】正品化妆品团购网站BJ,千万用户推荐,拆封30天无条件退货!", 8.0));
		sensitive_word.add(new Pair<String, Double>("登录聚美", 8.0));
		sensitive_word.add(new Pair<String, Double>("华为商城官网-提供华为手机(", 8.0));
		sensitive_word.add(new Pair<String, Double>("欢迎访问中国建设银行网站", 8.0));
		sensitive_word.add(new Pair<String, Double>("中国建设银行 个人客户网上银行", 8.0));
		sensitive_word.add(new Pair<String, Double>("交通银行 - 交银金融网", 8.0));
		sensitive_word.add(new Pair<String, Double>("一网通主页 -- 招商银行官方网站", 8.0));
		sensitive_word.add(new Pair<String, Double>("中国银行全球门户网站", 8.0));
		sensitive_word.add(new Pair<String, Double>("中国工商银行中国网站", 8.0));
		sensitive_word.add(new Pair<String, Double>("中国农业银行", 8.0));
		sensitive_word.add(new Pair<String, Double>("首页 - 广发银行", 8.0));
		sensitive_word.add(new Pair<String, Double>("中国邮政储蓄银行", 8.0));
		sensitive_word.add(new Pair<String, Double>("登录 - 当当网", 8.0));
		sensitive_word.add(new Pair<String, Double>("当当—网上购物中心：图书、母婴、美妆、家居、数码、家电、服装、鞋包等，正品低价，货到付款", 8.0));
		sensitive_word.add(new Pair<String, Double>("126网易免费邮--你的专业电子邮局", 8.0));
		sensitive_word.add(new Pair<String, Double>("163网易免费邮--中文邮箱第一品牌", 8.0));
		sensitive_word.add(new Pair<String, Double>("企业邮箱领航者|163企业邮箱-网易企业邮箱-外贸企业邮箱-中文企业邮箱首选品牌解决方案", 8.0));
		sensitive_word.add(new Pair<String, Double>("登录QQ邮箱", 8.0));
		sensitive_word.add(new Pair<String, Double>("QQ空间-分享生活，留住感动", 8.0));
		sensitive_word.add(new Pair<String, Double>("微博-随时随地发现新鲜事", 8.0));
		sensitive_word.add(new Pair<String, Double>("登录 - 支付宝", 8.0));
		sensitive_word.add(new Pair<String, Double>("登录 | 美团网", 8.0));
		sensitive_word.add(new Pair<String, Double>("[官]欢迎访问全国信用在线申请中心", 15.0));
		sensitive_word.add(new Pair<String, Double>("登录", 2.0));
		sensitive_word.add(new Pair<String, Double>("登陆", 3.0));
		sensitive_word.add(new Pair<String, Double>("中奖", 2.0));
		sensitive_word.add(new Pair<String, Double>("抽奖", 2.0));
		sensitive_word.add(new Pair<String, Double>("信用", 2.0));
	}

	public static double score(String title) {
		double result = 0.0;
		for (Pair<String, Double> p: sensitive_word) {
			if (title.indexOf(p.first) != -1 && p.second > result) {
				result = p.second;
			}
		}
		return result;
	}

   	public static void main(String[] args) {
   		System.out.println(score("[官]欢迎访问全国信用在线申请中心"));
   		System.out.println(score(" [官]欢迎访问全国信用在线申请中心 "));
   	}
}
