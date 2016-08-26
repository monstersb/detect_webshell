package chaitin.phishing;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import chaitin.phishing.HtmlParser.Form;
import chaitin.phishing.HtmlParser.Form.Input;
import chaitin.webshell.decoder.Pair;

public class ScoreForm {

	static List<Pair<Pattern, Double>> sensitive_action;
	static List<Pair<Pattern, Double>> sensitive_form_word;

	static {
		sensitive_action = new LinkedList<Pair<Pattern, Double>>();
		sensitive_action.add(new Pair<Pattern, Double>(Pattern.compile(".*fuck.*"), 5.0));
		sensitive_action.add(new Pair<Pattern, Double>(Pattern.compile(".*diaoyu.*"), 5.0));
		sensitive_action.add(new Pair<Pattern, Double>(Pattern.compile(".*\\.asp.*"), 2.0));
		sensitive_form_word = new LinkedList<Pair<Pattern, Double>>();
		sensitive_form_word.add(new Pair<Pattern, Double>(Pattern.compile(".*身份证.*"), 4.0));
		sensitive_form_word.add(new Pair<Pattern, Double>(Pattern.compile(".*额度.*"), 4.0));
		sensitive_form_word.add(new Pair<Pattern, Double>(Pattern.compile(".*手机号.*"), 2.0));
	}
	
	public static double scoce_action(String action) {
		double result = 0.0;
		for (Pair<Pattern, Double> p: sensitive_action) {
			if (p.first.matcher(action).matches() && p.second > result) {
				result = p.second;
			}
		}
		return result;
	}
	
	public static double score_form(Form form) {
		double result = 0.0;
		for (Input input: form.input) {
			if (input.type == "password") {
				result += 3.0;
				break;
			}
		}
		for (Pair<Pattern, Double> p: sensitive_form_word) {
			if (p.first.matcher(form.text).matches() && p.second > result) {
				result = p.second;
			}
		}
		result += scoce_action(form.action);
		return result;
	}
	
	public static double score(Form[] forms) {
		double result = 0.0;
		for (Form form: forms) {
			double t = score_form(form);
			if (t > result) {
				result = t;
			}
		}
		return result;
	}
}
