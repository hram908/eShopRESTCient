
		
package shop.json.client;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class JSONfunctionDelete {
		
	public static  void delIDfromURL (String url) {
		
		DefaultHttpClient httpclient = new DefaultHttpClient();		
		
		HttpDelete httpDel = new HttpDelete(url.toString());
		
		try {
			httpclient.execute(httpDel);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}   
	
}