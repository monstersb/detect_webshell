package chaitin.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.aliyun.odps.data.Record;
import com.aliyun.odps.mapred.Mapper;
import com.google.gson.Gson;

import chaitin.webshell.WebshellDetector;
import chaitin.webshell.WebshellMapper;
import chaitin.webshell.parser.AspScore;
import chaitin.webshell.parser.PhpScore;

public class Gao {
	
	static String output_file_name = "/tmp/out.txt";
	static int count = 0;
	
	public static void map_file(String input_file_name, Mapper mapper) throws IOException {
		//*
		new FileWriter(output_file_name).close();
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
        //*/
	}
	
	public static void dump(Object[] item) throws IOException {
		//*
    	Gson gson = new Gson();
    	String result = gson.toJson(item);
    	System.out.println(result);
		FileWriter fw = new FileWriter(output_file_name, true);
		fw.write(result + "\n");
		fw.close();
		//System.exit(0);
		count += 1;
		//*/
	}

	public static void main(String[] args) throws Exception {
		WebshellMapper mapper = new WebshellMapper();
		
		map_file("/Users/Monster/Documents/webshell.in.json", mapper);
		System.out.println("count: " + count);
		System.out.println("precision: " + (mapper._tp/(mapper._tp + mapper._fp)));
		System.out.println("recall: " + (mapper._tp/(mapper._tp + mapper._fn)));
		
		boolean b = WebshellDetector.isWebshell("", "1\u003d%40eval%2F%2A%CE%D2%C8%A5%C4%E3%C2%EE%C1%CB%B8%F4%B1%DA%2A%2F%01%28%24%7B%27%5FP%27.%27OST%27%7D%5Bz9%5D%2F%2A%CE%D2%C8%A5%C4%E3%C2%EE%C1%CB%B8%F4%B1%DA%2A%2F%01%28%24%7B%27%5FPOS%27.%27T%27%7D%5Bz0%5D%29%29%3B\u0026z0\u003dNTk1NTQ2O0Bpbmlfc2V0KCJkaXNwbGF5X2Vycm9ycyIsIjA");
		System.out.println(b);
	}

}
