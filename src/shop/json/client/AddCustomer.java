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

public class AddCustomer extends Activity{

	private Button bAddCustomerSubmit;
	private EditText etAddCustomerID;
	private EditText etAddCustomerZip;
	private EditText etAddCustomerDiscount;
	private final String DEBUG_TAG = "AddCustomer";
	
	
	JSONObject customerObj = new JSONObject();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_customer);
		
		
		
		etAddCustomerID = (EditText) findViewById(R.id.etAddCustomer_id);
		etAddCustomerZip = (EditText) findViewById(R.id.etAddCustomer_zip);
		etAddCustomerDiscount = (EditText) findViewById(R.id.etAddCustomer_discountCode);
		bAddCustomerSubmit();
		
	}

	private void bAddCustomerSubmit() {
		
		bAddCustomerSubmit = (Button) findViewById(R.id.bAddOrder_submit);
		bAddCustomerSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				// build the JSON object
				try {
					customerObj.put("customerId", etAddCustomerID.getText().toString());
					customerObj.put("zip", etAddCustomerZip.getText().toString());
					customerObj.put("discountCode", etAddCustomerDiscount.getText().toString());
				} catch (JSONException e1) {
					Log.e(DEBUG_TAG, "couldnt build JSON object");
					e1.printStackTrace();
				}

				// call the http post request function
				try {
					String navratka = JSONfunctionPost.postJSONtoURL("http://10.0.2.2:8080/Eshop/resources/entities.customer", customerObj);
//					String navratka = makeRequest("http://10.0.2.2:8080/Eshop/resources/entities.purchaseorder");
					Log.i(DEBUG_TAG, navratka);
				} catch (Exception e) {
					Log.e(DEBUG_TAG, "couldnt create http post request");
					e.printStackTrace();
				}
					
			}
			
			
		});
		
	}
	
	
}
