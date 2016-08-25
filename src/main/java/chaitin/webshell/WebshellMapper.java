package chaitin.webshell;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;

import com.aliyun.odps.data.Record;
import com.aliyun.odps.mapred.Mapper;
import com.aliyun.odps.mapred.TaskContext;

import at.ac.tuwien.infosys.www.phpparser.ParseNode;
import at.ac.tuwien.infosys.www.phpparser.ParseTree;
import at.ac.tuwien.infosys.www.phpparser.PhpLexer;
import at.ac.tuwien.infosys.www.phpparser.PhpParser;

import chaitin.webshell.decoder.Pair;
import chaitin.webshell.decoder.QueryString;
import chaitin.webshell.decoder.Unquote;
import java_cup.runtime.Symbol;

public class WebshellMapper implements Mapper {

    public void setup(TaskContext context) throws IOException {
    }
    

	public void map(long recordNum, Record record, TaskContext context) throws IOException {
		String id = (String) record.get(0);
		String uri = (String) record.get(1);
		String data = (String) record.get(2);

		if (WebshellDetector.isWebshell(uri, data)) {
			Record result = context.createOutputRecord();
			result.set("id", id);
			context.write(result);
		}
	}

	public void cleanup(TaskContext context) throws IOException {

	}

	public static void show(ParseNode pn) {

		if (pn.getNumChildren() == 0) {
			System.out.print(pn.getName() + " ");
		} else {
			for (ParseNode p : pn.getChildren()) {
				show(p);
			}
		}
	}

	public static void main(String[] args) throws Exception {

		File file = new File("/Users/araleii/src/ali/ali_webshell.data");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			while ((tempString = reader.readLine()) != null) {
				byte[] ds = Unquote.unquote(tempString.getBytes());
				System.out.println("line " + line + ": " + new String(ds));
				
				if (WebshellDetector.isWebshell(new String(ds), "")) {
					
				} else {
					// System.out.println("line " + line + ": " + new String(ds));
				}
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}
}
