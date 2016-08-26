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
	
	static double threshold = 7.999;
	
	double detect() {
		double score = 0.0;
		if (ScoreDomain.is_white(url.getHost())) {
			return score;
		}
		score += ScoreDomain.score(url.getHost());
		score += ScoreTitle.score(html.title);
		score += ScoreContent.score(html.text);
		score += ScoreForm.score(html.form);
		return score;
	}
	
	public static Boolean is_phishing(String url, String html) {
		return new PhishingDetector(url, html).detect() >= threshold;
	}
	

   	public static void main(String[] args) {
   		System.out.println(is_phishing("http://www.baidu.com.a", "<title>[官]欢迎访问全国信用在线申请中心"));
   		System.out.println(is_phishing("", " [官]欢迎访问全国信用在线申请中心 "));
   	}
}
