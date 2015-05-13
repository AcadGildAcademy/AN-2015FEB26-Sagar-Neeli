package com.example.sagar.aysnctask;

import android.os.AsyncTask;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;


public class ParallelAysncTask extends ActionBarActivity implements View.OnClickListener{

    ProgressBar progressBar1, progressBar2, progressBar3, progressBar4;
    Button download;
    MyAsyncTask asyncTask1, asyncTask2, asyncTask3, asyncTask4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallel_aysnc_task);

        download = (Button) findViewById(R.id.buttonDownload);
        progressBar1 = (ProgressBar)findViewById(R.id.progressBar1);
        progressBar2 = (ProgressBar)findViewById(R.id.progressBar2);
        progressBar3 = (ProgressBar)findViewById(R.id.progressBar3);
        progressBar4 = (ProgressBar)findViewById(R.id.progressBar4);

        download.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_parallel_aysnc_task, menu);
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
        asyncTask1 = new MyAsyncTask(progressBar1);
        StartAsyncTaskInParallel(asyncTask1);
        asyncTask2 = new MyAsyncTask(progressBar2);
        StartAsyncTaskInParallel(asyncTask2);
        asyncTask3 = new MyAsyncTask(progressBar3);
        asyncTask3.execute();
        asyncTask4 = new MyAsyncTask(progressBar4);
        asyncTask4.execute();
    }

    private void StartAsyncTaskInParallel(MyAsyncTask task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            task.execute();
    }

    class MyAsyncTask extends AsyncTask<Void, Integer, Void> {

        ProgressBar myProgressBar;

        public MyAsyncTask(ProgressBar myProgressBar) {
            this.myProgressBar = myProgressBar;
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 0; i < 100; i++) {
                publishProgress(i);
                SystemClock.sleep(100);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            myProgressBar.setProgress(values[0]);
        }
    }
}
