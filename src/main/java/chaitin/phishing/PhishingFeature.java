package chaitin.phishing;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

public class PhishingFeature {

	public static final int has_ip = 0;
	public static final int long_url = 1;
	public static final int short_service = 2;
	public static final int has_at = 3;
	public static final int double_slash_redirect = 4;
	public static final int pref_suf = 5;
	public static final int has_sub_domain = 6;
	public static final int ssl_state = 7;
	public static final int long_domain = 8;
	public static final int favicon = 9;
	public static final int port = 10;
	public static final int https_token = 11;
	public static final int req_url = 12;
	public static final int url_of_anchor = 13;
	public static final int tag_links = 14;
	public static final int SFH = 15;
	public static final int submit_to_email = 16;
	public static final int abnormal_url = 17;
	public static final int redirect = 18;
	public static final int mouseover = 19;
	public static final int right_click = 20;
	public static final int popup = 21;
	public static final int iframe = 22;
	public static final int domain_age = 23;
	public static final int dns_record = 24;
	public static final int traffic = 25;
	public static final int page_rank = 26;
	public static final int google_index = 27;
	public static final int links_to_page = 28;
	public static final int stats_report = 29;
	public static final int target = 30;

	public static int[] featureVector = new int[31];

	public static URL url;
	public static HtmlParser html;

	public static void init(String u, String h) {
		u = u.toLowerCase();
		h = h.toLowerCase();
		try {
			url = new URL(u);
		} catch (MalformedURLException e) {
			try {
				url = new URL("http://baidu.com/");
			} catch (MalformedURLException e1) {

			}
		}
		html = new HtmlParser(h);

		System.out.println(url.getHost());
		getFeatures();
	}

	public static boolean isIP(String ip) {
		// 0x and 0X
		ip = ip.toLowerCase();
		String[] as = ip.split("\\.");
		for (int i = 0; i < as.length; i++) {
			int base = 10;
			if (as[i].length() > 2 && as[i].indexOf("0x") == 0) {
				as[i] = as[i].substring(2);
				base = 16;
			}
			try {
				Long.parseLong(as[i], base);
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	// should be more precise ?
	public static int has_ip_feature() {
		String addr = url.getHost();
		if (isIP(addr)) {
			return 1;
		}
		return -1;
	}

	public static int long_url_feature() {
		int len = url.toString().length();
		if (len < 54) {
			return -1;
		} else if (54 <= len && len <= 75) {
			return 0;
		} else {
			return 1;
		}
	}

	// TODO: more precise
	public static int short_service_feature() {
		int len = url.toString().length();
		if (len <= 9) {
			return 1;
		}
		return -1;
	}

	public static int has_at_feature() {
		if (url.toString().indexOf('@') != -1) {
			return 1;
		}
		return -1;
	}

	public static int double_slash_redirect_feature() {
		if (url.toString().lastIndexOf("//") > 7) {
			return 1;
		}
		return -1;
	}

	public static int pref_suf_feature() {
		if (url.toString().indexOf("-") != -1) {
			return 1;
		}
		return -1;
	}

	public static int has_sub_domain_feature() {
		int cnt = 0;
		String u = url.toString();
		for (int i = 0; i < u.length(); i++) {
			if (u.charAt(i) == '.') {
				cnt++;
			}
		}
		if (cnt == 1) {
			return -1;
		} else if (cnt == 2) {
			return 0;
		} else {
			return 1;
		}
	}

	// Need to consider remain time
	public static int ssl_state_feature() {
		if (!"https".equals(url.getProtocol().toLowerCase())) {
			return 1;
		}
		return -1;
	}

	// Need to solve
	public static int long_domain_feature() {

		return -1;
	}

	public static int favicon_feature() {
		for (int i = 0; i < html.link.length; i++) {
			if (html.link[i].rel.indexOf("icon") != -1) {
				try {
					URL tu = new URL(html.link[i].href);
					return 1;
				} catch (MalformedURLException e) {
					
				}
			}
		}
		return 0;
	}

	// Need to solve
	public static int port_feature() {

		return -1;
	}

	public static int https_token_feature() {
		if (url.getHost().indexOf("https") != -1) {
			return 1;
		}
		return -1;
	}

	// This feature may be important
	// Need to solve
	public static int req_url_feature() {

		return 0;
	}

	public static int url_of_anchor_feature() {
		double anchor = 0;
		double tot = 0;
		for (int i = 0; i < html.a.length; i++) {
			if (html.a[i].href.indexOf('#') != -1 || html.a[i].href.indexOf("javascript:") != -1) {
				anchor += 1.0;
			}
			tot += 1.0;
		}
		double p = anchor / tot;
		if (p < 0.22) {
			return -1;
		} else if (p >= 0.22 && p <= 0.61) {
			return 0;
		} else {
			return 1;
		}
	}

	public static int tag_links_feature() {

		return 0;
	}

	public static int SFH_feature() {

		return 0;
	}

	public static int submit_to_email_feature() {

		return 0;
	}

	public static int abnormal_url_feature() {

		return 0;
	}

	public static int redirect_feature() {

		return 0;
	}

	public static int mouseover_feature() {

		return 0;
	}

	public static int right_click_feature() {

		return 0;
	}

	public static int popup_feature() {

		return 0;
	}

	public static int iframe_feature() {

		return 0;
	}

	public static int domain_age_feature() {

		return 0;
	}

	public static int dns_record_feature() {

		return 0;
	}

	public static int traffic_feature() {

		return 0;
	}

	public static int page_rank_feature() {

		return 0;
	}

	public static int google_index_feature() {

		return 0;
	}

	public static int links_to_page_feature() {

		return 0;
	}

	public static int stats_report_feature() {

		return 0;
	}

	public static int target_feature() {

		return 0;
	}

	public static boolean getFeatures() {

		featureVector[has_ip] = has_ip_feature();
		featureVector[long_url] = long_url_feature();
		featureVector[short_service] = short_service_feature();
		featureVector[has_at] = has_at_feature();
		featureVector[double_slash_redirect] = double_slash_redirect_feature();
		featureVector[pref_suf] = pref_suf_feature();
		featureVector[has_sub_domain] = has_sub_domain_feature();
		featureVector[ssl_state] = ssl_state_feature();
		featureVector[long_domain] = long_domain_feature();
		featureVector[favicon] = favicon_feature();
		featureVector[port] = port_feature();
		featureVector[https_token] = https_token_feature();
		featureVector[req_url] = req_url_feature();
		featureVector[url_of_anchor] = url_of_anchor_feature();
		featureVector[tag_links] = tag_links_feature();
		featureVector[SFH] = SFH_feature();
		featureVector[submit_to_email] = submit_to_email_feature();
		featureVector[abnormal_url] = abnormal_url_feature();
		featureVector[redirect] = redirect_feature();
		featureVector[mouseover] = mouseover_feature();
		featureVector[right_click] = right_click_feature();
		featureVector[popup] = popup_feature();
		featureVector[iframe] = iframe_feature();
		featureVector[domain_age] = domain_age_feature();
		featureVector[dns_record] = dns_record_feature();
		featureVector[traffic] = traffic_feature();
		featureVector[page_rank] = page_rank_feature();
		featureVector[google_index] = google_index_feature();
		featureVector[links_to_page] = links_to_page_feature();
		featureVector[stats_report] = stats_report_feature();
		featureVector[target] = target_feature();

		return true;
	}

	public static void show() {
		for (int i = 0; i < featureVector.length; i++) {
			System.out.print(featureVector[i]);
		}
	}

	public static void test() {
		boolean res = true;
		res &= (isIP("1.1.1.1"));
		res &= (isIP("0x58.0xCC.0xCA.0x62"));
		res &= (isIP("0x58.0xCA.0x62"));
		res &= (isIP("0x58CCCA62"));
		res &= (isIP("0x58CA62"));
		res &= (isIP("0x58CA6"));
		res &= (!isIP("baidu.com"));
		res &= (!isIP("xxx.123.abc"));

		if (res) {
			System.out.println("\nIs ip test succ");
		} else {
			System.out.println("\nIs ip test fail");
		}

		if (res) {
			System.out.println("\n==TEST PASSED==");
		} else {
			System.out.println("\n==TEST FAILED==");
		}

	}

	public static void main(String[] args) {

		for (int i = 0; i < featureVector.length; i++) {
			System.out.print(featureVector[i]);
		}
		System.out.print("");

		PhishingFeature.init("https://baidu.com",
				"<html><head><link rel=\"shortcut icon\" href=\"favicon.ico\" /> </head><link rel=\"icon\" href=\"animated_favicon.gif\" type=\"image/gif\" /> </html>");
		System.out.println(PhishingFeature.url.getProtocol());

		try {
			URL uu = new URL("test.t");
			System.out.println(uu.getProtocol());
		} catch (MalformedURLException e) {
			System.out.println("dododo");
		}

		test();
	}

}
