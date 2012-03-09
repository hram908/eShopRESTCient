package shop.json.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

public class JSONfunctionPost {

	public static String postJSONtoURL(String url, JSONObject content) throws Exception {
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url.toString());	
		
		ResponseHandler responseHandler = new BasicResponseHandler();
		StringEntity se = new StringEntity(content.toString());
		
		httpost.setEntity(se);
//		httpost.setHeader("Accept", "application/json");
		httpost.setHeader("Media-Type", "application/json");
		httpost.setHeader("Content-Type", "application/json");

		String response = httpclient.execute(httpost, responseHandler);
		return response.toString();
	}
	
}
