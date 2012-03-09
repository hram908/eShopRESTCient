package shop.json.client;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import shop.json.client.JSONfunctionDelete;

public class MainMenuCustomer extends Activity {
	
    final static public String PREFS_NAME = "settings";
	public static final String DEBUG_TAG = "MainMenu";
	static private String userID;
	private Button bListProducts;
	private Button bListMyOrders;
	private EditText etRemoveMyOrder;
	private Button bRemoveMyOrder;
	private EditText etFindMyOrderID;
	private Button bFindMyOrderID;
	private Button bAddOrder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	    setContentView(R.layout.main_menu_customer);
		
		userID = getValueFromPrefs(PREFS_NAME, "USER_ID");
		
			initCustomerMenu();
	}

	private void initCustomerMenu() {
		
		initbListProducts();
		initbListMyOrders();		
		initetRemoveMyOrder();
		initbRemoveMyOrder();
		initetFindMyOrderID();
		initbFindMyOrderID();
		initbAddOrder();
		
	}

	private void initbAddOrder() {
		bAddOrder = (Button) findViewById(R.id.bAddOrder);
		
		bAddOrder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
		    	Intent intentAddOrder = new Intent("shop.json.client.ADDORDERCUSTOMER");
				startActivity(intentAddOrder);

			}
		});
		
	}

	private void initbFindMyOrderID() {
		bFindMyOrderID = (Button) findViewById(R.id.bFindMyOrderID);
	}

	private void initetFindMyOrderID() {
		etFindMyOrderID = (EditText) findViewById(R.id.etFindMyOrderID);
	}

	private void initbRemoveMyOrder() {
		bRemoveMyOrder = (Button) findViewById(R.id.bRemoveMyOrder);
		bRemoveMyOrder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				 String url = "http://10.0.2.2:8080/Eshop/resources/entities.purchaseorder/";
				 String id = etRemoveMyOrder.getText().toString().trim();
				 String fullUrl = url+id;
				 JSONfunctionDelete.delIDfromURL(fullUrl);
				 Toast.makeText(getBaseContext(), "entry successfully removed", Toast.LENGTH_SHORT).show();
				 etRemoveMyOrder.setText(null);
			}
		});
		
	}

	private void initetRemoveMyOrder() {
		etRemoveMyOrder = (EditText) findViewById(R.id.etRemoveMyOrder);
	}

	private void initbListMyOrders() {
		bListMyOrders = (Button) findViewById(R.id.bListMyOrders);
	}

	private void initbListProducts() {
		bListProducts = (Button) findViewById(R.id.bListProducts);
		bListProducts.setOnClickListener(new OnClickListener() {
        	        	
			@Override
			public void onClick(View v) {
					
				Intent goToMainMenuCustomer = new Intent(
						"shop.json.client.LISTPRODUCTS");
				startActivity(goToMainMenuCustomer);

			}
		});
		
		
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	protected void storeValueToPrefs(String value, String valueName,
			String prefsName) {
		SharedPreferences prefs = getSharedPreferences(prefsName, 0);
		Editor edit = prefs.edit();
		edit.putString(valueName, value);
		edit.commit();
		Log.i(valueName, "Value " + value + " stored in the " + valueName
				+ " prefs");
	}

	protected String getValueFromPrefs(String prefsName, String valueName) {
		SharedPreferences prefs = getSharedPreferences(prefsName, 0);
		String value = prefs.getString(valueName, null);
		if (value != null) {

			Log.i(valueName, "Value retrieved from prefs: " + value);
			return value;

		} else {
			Log.i(valueName, "Value " + valueName + " not found in the prefs");
			return null;
		}
	}

}
