package chaitin.phishing;

import java.io.IOException;

import com.aliyun.odps.data.Record;
import com.aliyun.odps.mapred.Mapper;
import com.aliyun.odps.mapred.Mapper.TaskContext;

public class PhishingMapper implements Mapper {
    public void setup(TaskContext context) throws IOException {
    }

    public void map(long recordNum, Record record, TaskContext context) throws IOException {
        String url = (String) record.get(0);
        String html = (String) record.get(1);
        if (true) {
        	Record result = context.createOutputRecord();
        	result.set("url", url);
            context.write(result);
        }
    }

    public void cleanup(TaskContext context) throws IOException {

    }
}
