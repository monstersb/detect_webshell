package chaitin.webshell;
import java.io.IOException;
import com.aliyun.odps.data.Record;
import com.aliyun.odps.mapred.Mapper;

import chaitin.utils.Gao;

public class WebshellMapper implements Mapper {

    public void setup(TaskContext context) throws IOException {
    	
    }
    

	public void map(long recordNum, Record record, TaskContext context) throws IOException {
		String id = (String) record.get(0);
		String uri = (String) record.get(1);
		String data = (String) record.get(2);
		Boolean result = WebshellDetector.isWebshell(uri, data);
		if (context != null) {
			if (result) {
				Record result_record = context.createOutputRecord();
				result_record.set("id", id);
				context.write(result_record);
			}
		} else {
			if (!result.toString().equals(record.get(3))) {
				Gao.dump(record.toArray());
			}
		}
	}

	public void cleanup(TaskContext context) throws IOException {

	}


	public static void main(String[] args) throws Exception {

	}
}
