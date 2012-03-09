package shop.json.client;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddOrderCustomer extends Activity{

	private Button bAddOrderSubmit;
	private EditText etAddOrderCustomerID;
	private EditText etAddOrderProductID;
	private EditText etAddOrderNumber;
	private final String DEBUG_TAG = "AddOrder";
	
	JSONObject orderObj = new JSONObject();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_order_customer);
		
		etAddOrderNumber = (EditText) findViewById(R.id.etAddOrder_number);
		etAddOrderCustomerID = (EditText) findViewById(R.id.etAddOrder_customerid);
		etAddOrderProductID = (EditText) findViewById(R.id.etAddOrder_productid);
		initbAddOrderSubmit();
		
	}

	private void initbAddOrderSubmit() {
		
		bAddOrderSubmit = (Button) findViewById(R.id.bAddOrder_submit);
		bAddOrderSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				// build the JSON object
				try {
					orderObj.put("orderNum", etAddOrderNumber.getText().toString());
					orderObj.put("customerId", etAddOrderCustomerID.getText().toString());
					orderObj.put("productId", etAddOrderProductID.getText().toString());
				} catch (JSONException e1) {
					Log.e(DEBUG_TAG, "couldnt build JSON object");
					e1.printStackTrace();
				}

				// call the http post request function
				try {
					String navratka = JSONfunctionPost.postJSONtoURL("http://10.0.2.2:8080/Eshop/resources/entities.purchaseorder", orderObj);
//					String navratka = makeRequest("http://10.0.2.2:8080/Eshop/resources/entities.purchaseorder");
					Log.i(DEBUG_TAG, navratka);
				} catch (Exception e) {
					Log.e(DEBUG_TAG, "couldnt create http post request");
					e.printStackTrace();
				}
					
			}
			
			
//			
//			// http request function
//			private String makeRequest(String path) throws Exception {
//				
//				DefaultHttpClient httpclient = new DefaultHttpClient();
//				HttpPost httpost = new HttpPost(path.toString());	
//				
//				ResponseHandler responseHandler = new BasicResponseHandler();
//				StringEntity se = new StringEntity(orderObj.toString());
//				
//
//				
//				httpost.setEntity(se);
//				httpost.setHeader("Accept", "application/json");
//				httpost.setHeader("Content-type", "application/json");
//
//				String response = httpclient.execute(httpost, responseHandler);
//				return response.toString();
//			}
			
			
		});
		
	}
	
	
}
