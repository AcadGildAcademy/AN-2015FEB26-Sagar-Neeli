package acadgild.example.sharedpreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


public class MainActivity extends Activity implements OnClickListener{

	EditText user;
	CheckBox check;
	Button save;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		check = (CheckBox)findViewById(R.id.check);
		user = (EditText) findViewById(R.id.user);
		save = (Button) findViewById(R.id.save);
		save.setOnClickListener(this);

		loadMySavedPreferences(); //User defined

	}

	private void loadMySavedPreferences() {
		// TODO Auto-generated method stub
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		String str = sp.getString("USER", "Acadgild");//getting Default value for ET
		boolean cb = sp.getBoolean("CHECK", true);//getting Default value for CB


		user.setText(str);//setting that value

		check.setChecked(cb);//setting that value


	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		saveMySharedPrefrences("CHECK",check.isChecked());
		if(check.isChecked())
		{
			String str = user.getText().toString();
			saveMySharedPrefrences("USER",str);
		}
		finish();
	}

	private void saveMySharedPrefrences(String key, String value) {
		// TODO Auto-generated method stub
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);//getting the default file
		Editor ed = sp.edit();//open the file with an editor
		ed.putString(key, value);//input the value to the file
		ed.commit();//save 
	}

	private void saveMySharedPrefrences(String key, boolean value) {
		// TODO Auto-generated method stub
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);//getting the default file
		Editor ed = sp.edit();//open the file with an editor
		ed.putBoolean(key, value);//input the value to the file
		ed.commit();//save 
	}
}
