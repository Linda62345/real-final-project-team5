

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WordCounter {
	private String urlStr;
    private String content;
    
    public WordCounter(String urlStr){
    	this.urlStr = urlStr;
    }
    
    public String fetchContent() throws IOException{//private
    	String retVal = "";

		try {
			URL u = new URL(urlStr);

			HttpURLConnection conn = (HttpURLConnection) u.openConnection();

			conn.setRequestProperty("User-agent", "Chrome/96.0.4664.110");
			 if(conn.getResponseCode() != 403 || conn.getResponseCode() != 400 || conn.getResponseCode() != 404) {
			InputStream in = conn.getInputStream();
			InputStreamReader inReader = new InputStreamReader(in, "utf-8");

			BufferedReader bufReader = new BufferedReader(inReader);

			String line = null;
			while ((line = bufReader.readLine()) != null) {
				retVal += line;
			}} //全包

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return retVal;
		/*URL url = new URL(this.urlStr);
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
	
		String retVal = "";
	
		String line = null;
		
		//url找不到 retVal=null;
		
		while ((line = br.readLine()) != null){
		    retVal = retVal + line + "\n";
		}
	
		return retVal;*/
    }
    
    public int countKeyword(String keyword) throws IOException{
		if (content == null){
		    content = fetchContent();
		}
		
		//To do a case-insensitive search, we turn the whole content and keyword into upper-case:
		content = content.toUpperCase();
		keyword = keyword.toUpperCase();
	
		int retVal = 0;
		int fromIdx = 0;
		int found = -1;
	
		while ((found = content.indexOf(keyword, fromIdx)) != -1){
		    retVal++;
		    fromIdx = found + keyword.length();
		}
	
		return retVal;
    }
}
