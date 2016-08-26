package chaitin.phishing;

import java.net.MalformedURLException;
import java.net.URL;

public class PhishingDetector {

	URL url;
	HtmlParser html;
	
	PhishingDetector (String url, String html) {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			try {
				this.url = new URL("http://chaitin-monster.com/");
			} catch (MalformedURLException e1) {
			}
		}
		this.html = new HtmlParser(html);
	}
	
	static double threshold = 10.0;
	
	double detect() {
		double score = 0.0;
		score += ScoreDomain.score(url.getHost());
		if (score == 0.0) {
			return score;
		}
		score += ScoreTitle.score(html.title);
		return score;
	}
	
	public static Boolean is_phishing(String url, String html) {
		return new PhishingDetector(url, html).detect() >= threshold;
	}
}
