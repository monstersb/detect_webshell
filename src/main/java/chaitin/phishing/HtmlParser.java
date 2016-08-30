package chaitin.phishing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {
	public static class Form {
		public static class Input {
			public String type;
			public String name;
			public String id;
			public String placeholder;
		}
		public String action;
		public String text;
		public Input[] input;
	}
	
	public static class Link {
		public String rel;
		public String href;
	}
	
	public static class A {
		public String href;
	}
	
	String html;
	Document doc;
	public String text;
	public String title;
	public Form form[];
	public Link link[];
	public A a[];
	
	public HtmlParser (String _html) {
		html = _html;
		parse();
	}
	
	Boolean parse () {
		doc = Jsoup.parse(html);
		text = doc.text();
		Elements es = doc.getElementsByTag("title");
		if (es.size() > 0) {
			title = es.first().text();
		} else {
			title = "";
		}
		es = doc.getElementsByTag("form");
		form = new Form[es.size()];
		for (int i = 0; i < es.size(); ++i) {
			Element e = es.get(i);
			Form f = new Form();
			f.action = e.attr("action").toLowerCase();
			f.text = e.text();
			Elements es1 = e.getElementsByTag("input");
			f.input = new Form.Input[es1.size()];
			for (int j = 0; j < es1.size(); ++j) {
				Form.Input input = new Form.Input();
				Element e1 = es1.get(j);
				input.type = e1.attr("type").toLowerCase();
				input.name = e1.attr("name");
				input.id = e1.attr("id");
				input.placeholder = e1.attr("placeholder");
				f.input[j] = input;
			}
			form[i] = f;
		}
		
		es = doc.getElementsByTag("link");
		link = new Link[es.size()];
		for (int i = 0; i < es.size(); ++i) {
			link[i] = new Link();
			Element e = es.get(i);
			link[i].rel = e.attr("rel").toLowerCase();
			link[i].href = e.attr("href").toLowerCase();
		}
		
		es = doc.getElementsByTag("a");
		a = new A[es.size()];
		for (int i = 0; i < es.size(); ++i) {
			a[i] = new A();
			Element e = es.get(i);
			a[i].href = e.attr("href").toLowerCase();
		}
		
		return true;
	}

   	public static void main(String[] args) {
   		System.out.println("text: " + new HtmlParser("<title>testasd</title><body>body<h1>h1</h1><div>div</div></body>").text);
   		System.out.println(new HtmlParser("<title>test<img />asd</title>").title);
   		System.out.println(new HtmlParser("<title1>test<title>").title);
   		System.out.println(new HtmlParser("<TITLE>TEST哈哈哈</title><title>test2</title>").title);
   		System.out.println(new HtmlParser("<title>test1</title><title>test2</title>").form.length);
   		System.out.println(new HtmlParser("<form action=alert></form>").form.length);
   		System.out.println(new HtmlParser("<form action='http://阿萨德'></form>").form[0].action);
   		System.out.println("id: " + new HtmlParser("<form action='http://阿萨德'><input type=text /></form>").form[0].input[0].id);
   		System.out.println("type: " + new HtmlParser("<form action='http://阿萨德'><input type=text /></form>").form[0].input[0].type);
   		
   		HtmlParser h =  new HtmlParser("<html><head><link rel=\"shortcut icon\" /> </head><link rel=\"icon\" href=\"animated_favicon.gif\" type=\"image/gif\" /> </html>");
   		System.out.println(h.link.length);
   		System.out.println("href: " + h.link[0].href);
   		System.out.println(h.link[0].rel);
   		System.out.println(h.link[1].href);
   		System.out.println(h.link[1].rel);
   		
   		h =  new HtmlParser("<html><head><a href><a href=\"a\"><link rel=\"shortcut icon\" /><a href=\"b\"> </head><link rel=\"icon\" href=\"animated_favicon.gif\" type=\"image/gif\" /> </html>");
   		System.out.println(h.a.length);
   		System.out.println("href: " + h.a[0].href);
   		System.out.println("href: " + h.a[1].href);
   		System.out.println("href: " + h.a[2].href);
   		
   		return;
   	}
}
