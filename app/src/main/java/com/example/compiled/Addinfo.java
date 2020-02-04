package com.example.compiled;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import androidx.appcompat.app.AppCompatActivity;

public class Addinfo extends AppCompatActivity {
/*
    EditText Name,Email,Mobile;
    String name,email,mobile;

 */
    EditText Name,Age,Height,Weight,Data;
    String name,age,height,weight,data;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_info_layout);
        Name=(EditText)findViewById(R.id.et_name);
        Age=(EditText)findViewById(R.id.et_age);
        Height=(EditText)findViewById(R.id.et_height);
        Weight=(EditText)findViewById(R.id.et_weight);
        Data=(EditText)findViewById(R.id.et_data);


    }


    public void saveInfo (View view)
    {
        name=Name.getText().toString();
        age=Age.getText().toString();
        height=Height.getText().toString();
        weight=Weight.getText().toString();
        data=Data.getText().toString();

        BackgroundTask backgroundTask=new BackgroundTask();

        backgroundTask.execute(name,age,height,weight,data);
        //backgroundTask.execute(name,age,height,weight);
        finish();

    }

    class BackgroundTask extends AsyncTask<String,Void,String> {

        String add_info_url;

        @Override
        protected void onPreExecute() {

            add_info_url= "https://manz1234.000webhostapp.com/add_info.php";
        }


        @Override
        protected String doInBackground(String... args) {

            String name,age,height,weight,data;
            //String name,age,height,weight;
            name= args[0];

            age= args[1];
            height= args[2];
            weight= args[3];
            data= args[4];

            try {
                URL url = new URL(add_info_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data_string = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("age", "UTF-8") + "=" + URLEncoder.encode(age, "UTF-8") + "&" +
                        URLEncoder.encode("height", "UTF-8") + "=" + URLEncoder.encode(height, "UTF-8") + "&" +
                        URLEncoder.encode("weight", "UTF-8") + "=" + URLEncoder.encode(weight, "UTF-8") + "&" +
                        URLEncoder.encode("data", "UTF-8") + "=" + URLEncoder.encode(data, "UTF-8");


/*
                String data_string = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("age", "UTF-8") + "=" + URLEncoder.encode(age, "UTF-8") + "&" +
                        URLEncoder.encode("height", "UTF-8") + "=" + URLEncoder.encode(height, "UTF-8") + "&" +
                        URLEncoder.encode("weight", "UTF-8") + "=" + URLEncoder.encode(weight, "UTF-8");
*/
                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();

                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();


                httpURLConnection.disconnect();


                return "One row of data inserted";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }  catch (IOException e) {
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
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
        }

    }







}