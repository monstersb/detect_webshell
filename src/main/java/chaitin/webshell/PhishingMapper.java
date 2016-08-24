package chaitin.webshell;

import java.io.IOException;

import com.aliyun.odps.data.Record;
import com.aliyun.odps.mapred.Mapper.TaskContext;

public class PhishingMapper {
    public void setup(TaskContext context) throws IOException {
    }

    public void map(long recordNum, Record record, TaskContext context) throws IOException {
        String id = (String) record.get(0);
        String uri = (String) record.get(1);
        String data = (String) record.get(2);
        if (true) {
        	Record result = context.createOutputRecord();
        	result.set("id", id);
            context.write(result);
        }
    }

    public void cleanup(TaskContext context) throws IOException {

    }
}
