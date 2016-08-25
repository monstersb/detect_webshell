package chaitin.webshell;

import java.io.IOException;
import java.util.List;

import com.aliyun.odps.data.Record;
import com.aliyun.odps.mapred.Mapper;
import com.aliyun.odps.mapred.TaskContext;

import chaitin.webshell.decoder.Pair;
import chaitin.webshell.decoder.QueryString;

public class WebshellMapper implements Mapper {

    public void setup(TaskContext context) throws IOException {
    }
    
    public static boolean isWebShell(String uri, String data) {
    	List<Pair<byte[], byte[]>> plist = QueryString.query_string(data.getBytes());
    	for (Pair<byte[], byte[]> p : plist) {
    		if ("z0".equals(new String(p.first))) {
    			return true;
    		}
    	}
		return false;
    }

    public void map(long recordNum, Record record, TaskContext context) throws IOException {
        String id = (String) record.get(0);
        String uri = (String) record.get(1);
        String data = (String) record.get(2);
        
        if (isWebShell(uri, data)) {
        	Record result = context.createOutputRecord();
        	result.set("id", id);
            context.write(result);
        }
    }

    public void cleanup(TaskContext context) throws IOException {

    }
    
	public static void main(String[] args) {
		System.out.println(isWebShell("", "z0=123"));
		System.out.println(isWebShell("", "z1=123"));
		System.out.println(isWebShell("", "z00000"));
	}
}
