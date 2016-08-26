package chaitin.phishing;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import chaitin.webshell.decoder.Pair;

public class ScoreContent {
	static List<Pair<Pattern, Double>> sensitive_word;
	static List<Pair<Pattern, Double>> sensitive_word_once;
	
	static {
		sensitive_word = new LinkedList<Pair<Pattern, Double>>();
		sensitive_word.add(new Pair<Pattern, Double>(Pattern.compile(".*欢迎光临京东商城.*"), 10.0));
		sensitive_word.add(new Pair<Pattern, Double>(Pattern.compile(".*欢迎登录(华为|苹果|QQ|qq|腾讯|腾讯qq|腾讯QQ|百度|京东|淘宝|支付宝|美团|微信|新浪微博)帐号.*"), 8.0));
		sensitive_word.add(new Pair<Pattern, Double>(Pattern.compile(".*已经发放.{0,20}奖金.*"), 10.0));
		sensitive_word.add(new Pair<Pattern, Double>(Pattern.compile(".*Forgot Apple ID or password.*"), 10.0));
		sensitive_word.add(new Pair<Pattern, Double>(Pattern.compile(".*没有Apple ID.{10}现在创建一个.*"), 10.0));
		sensitive_word.add(new Pair<Pattern, Double>(Pattern.compile(".*礼品.*"), 1.0));
		sensitive_word.add(new Pair<Pattern, Double>(Pattern.compile(".*奖金.*"), 1.0));
		sensitive_word.add(new Pair<Pattern, Double>(Pattern.compile(".*中奖.*"), 1.0));
		sensitive_word.add(new Pair<Pattern, Double>(Pattern.compile(".*抽奖.*"), 1.0));
		sensitive_word.add(new Pair<Pattern, Double>(Pattern.compile(".*赚钱.*"), 1.0));
		sensitive_word.add(new Pair<Pattern, Double>(Pattern.compile(".*额度.*"), 1.0));
		sensitive_word.add(new Pair<Pattern, Double>(Pattern.compile(".*免费.*"), 1.0));
		sensitive_word.add(new Pair<Pattern, Double>(Pattern.compile(".*发放.*"), 1.0));
		sensitive_word.add(new Pair<Pattern, Double>(Pattern.compile(".*下发.*"), 1.0));
		sensitive_word.add(new Pair<Pattern, Double>(Pattern.compile(".*登陆.*"), 1.0));
		sensitive_word.add(new Pair<Pattern, Double>(Pattern.compile(".*登录.*"), 1.0));
		sensitive_word_once = new LinkedList<Pair<Pattern, Double>>();
	}
	
	public static double score(String text) {
		double result = 0.0;
		for (Pair<Pattern, Double> p: sensitive_word) {
			if (p.first.matcher(text).matches()) {
				result += p.second;
			}
		}
		return result;
	}

   	public static void main(String[] args) {
   		System.out.println(score("已经发放 17332305 元奖金"));
   	}
}
