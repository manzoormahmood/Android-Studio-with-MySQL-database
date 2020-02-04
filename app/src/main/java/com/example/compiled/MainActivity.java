package com.example.compiled;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    String json_string;
    Button B3;//B4;
    TextView textView2;
    EditText nameInput;
    String name_input;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        B3=(Button)findViewById(R.id.b3);
        //B4=(Button)findViewById(R.id.b4);
        textView2=(TextView)findViewById(R.id.textView2);

        ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();

        if (networkInfo!=null & networkInfo.isConnected())
        {
            textView2.setVisibility(View.INVISIBLE);
        }
        else
        {
            B3.setEnabled(false);
            //B4.setEnabled(false);
        }
    }

    public void addContact(View view)
    {
        startActivity(new Intent(this,Addinfo.class));

    }

    public void getJSON(View view)
    {

        new BackgroundTask().execute();


    }




    class BackgroundTask extends AsyncTask<Void,Void,String> {

        String json_url;



        @Override
        protected void onPreExecute() {
            json_url="https://manz1234.000webhostapp.com/json_get_data.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();
                while((json_string=bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(json_string+"\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            TextView textView=(TextView)findViewById(R.id.textView);
            //textView.setText(result);

            nameInput=(EditText)findViewById(R.id.input_name);
            name_input=nameInput.getText().toString();



            textView.setVisibility(View.INVISIBLE);

            json_string=result;

            if (json_string==null)
            {
                Toast.makeText(getApplicationContext(),"First get json",Toast.LENGTH_LONG).show();
            }
            else
            {
                Intent intent=new Intent(getApplicationContext(),DisplayListView.class);
                intent.putExtra("json_data",json_string);
                intent.putExtra("name_input",name_input);

                startActivity(intent);
            }

        }
    }


/*

    public void parseJSON(View view)
    {
        if (json_string==null)
        {
            Toast.makeText(getApplicationContext(),"First get json",Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent intent=new Intent(this,DisplayListView.class);
            intent.putExtra("json_data",json_string);
            startActivity(intent);
        }

    }

 */


}
