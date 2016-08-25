package chaitin.utils;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import chaitin.webshell.WebshellDetector;
import net.sf.json.JSONObject;

public class MongoService {

	//static private String host = "localhost";
	static private String host = "mongo.patronus.in.chaitin.com";
	static private int port = 27017;
	private static MongoClient mc = new MongoClient(host, port);


	public static String testMongoData() {

		MongoDatabase db = mc.getDatabase("benchmark");
		MongoCollection<Document> col = db.getCollection("aqb_new_with_label");
		
		FindIterable<Document> findIterable = col.find(new BasicDBObject());
		MongoCursor<Document> mongoCursor = findIterable.iterator();
		
		int cnt = 0;
		
		int labeled = 0;
		int detected = 0;
		int hit = 0;
		
		while (mongoCursor.hasNext()) {
			JSONObject jo = JSONObject.fromObject(mongoCursor.next().toJson());
			String urlpath = jo.getString("urlpath");
			JSONObject bjo = (JSONObject) jo.get("body");
			String body = bjo.getString("$binary");
			JSONObject ljo = (JSONObject) jo.get("label");
			long mongo_label = (ljo.getLong("$numberLong")) & (1 << 8);
			
			if (mongo_label > 0) {
				labeled++;
			}
			
			long d_res = 0;
			if (WebshellDetector.isWebshell(urlpath, body)) {
				d_res = (1 << 8);
				detected++;
			}
			
			if (d_res > 0 && mongo_label == d_res) {
				hit++;
			}
			
			double precise = 100.0 * (double) hit / (double) detected;
			double recall = 100.0 * (double) hit / (double) labeled;
			
			cnt ++;
			if (cnt > 2) {
				break;
			}
			if (cnt % 10000 == 0) {
				System.out.println("Detected: " + detected + "   labeled: " + labeled + "   hit: " + hit + "   Precise: " + precise + "   Recall:  " + recall);
			}
		}
		return null;
	}

	public static void main(String[] args) {
		testMongoData();
	}
}
