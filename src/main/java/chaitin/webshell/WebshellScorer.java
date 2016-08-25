package chaitin.webshell;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.util.Iterator;

import org.apache.commons.codec.binary.Hex;

import at.ac.tuwien.infosys.www.phpparser.ParseNode;
import at.ac.tuwien.infosys.www.phpparser.ParseTree;
import at.ac.tuwien.infosys.www.phpparser.PhpLexer;
import at.ac.tuwien.infosys.www.phpparser.PhpParser;


public class WebshellScorer {
	
	public static String preprocess(byte[] bs, int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			if ((int)bs[i] >= 20 || bs[i] == '\n' || bs[i] == '\r'  || bs[i] == '\t' ) {
				sb.append((char)bs[i]);
			}
		}
		return sb.toString();
	}
	
	public static double phpScore(String data) {
		
		data = data.trim();
		
		if (!(data.length() >= 5 && "<php?".equals(data.substring(0, 5)))) {
			data = "<?php\n" + data + "\n?>";
		}
		
		PhpLexer lexer = new PhpLexer(new StringReader(data));
		lexer.setFileName("webshell");
		PhpParser parser = new PhpParser(lexer);
		ParseNode rootNode = null;
		
		try {
			rootNode = (ParseNode) parser.parse().value;
		} catch (Exception e) {
			return 0;
		}
		
		int leave_cnt = 0;
		ParseTree pt = new ParseTree(rootNode);
		for (Iterator<ParseNode> iter = pt.leafIterator(); iter.hasNext(); ) {
			
			ParseNode leaf = (ParseNode) iter.next();
			
			leave_cnt ++;
			if (leave_cnt >= 3) {
				return 1.0;
			}
		}
		return 0.0;
	}

	public static void main(String[] args) {
		
	}

}
