package chaitin.phishing;

import java.util.LinkedList;
import java.util.List;

import chaitin.webshell.decoder.Pair;

public class ScoreContent {
	static List<Pair<String, Double>> sensitive_word;
	
	static {
		sensitive_word = new LinkedList<Pair<String, Double>>();
		sensitive_word.add(new Pair<String, Double>("欢迎光临京东商城", 10.0));
	}
	
	public static double score (String text) {
		double result = 0.0;
		int times = 0;
		for (Pair<String, Double> p: sensitive_word) {
			if (text.indexOf(p.first) != -1) {
				result = p.second;
				times += 1;
			}
		}
		if (times > 1) {
			result = 0.0;
		}
		return result;
	}
}
