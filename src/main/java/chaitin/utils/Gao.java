package chaitin.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.aliyun.odps.data.Record;
import com.aliyun.odps.mapred.Mapper;
import com.google.gson.Gson;

import chaitin.webshell.WebshellMapper;

public class Gao {
	public static String output_file_name = "/tmp/out.txt";
	public static void map_file(String input_file_name, Mapper mapper) throws IOException {
		//new FileWriter(output_file_name).close();
        BufferedReader br = new BufferedReader(new InputStreamReader(
        		new FileInputStream(input_file_name)));
        String line;
    	Gson gson = new Gson();
        while ((line = br.readLine()) != null) {
        	String[] item = gson.fromJson(line, String[].class);
        	ChaitinRecord record = new ChaitinRecord(item);
        	mapper.map(0, record, null);
        	//break;
        }
        br.close();
	}
	
	public static void dump(Object[] item) throws IOException {
    	Gson gson = new Gson();
    	String result = gson.toJson(item);
    	System.out.println(result);
		//FileWriter fw = new FileWriter(output_file_name, true);
		//fw.write(result + "\n");
		//fw.close();
	}

	public static void main(String[] args) throws Exception {
		Mapper mapper = new WebshellMapper();
		map_file("/Users/Monster/Documents/webshell.in.json", mapper);
	}

}
