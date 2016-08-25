package chaitin.webshell;

import java.io.ByteArrayInputStream;
import java.util.Iterator;

import at.ac.tuwien.infosys.www.phpparser.ParseNode;
import at.ac.tuwien.infosys.www.phpparser.ParseTree;
import at.ac.tuwien.infosys.www.phpparser.PhpLexer;
import at.ac.tuwien.infosys.www.phpparser.PhpParser;


public class WebshellScorer {
	
	public static double phpScore(String data) {
		
		data = data.trim();
		
		if (!(data.length() >= 5 && "<php?".equals(data.substring(0, 5)))) {
			data = "<php?\n" + data + "\n?>";
		}
		
		PhpLexer lexer = new PhpLexer(new ByteArrayInputStream(data.getBytes()));
		lexer.setFileName("webshell");
		PhpParser parser = new PhpParser(lexer);
		ParseNode rootNode = null;
		try {
			rootNode = (ParseNode) parser.parse().value;
		} catch (Exception e) {
			// e.printStackTrace();
			return 0;
		}
		ParseTree pt = new ParseTree(rootNode);
		// for (Iterator<ParseNode> iter = pt.leafIterator(); iter.hasNext(); ) {
		//	ParseNode leaf = (ParseNode) iter.next();
		//	System.out.print(leaf.getName() + " ");
		// }
		return 1.0;
	}

	public static void main(String[] args) {
		System.out.println(phpScore("$a = 1; $a = 1;"));
	}

}
