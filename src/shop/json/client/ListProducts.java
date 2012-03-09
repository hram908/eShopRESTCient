package shop.json.client;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ListProducts extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listplaceholder);
        
        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
 
        JSONObject json = JSONfunctionGet.getJSONfromURL("http://10.0.2.2:8080/Eshop/resources/entities.product/"); 
                
        try{
        	
        	JSONArray  earthquakes = json.getJSONArray("product");
        	
	        for(int i=0;i<earthquakes.length();i++){						
				HashMap<String, String> map = new HashMap<String, String>();	
				JSONObject e = earthquakes.getJSONObject(i);
				
				map.put("id",  String.valueOf(i));
	        	map.put("description", "Description: \n" + e.getString("description"));
	        	map.put("purchaseCost", "Price: " +  e.getString("purchaseCost"));
	        	mylist.add(map);			
			}		
        }
        
        catch(JSONException e)        {
        	 Log.e("log_tag", "Error parsing data "+e.toString());
        }
        
        ListAdapter adapter = new SimpleAdapter(this, mylist , R.layout.main, 
                        new String[] { "description", "purchaseCost" }, 
                        new int[] { R.id.item_title, R.id.item_subtitle });
        
        setListAdapter(adapter);
        
        final ListView lv = getListView();
        lv.setTextFilterEnabled(true);	
        lv.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {        		
        		@SuppressWarnings("unchecked")
				HashMap<String, String> o = (HashMap<String, String>) lv.getItemAtPosition(position);	        		
        		Toast.makeText(ListProducts.this, "ID '" + o.get("id") + "' was clicked.", Toast.LENGTH_SHORT).show(); 

			}
		});
    }
}