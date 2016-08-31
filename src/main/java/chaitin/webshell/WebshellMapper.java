package chaitin.webshell;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.aliyun.odps.data.Record;
import com.aliyun.odps.mapred.Mapper;
import com.aliyun.odps.mapred.TaskContext;

import chaitin.decoder.Pair;
import chaitin.decoder.QueryString;
import chaitin.decoder.Unquote;

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


	public static void main(String[] args) throws Exception {

	}
}
