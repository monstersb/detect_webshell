package chaitin.phishing;

import chaitin.phishing.HtmlParser.Form;
import chaitin.phishing.HtmlParser.Form.Input;

public class ScoreForm {

	public static double scoce_action(String action) {
		return 0.0;
	}
	
	public static double score_form(Form form) {
		double result = 0.0;
		for (Input input: form.input) {
			if (input.type == "password") {
				result += 3;
				break;
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
