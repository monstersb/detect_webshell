package chaitin.decoder;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class ParseUrl extends Decoder {
	public static class Url {
		public byte [] protocol;
		public byte [] host;
		public int port;
		public byte [] path;
		public byte [] query;
	}
	
    @Override
    List<byte[]> decode(byte[] input) {
        List<byte[]> result = new LinkedList<byte[]>();
        return result;
    }
    
    public static Url parse_url(byte[] input) {
    	Url url = new Url();
    	try {
			URL _url = new URL(new String(input));
			url.protocol = _url.getProtocol().getBytes();
			url.host = _url.getHost().getBytes();
			url.port = _url.getPort();
			url.path = _url.getPath().getBytes();
			url.query = _url.getQuery().getBytes();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    	return url;
    }
}
