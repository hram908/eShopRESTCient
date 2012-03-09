package shop.json.client;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainMenuAdmin extends Activity {
	
    final static public String PREFS_NAME = "settings";
	public static final String DEBUG_TAG = "MainMenu";
	static private String userID;
	private Button bListCustomers;
	private EditText etCustomersOrders;
	private Button bCustomersOrders;
	private Button bAddCustomer;
	private Button bAddProduct;
	private EditText etRemoveProduct;
	private Button bRemoveProduct;
	private EditText etRemoveCustomer;
	private Button bRemoveCustomer;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.main_menu_admin);
		
		userID = getValueFromPrefs(PREFS_NAME, "USER_ID");
		
			initAdminMenu();
			
	}

	private void initAdminMenu() {
		
		initbListCustomers();
		initetCustomersOrders();
		initbCustomersOrders();
		initbAddCustomer();
		initbAddProduct();
		initetRemoveProduct();
		initbRemoveProduct();
		initetRemoveCustomer();
		initbRemoveCustomer();
		
	}

	private void initbRemoveCustomer() {
		bRemoveCustomer = (Button) findViewById(R.id.bRemoveCustomer);
		bRemoveCustomer.setOnClickListener(new OnClickListener() {
        	
			@Override
			public void onClick(View v) {
				
				 String url = "http://10.0.2.2:8080/Eshop/resources/entities.customer/";
				 String id = etRemoveCustomer.getText().toString().trim();
				 String fullUrl = url+id;
				 JSONfunctionDelete.delIDfromURL(fullUrl);
				 Toast.makeText(getBaseContext(), "entry successfully removed", Toast.LENGTH_SHORT).show();
				 etRemoveCustomer.setText(null);

			}
		});
		

		
		
	}

	private void initetRemoveCustomer() {
		etRemoveCustomer = (EditText) findViewById(R.id.etRemoveCustomer);
	}

	private void initbRemoveProduct() {
		bRemoveProduct = (Button) findViewById(R.id.bRemoveProduct);
		bRemoveProduct.setOnClickListener(new OnClickListener() {
        	
			@Override
			public void onClick(View v) {
				
				 String url = "http://10.0.2.2:8080/Eshop/resources/entities.product/";
				 String id = etRemoveProduct.getText().toString().trim();
				 String fullUrl = url+id;
				 JSONfunctionDelete.delIDfromURL(fullUrl);
				 Toast.makeText(getBaseContext(), "entry successfully removed", Toast.LENGTH_SHORT).show();
				 etRemoveProduct.setText(null);

			}
		});
	}

	private void initetRemoveProduct() {
		etRemoveProduct = (EditText) findViewById(R.id.etRemoveProduct);
		etRemoveProduct.setText("Product #");
	}

	private void initbAddProduct() {
		bAddProduct = (Button) findViewById(R.id.bAddProduct);
	}

	private void initbAddCustomer() {
		bAddCustomer = (Button) findViewById(R.id.bAddCustomer);
	}

	private void initbCustomersOrders() {
		bCustomersOrders = (Button) findViewById(R.id.bCustomersOrders);
		//TODO implement
	}

	private void initetCustomersOrders() {
		etCustomersOrders = (EditText) findViewById(R.id.etCustomersOrders);
		etCustomersOrders.setText("Customer #");
		}

	private void initbListCustomers() {
		bListCustomers = (Button) findViewById(R.id.bListCustomers);
		bListCustomers.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent listCustomersIntent = new Intent(
						"shop.json.client.LISTCUSTOMERS");
				startActivity(listCustomersIntent);

			}
		});
		
	}

 
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

 
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
