package chaitin.phishing;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import com.aliyun.odps.data.Record;
import com.aliyun.odps.mapred.Mapper;

import chaitin.webshell.decoder.Pair;


public class PhishingMapper implements Mapper {
	
    public void setup(TaskContext context) throws IOException {
    }

    public void map(long recordNum, Record record, TaskContext context) throws IOException {
        String url = (String) record.get(0);
        String html = (String) record.get(1);
        if (PhishingDetector.is_phishing(url, html)) {
        	Record result = context.createOutputRecord();
        	result.set("url", url);
            context.write(result);
        }
    }

    public void cleanup(TaskContext context) throws IOException {

    }
    
}
