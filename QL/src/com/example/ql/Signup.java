package com.example.ql;

import DB.DBAdapter;
import DB.NewDB;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends Activity {
	
	EditText edtUserName,edtPassword,edtConfirmPassword;
	Button btnCreateAccount;
	//LoginDataBaseAdapter loginDataBaseAdapter;
	DBAdapter dbAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		
		// get Instance  of Database Adapter
		//loginDataBaseAdapter=new LoginDataBaseAdapter(this);
		//loginDataBaseAdapter=loginDataBaseAdapter.open();
		
		//=================new test =====================
		dbAdapter = new DBAdapter(this);
		dbAdapter = dbAdapter.open();
		
		// Get Refferences of Views
		edtUserName=(EditText)findViewById(R.id.edtUserName);
		edtPassword=(EditText)findViewById(R.id.edtPassword);
		edtConfirmPassword=(EditText)findViewById(R.id.edtConfirmPassword);
		btnCreateAccount = (Button) findViewById(R.id.btnCreateAccount);
	}
	
	//ham SignUP
//	public void CreateAccount(View v){
//		
//		String userName=edtUserName.getText().toString();
//		String password=edtPassword.getText().toString();
//		String confirmPassword=edtConfirmPassword.getText().toString();
//		
//		// check if any of the fields are vaccant
//		if(userName.equals("")||password.equals("")||confirmPassword.equals(""))
//		{
//				Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ vào các trường!", Toast.LENGTH_LONG).show();
//				return;
//		}
//		// check if both password matches
//		if(!password.equals(confirmPassword))
//		{
//			Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
//			return;
//		}
//		else
//		{
//		    // Save the Data in Database
//		    loginDataBaseAdapter.insertEntry(userName, password);
//		    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
//		    
//		    //call login screen
//		    Intent intent = new Intent(this, MainActivity.class);
//		    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//		    startActivity(intent);
//		    finish();
//		}
//	}

	
	//ham SignUP moi 12/11/2015
		public void CreateAccount(View v){
			
			String userName=edtUserName.getText().toString();
			String password=edtPassword.getText().toString();
			String confirmPassword=edtConfirmPassword.getText().toString();
			
			// check if any of the fields are vaccant
			if(userName.equals("")||password.equals("")||confirmPassword.equals(""))
			{
					Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ vào các trường!", Toast.LENGTH_LONG).show();
					return;
			}
			// check if both password matches
			if(!password.equals(confirmPassword))
			{
				Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
				return;
			}
			else
			{
			    // Save the Data in Database
				
				dbAdapter.createUser(userName, password);
				//newDB.close();
			    //loginDataBaseAdapter.insertEntry(userName, password);
			    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
			    
			    //call login screen
			    Intent intent = new Intent(this, MainActivity.class);
			    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			    startActivity(intent);
			    finish();
			}
		}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.signup, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		dbAdapter.close();
		//loginDataBaseAdapter.close();
	}
}
