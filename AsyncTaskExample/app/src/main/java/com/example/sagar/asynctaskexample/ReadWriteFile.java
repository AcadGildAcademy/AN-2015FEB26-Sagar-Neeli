package com.example.sagar.asynctaskexample;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class ReadWriteFile extends ActionBarActivity implements View.OnClickListener{

    private static final String FILENAME = "test.txt";
    private static final String FILEPATH = "TestFileStorage";
    private static final String DNAME = "myfiles";

    EditText editText;
    TextView display;
    Button add, delete;

    File dataFile;
    File rootPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_write_file);

//        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
//        directory = contextWrapper.getDir(filepath, Context.MODE_PRIVATE);
//        myInternalFile = new File(directory , filename);

        editText = (EditText) findViewById(R.id.editText);
        display = (TextView) findViewById(R.id.textView);
        add = (Button) findViewById(R.id.buttonAdd);
        delete = (Button) findViewById(R.id.buttonDelete);

        add.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_read_write_file, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonAdd:
                ReadWriteAsyncTask write = new ReadWriteAsyncTask();
                write.execute();
                break;
            case R.id.buttonDelete:
                dataFile.delete();
                break;
        }


    }

    class ReadWriteAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected void onProgressUpdate(String... data) {
            super.onProgressUpdate(data);
            try {
                FileOutputStream mOutput = new FileOutputStream(dataFile, false);
                mOutput.write(data[0].getBytes());
                mOutput.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected String doInBackground(String... params) {
            String data = editText.getText().toString();
            rootPath = new File(Environment.getExternalStorageDirectory(), DNAME);
            if(!rootPath.exists()) {
                rootPath.mkdirs();
            }
            if(!rootPath.exists()) {
                rootPath.mkdirs();
            }
            dataFile = new File(rootPath, FILENAME);
            if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//                Toast.makeText(this, "Cannot use storage.", Toast.LENGTH_SHORT).show();
                finish();
            }
            publishProgress(data);
            return null;
        }

        @Override
        protected void onPostExecute(String a) {
            super.onPostExecute(a);
//            display.setText();
            try {
                FileInputStream mInput = new FileInputStream(dataFile);
                byte[] data = new byte[128];
                mInput.read(data);
                mInput.close();
                String tv = new String(data);
                display.setText(tv.trim());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
