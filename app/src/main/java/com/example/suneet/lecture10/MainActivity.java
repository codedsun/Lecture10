package com.example.suneet.lecture10;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button; ProgressDialog  progressDialog;
    MyAsyncTask myAsyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (TextView) findViewById(R.id.textView);
        button= (Button) findViewById(R.id.button);
        myAsyncTask = new MyAsyncTask();
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                        try {

                                            myAsyncTask.execute(10);
                                        }
                                        catch (Exception e)
                                        {
                                            textView.setText("The Data is Loaded ");
                                        }
                                      }
                                  }
        );
    }

    class MyAsyncTask extends AsyncTask<Integer,Integer,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Heii..pLease Wait");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {//iska values progress value hai
            super.onProgressUpdate(values);
            progressDialog.setMessage(values[0]+" second has passed");
        }

        @Override
        protected Void doInBackground(Integer... integers) {//iska integers input hai
            //runs on another thread than main thread
            for(int i=0;i<10;i++)
            {
                long currTime=System.currentTimeMillis();
                while(System.currentTimeMillis()-currTime<1000);
                publishProgress(i);


            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            textView.setText("10 seconds have passed");
            super.onPostExecute(aVoid);
            progressDialog.hide();
        }

    }
}
