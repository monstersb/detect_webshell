package chaitin.webshell;

import com.aliyun.odps.data.Record;
import com.aliyun.odps.mapred.Mapper;

import java.io.IOException;

/**
 * Mapper模板。请用真实逻辑替换模板内容
 */
public class MyMapper implements Mapper {
	
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