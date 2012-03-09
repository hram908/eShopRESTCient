package shop.json.client;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.sax.TextElementListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class Login extends Activity {
	
    final static public String PREFS_NAME = "settings";
	public static final String DEBUG_TAG = "Login";
	static private String userID;
	private TextView tvLoginInfo;
	private EditText etUserID;
	private Button bLogin, bLaunchAdminMenu, bLaunchCustomerMenu;

	
	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
        Toast.makeText(this, "value stored in prefs: " + userID, Toast.LENGTH_SHORT).show();

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
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		userID = getValueFromPrefs(PREFS_NAME, "USER_ID");
		
    	inittvLoginInfo();
        
        initetUserID();
        
        initbLogin();
        
        initbLaunchAdminMenu();
        
        initbLaunchCustomerMenu();
		
        Toast.makeText(this, "value stored in prefs: " + userID, Toast.LENGTH_SHORT).show();
	}

	private void initbLaunchCustomerMenu() {
		bLaunchCustomerMenu = (Button) findViewById(R.id.bLaunchCustomerMenu);
		bLaunchCustomerMenu.setOnClickListener(new OnClickListener() {
        	
			@Override
			public void onClick(View v) {
				Intent goToMainMenuAdmin = new Intent(
						"shop.json.client.MAINMENUCUSTOMER");
				startActivity(goToMainMenuAdmin);	

			}
		});
	}

	private void initbLaunchAdminMenu() {
		bLaunchAdminMenu = (Button) findViewById(R.id.bLaunchAdminMenu);
		bLaunchAdminMenu.setOnClickListener(new OnClickListener() {
        	
			@Override
			public void onClick(View v) {
				Intent goToMainMenuAdmin = new Intent(
						"shop.json.client.MAINMENUADMIN");
				startActivity(goToMainMenuAdmin);	

			}
		});
	}

	private void initbLogin() {
		bLogin = (Button) findViewById(R.id.bLogin);
		bLogin.setOnClickListener(new OnClickListener() {
        	
			@Override
			public void onClick(View v) {
				
				userID = etUserID.getText().toString().trim();
				
				storeValueToPrefs(userID, "USER_ID", PREFS_NAME);
				
				if (getValueFromPrefs(PREFS_NAME, "USER_ID") == "admin"){
					Intent goToMainMenuAdmin = new Intent(
							"shop.json.client.MAINMENUADMIN");
					startActivity(goToMainMenuAdmin);
				} 
				
				else {
				
				Intent goToMainMenuCustomer = new Intent(
						"shop.json.client.MAINMENUCUSTOMER");
				startActivity(goToMainMenuCustomer);
				}
			}
		});
		
	}

	private void initetUserID() {
		etUserID = (EditText) findViewById(R.id.etUserID);
		
	}

	private void inittvLoginInfo() {
		tvLoginInfo = (TextView) findViewById(R.id.tvLoginInfo);
	
	}

}
